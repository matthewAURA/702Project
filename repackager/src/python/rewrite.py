#!/usr/bin/python


import re,sys
import xml.dom.minidom as xml
#open the file from stdin


'''
Annotations for defining method joins
existingPackage.class.method injectedPackage.class.method arg1 arg2 ... 

example

com.example.MyClass.foo com.injected.MyInjectedClass java.lang.String
    
'''


class MethodInjector:
    def __init__(self):
        self.methodMappings = [("android.content.ContentResolver.query","com.secure.ResourceLogger.logQuery",[1])]
        self.methodReplaces = []
        for mapping in self.methodMappings:
            r = MethodReplace(mapping[0],mapping[1],mapping[2])
            self.methodReplaces.append(r)

    def swapLine(self,line):
        for replace in self.methodReplaces:
            if replace.targetMethod in line:
                print line
                line = replace.getNewMethodCode(line) + "\n" + line
                return line
        return line

class MethodReplace:
    def __init__(self,targetMethodString,newMethodString,argIndexes):
        self.targetMethodString = targetMethodString
        self.newMethodString = newMethodString
        self.argIndexes = argIndexes
        self.targetMethod = self.__parseMethod__(self.targetMethodString)

    #Disclaimer: This code is amazingly well written
    def __generateNewArguments__(self,line):
        invocationParts = line.strip().split(" ")
        invocationType = invocationParts[0]
        invocationArgs = invocationParts[1:len(invocationParts)-1]
        argumentTypes = invocationParts[-1].split("(")[1].split(")")[0].split(";")
        argumentTypes.insert(0,"")
        args = []
        if "/range" in invocationType:
            start = int(re.findall(r'\d+', invocationArgs[0])[0])
            end = int(re.findall(r'\d+', invocationArgs[-1])[0])
            prefix = invocationArgs[0].strip("{")[0]
            tmpArgs = []
            for i in range(start,end):
                tmpArgs.append(prefix+str(i))
            invocationArgs = tmpArgs

        for id in self.argIndexes:
                args.append((invocationArgs[id].strip("{,"),argumentTypes[id].strip("[]")))

        return args

    def __parseMethod__(self,methodString):
        name = methodString.split(".")[-1]
        return "L"+"/".join(methodString.split(".")[:len(methodString.split("."))-1])+ ";->"+name

    def getNewMethodCode(self,line):
        args = self.__generateNewArguments__(line)
        self.newMethod = self.__parseMethod__(self.newMethodString)
        str = "\tinvoke-static {"
        for arg in args:
            str += arg[0]
            if (args.index(arg) != len(args)-1):
                str += ", "
        str+=  "}, "+self.newMethod+"("
        for arg in args:
            str += arg[1]+";"
        str+=")V"
        return str



def findMainActivity():
    manifestFile = sys.argv[2]
    with open(manifestFile) as manifest:
        xmlManifest =  xml.parseString(manifest.read())
        nodes = xmlManifest.getElementsByTagName('activity')
        for node in nodes:
            intentFilter = node.getElementsByTagName('intent-filter')
            for filter in intentFilter:
                actions = filter.getElementsByTagName('action')
                for action in actions:
                    if action.attributes["android:name"].value == "android.intent.action.MAIN":
                        return node.attributes["android:name"].value
    return None

def parseFileHead(mainActivity,head):
    if head.split(" ")[0] == ".class" and head.split(" ")[-1].strip("\n") == mainActivity:
        return True
    else:
        return False

def injectServiceStart(activtiy,line):
    if ";->onCreate(Landroid/os/Bundle;)V" in line:
        print "Found Main line"
        print line
        line = line +"\n\tinvoke-virtual {p0}, "+activtiy+"->getApplicationContext()Landroid/content/Context;\n"+\
        "\tmove-result-object v4\n"+\
        "\tsput-object v4, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;\n"
    return line



def main():
    injector = MethodInjector()
    mainActivity = findMainActivity()
    mainActivity = "L"+"/".join(mainActivity.split("."))+";"
    if (len(sys.argv) < 3):
        print "usage: rewrite.py [application file] [AndroidManifest.xml]"
    else:
        fname = sys.argv[1]
        print fname
        out = ""
        with open(fname,'r') as file:
            lines = file.read().split("\n")
            for i in range(0,len(lines)):
                if (i == 0):
                    isMain = parseFileHead(mainActivity,lines[i])
                if (isMain):
                    lines[i] = injectServiceStart(mainActivity,lines[i])
                out+= injector.swapLine(lines[i]) + "\n"

        targetFile = open(fname,'w')
        targetFile.write(out)
        targetFile.close()


if __name__ == "__main__":
    main()


'''
    Legacy Methods
    
    '''
def modifiySmali(text,targetClassName,newClassName):
    lines = text.split('\n')
    regexClassName = targetClassName.replace('/','\/')
    for i in range(0,len(lines)):
        #do not match method invocations
        if not re.match('.*\(.*'+regexClassName+'.*\).*',lines[i]) and re.match('.*'+regexClassName+'.*',lines[i]):
            lines[i] = lines[i].replace(targetClassName,newClassName)
    text = '\n'.join(lines)
    return text

def renameMethods(sourceFile,targetFile):
    swaps = []
    with open(sourceFile,'r') as fp:
        for line in fp:
            if "#!rename" in line:
                parts = line.rstrip('\n').split(' ')
                swaps.append((parts[1],parts[2]))
    lines = targetFile.split('\n')
    for i in range(0,len(lines)):
        for targetMethod,newMethod in swaps:
            lines[i] = lines[i].replace(targetMethod,newMethod)
    targetFile = '\n'.join(lines)
    return targetFile



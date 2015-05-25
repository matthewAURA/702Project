#!/usr/bin/python


import re,sys
import xml.dom.minidom as xml
#open the file from stdin

'''
MethodInjector Class
Injects smali source code around target methods to add logging functionality
'''
class MethodInjector:
    def __init__(self):
        '''
        Annotations for defining method joins
        existingPackage.class.method injectedPackage.class.method [args]

        '''
        self.methodMappings = [("android.content.ContentResolver.query","com.secure.ResourceLogger.logQuery",[1])]
        self.methodReplaces = []
        for mapping in self.methodMappings:
            r = MethodReplace(mapping[0],mapping[1],mapping[2])
            self.methodReplaces.append(r)

    '''
    Takes in a line of source  code and if it matches a method that needs code injected, will return the modified line of
    code with the call to the logging service
    '''
    def swapLine(self,line):
        for replace in self.methodReplaces:
            if replace.targetMethod in line:
                print line
                line = replace.getNewMethodCode(line) + "\n" + line
                return line
        return line


'''
MethodReplace: Helper method to manipulate strings in smali.
'''
class MethodReplace:
    def __init__(self,targetMethodString,newMethodString,argIndexes):
        self.targetMethodString = targetMethodString
        self.newMethodString = newMethodString
        self.argIndexes = argIndexes
        self.targetMethod = self.parseMethod(self.targetMethodString)

    '''
    Disclaimer: This code is amazingly well written

    Basically, we want to take an argument from the method we are injecting code around and call our logging method with that
    argument. This method gets the argument names from the target method call
    '''
    def generateNewArguments(self,line):
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

    '''
    Swaps a method from the package.class.method format to the smali format of Lpackage/class;->Method
    '''
    def parseMethod(self,methodString):
        name = methodString.split(".")[-1]
        return "L"+"/".join(methodString.split(".")[:len(methodString.split("."))-1])+ ";->"+name

    '''
    Generates a smali method invocation from the given base line of smali
    '''
    def getNewMethodCode(self,line):
        args = self.generateNewArguments(line)
        self.newMethod = self.parseMethod(self.newMethodString)
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


'''
Opens the AndroidManifest.xml manifest and parses it to find the main activity, returns the class name of the main activity
'''
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

'''
Determines if the class is the main activity
'''
def parseFileHead(mainActivity,head):
    if head.split(" ")[0] == ".class" and head.split(" ")[-1].strip("\n") == mainActivity:
        return True
    else:
        return False


'''
Given the main activity entry point, injects a line of code to the onCreate method to provide a context for our logging application.
'''
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
    if (len(sys.argv) != 3):
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

        #yeah, this is the reason why parsing smali files is so slow, I couldn't be bothered doing it properly - Matt
        targetFile = open(fname,'w')
        targetFile.write(out)
        targetFile.close()


if __name__ == "__main__":
    main()


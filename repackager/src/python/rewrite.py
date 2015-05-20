#!/usr/bin/python


import re,sys,os
#open the file from stdin


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

def main():
    if (len(sys.argv) != 3):
        print "usage: rewrite.py [application file] [modified smali directory]"
    else:
        fname = sys.argv[1]
        targetFile = open(fname,'r');
        out = targetFile.read()
        targetFile.close()
        classSwaps = []
        for modFileName in os.listdir(sys.argv[2]):
            if ('.smali' in modFileName):
                modFile = open(sys.argv[2] + '/' + modFileName,'r')
                newClassName = modFile.readline().split(' ')[-1].rstrip(';\n')
                targetClassName = modFile.readline().split(' ')[-1].rstrip(';\n')
                classSwaps.append((targetClassName,newClassName))
                out = renameMethods(sys.argv[2] + '/' + modFileName,out)
                modFile.close()

        for targetClassName,newClassName in classSwaps:
            print "replacing " + targetClassName + " with " + newClassName
            out = modifiySmali(out,targetClassName,newClassName)

        targetFile = open(fname,'w')
        targetFile.write(out)
        targetFile.close()
if __name__ == "__main__":
    main()


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

def main():
    if (len(sys.argv) != 3):
        print "usage: rewrite.py [application file] [modified smali directory]"
    else:
        fname = sys.argv[1]
        print fname
        targetFile = open(fname,'r');
        out = targetFile.read()
        targetFile.close()
        for modFileName in os.listdir(sys.argv[2]):
            if ('.smali' in modFileName):
                modFile = open(sys.argv[2] + '/' + modFileName,'r')
                newClassName = modFile.readline().split(' ')[-1].rstrip(';\n')
                targetClassName = modFile.readline().split(' ')[-1].rstrip(';\n')
                print targetClassName,newClassName
                out = modifiySmali(out,targetClassName,newClassName)

        targetFile = open(fname,'w')
        targetFile.write(out)
        targetFile.close()
if __name__ == "__main__":
    main()


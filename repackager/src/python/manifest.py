import sys
import re




def main():
    manifestFile = sys.argv[1]
    with open(manifestFile) as manifest:
        manifest = manifest.read()
        applicationString =  re.findall("\<application.*\>",manifest)[0]
        pos = manifest.find(applicationString)+len(applicationString)
        manifest = manifest[:pos] + "\n<service android:name=\"com.secure.InjectionService\" android:enabled=\"true\"/>" + manifest[pos:]
                   #'\n<receiver android:name="com.secure.Receiver">\n<intent-filter>\n<action android:name="android.intent.action.TIME_TICK" />\n<action android:name="android.intent.action.USER_PRESENT" />\n<action android:name="android.intent.action.PACKAGE_FIRST_LAUNCH" />\n<action android:name="android.intent.action.PACKAGE_REPLACED" />\n</intent-filter>\n</receiver>'\
        newManifest = manifest
    targetFile = open(manifestFile,'w')
    targetFile.write(newManifest)
    targetFile.close()

if __name__ == "__main__":
    main()

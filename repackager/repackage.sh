#!/bin/sh

origApk=$1

apkTool=Apktool/brut.apktool/apktool-cli/build/libs/apktool-cli.jar

if [ -z $origApk ];
then
    echo "usage: repackage.sh [target.apk]"
else

    #unpack the apk using apktool

    java -jar $apkTool d -f ${origApk}

    workDir=`pwd`/${origApk%%.apk}

    #do stuff to modify the apk here

    find $workDir/smali/com -regex '.*smali' -type f -exec python src/python/rewrite.py {} src/java/com/secure/ \;
    #copy in new .smali files
    cp -r src/java/* $workDir/smali

    #Modify Android Manifest
    rm ${workDir}/original/AndroidManifest.xml
    python src/python/manifest.py ${workDir}/AndroidManifest.xml

    #repackage
    modifiedApk="${workDir}-modified.apk"
    java -jar $apkTool b -c ${workDir} -o ${modifiedApk}

    ##aapt
    #./aurasium/dependencies/aapt/aapt package -m -J ${workDir}



    #re sign
    python ./aurasium/pyAPKRewriter/src/Signer.py -resign ${modifiedApk} ${origApk}
fi
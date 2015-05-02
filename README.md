# COMPSCI 702 Project

## An Android security framework to monitor resource accesses

This project monitors Android applications by modifing their bytecode and logging accesses to system resources such as photos, contacts and music. The project's approach is based off of, and uses part of the [Aurasium](https://github.com/xurubin/aurasium) framework. 

### Modifying an Android .apk

```
1) git clone https://github.com/matthewAURA/702Project
2) cd repackager
3) make
4) ./repackage.sh <your_application.apk>
```

This will change the signature of your APK!

Licence:

[Apktool](https://github.com/iBotPeaches/Apktool) is used under an Apache Licence.

[Aurasium](https://github.com/xurubin/aurasium) is used under a GPLv3 Licence.

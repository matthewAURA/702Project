# COMPSCI 702 Project

## An Android security framework to monitor resource accesses

This project monitors Android applications by modifing their bytecode and logging accesses to system resources such as photos, contacts and music. The project's approach is based off of, and uses part of the [Aurasium](https://github.com/xurubin/aurasium) framework. 

### Modifying an Android .apk

```
1. git clone https://github.com/matthewAURA/702Project
2. cd repackager
3. make
4. ./repackage.sh <your_application.apk> 

The apk must be in the repackager directory
```

This will change the signature of your APK and inject our resource access logging code into the application.

Licence:

[Apktool](https://github.com/iBotPeaches/Apktool) is used under an Apache Licence.

[Aurasium](https://github.com/xurubin/aurasium) is used under a GPLv3 Licence.

### The test Resource Access App

#### Installation
1. Open the git repository as a new project in AndroidStudio
2. Run the "resourceaccessapp"

#### Features
- Human triggered access of Contacts and Audio
- Human triggered intent creation to access an Image
- Continual machine access of the phone Contacts

### The Viewer App
#### Installation
1. Open the git repository as a new project in AndroidStudio
2. Run the "app"

#### Features
- List display of all recorded resource accesses in the database
- List items expandable to display more info
- Swipe to refresh the list with new resource accesses
- Clear All menu option to clear the database

### Our project findings
[Google Doc] (https://docs.google.com/document/d/17WsT-D2gnf_n3IeoNfgEpp2LW62qtbXXbajp_pAODjM/edit?usp=sharing) 

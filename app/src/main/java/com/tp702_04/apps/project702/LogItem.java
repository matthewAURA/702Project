package com.tp702_04.apps.project702;

/**
 * Created by Nazish Khan on 10/04/2015.
 */
public class LogItem {

    //private variables
    int _id;
    String _resource_accessed_name;
    String _app;
    String _date;
    String _time;
    String _tag_message;
    String _is_machine_access;

    public LogItem(){
        //empty constructor
    }

    public LogItem(int _id, String _resource_accessed_name, String _app, String _date, String _time, String _tag_message, String _is_machine_access){
        this._id = _id;
        this._resource_accessed_name = _resource_accessed_name;
        this._app = _app;
        this._date = _date;
        this._time = _time;
        this._tag_message = _tag_message;
        this._is_machine_access = _is_machine_access;
    }

    public LogItem(String _resource_accessed_name, String _app, String _date, String _time, String _tag_message, String _is_machine_access){
        this._resource_accessed_name = _resource_accessed_name;
        this._app = _app;
        this._date = _date;
        this._time = _time;
        this._tag_message = _tag_message;
        this._is_machine_access = _is_machine_access;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting resource accessed name
    public String getName(){
        return this._resource_accessed_name;
    }

    // setting resource accessed name
    public void setName(String _resource_accessed_name){
        this._resource_accessed_name = _resource_accessed_name;
    }

    // getting application that accesses the resource
    public String getApp(){
        return this._app;
    }

    // setting application that accesses the resource
    public void setApp(String _app){
        this._app = _app;
    }

    // getting date
    public String getDate(){
        return this._date;
    }

    // setting date
    public void setDate(String _date){
        this._date= _date;
    }

    // getting time
    public String getTime(){
        return this._time;
    }

    // setting time
    public void setTime(String _time){
        this._time= _time;
    }

    // getting tag message
    public String getTagMessage(){
        return this._tag_message;
    }

    // setting tag message
    public void setTagMessage(String _tag_message){
        this._tag_message= _tag_message;
    }

    public String getIsMachineAccess() {return this._is_machine_access; }

    public void setIsMachineAccess(String _is_machine_access) { this._is_machine_access = _is_machine_access;}
}

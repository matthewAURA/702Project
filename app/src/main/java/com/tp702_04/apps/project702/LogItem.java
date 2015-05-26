package com.tp702_04.apps.project702;

/**
 * @Author: Nazish Khan
 * @Since 10/04/2015
 */

/**
 * This class stores information about an individual log item and its characteristics.
 */

public class LogItem {

    int _id;
    String _resource_accessed_name;
    String _app;
    String _date;
    String _time;
    String _tag_message;
    String _is_machine_access;

    public LogItem(){

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

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._resource_accessed_name;
    }

    public void setName(String _resource_accessed_name){ this._resource_accessed_name = _resource_accessed_name; }

    public String getApp(){
        return this._app;
    }

    public void setApp(String _app){
        this._app = _app;
    }

    public String getDate(){
        return this._date;
    }

    public void setDate(String _date){
        this._date= _date;
    }

    public String getTime(){
        return this._time;
    }

    public void setTime(String _time){
        this._time= _time;
    }

    public String getTagMessage(){
        return this._tag_message;
    }

    public void setTagMessage(String _tag_message){
        this._tag_message= _tag_message;
    }

    public String getIsMachineAccess() {return this._is_machine_access; }

    public void setIsMachineAccess(String _is_machine_access) { this._is_machine_access = _is_machine_access;}
}

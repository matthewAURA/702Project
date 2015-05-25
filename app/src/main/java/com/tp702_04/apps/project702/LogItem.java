package com.tp702_04.apps.project702;

/**
 * Created by Nazish Khan on 10/04/2015.
 * This class stores information about an individual log item and its characteristics.
 */
public class LogItem {

    //private variables
    int _id;
    String _resource_accessed_name;
    String _app;
    String _date;
    String _time;
    String _tag_message;

    public LogItem(){
        //empty constructor
    }

    public LogItem(int _id, String _resource_accessed_name, String _app, String _date, String _time, String _tag_message){
        this._id = _id;
        this._resource_accessed_name = _resource_accessed_name;
        this._app = _app;
        this._date = _date;
        this._time = _time;
        this._tag_message = _tag_message;
    }

    public LogItem(String _resource_accessed_name, String _app, String _date, String _time, String _tag_message){
        this._resource_accessed_name = _resource_accessed_name;
        this._app = _app;
        this._date = _date;
        this._time = _time;
        this._tag_message = _tag_message;
    }

    // getting log item ID
    public int getID(){
        return this._id;
    }

    // setting log item id
    public void setID(int id){
        this._id = id;
    }

    // getting name of the resource being accessed
    public String getName(){
        return this._resource_accessed_name;
    }

    // setting name of the resource being accessed
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

    // getting date of when the access was made
    public String getDate(){
        return this._date;
    }

    // setting date of when the access was made
    public void setDate(String _date){
        this._date= _date;
    }

    // getting time of when the access was made
    public String getTime(){
        return this._time;
    }

    // setting time of when the access was made
    public void setTime(String _time){
        this._time= _time;
    }

    // getting tag message associated with access made
    public String getTagMessage(){
        return this._tag_message;
    }

    // setting tag message associated with access made
    public void setTagMessage(String _tag_message){
        this._tag_message= _tag_message;
    }
}

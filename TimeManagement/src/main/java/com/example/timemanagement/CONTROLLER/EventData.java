package com.example.timemanagement.CONTROLLER;

import javafx.beans.property.ObjectProperty;

import java.time.LocalDate;
import java.util.Date;

public class EventData {
    public static String[]status={"Complete","Not finish","In Progress"};
    private  String Event;
    private Date Date_start;

    private Date dateend;
    private Date date;
    private String Status;
    private int id;
    public EventData(int id, String Event, Date Date_start, Date dateend, Date date, String Status){
        this.id=id;
        this.Event=Event;
        this.Date_start=Date_start;
        this.dateend=dateend;
        this.date=date;
        this.Status=Status;
    }
    public EventData( String Event , Date Date_start, Date dateend,String Status){
        this.Event=Event;
        this.Date_start=Date_start;
        this.dateend=dateend;
        this.date=date;
        this.Status=Status;
    }

    public Date getDate() {
        return date;
    }

    public Date getDate_end() {
        return dateend;
    }

    public int getId() {
        return id;
    }

    public Date getDate_start() {
        return Date_start;
    }

    public String getEvent() {
        return Event;
    }

    public String getStatus() {
        return Status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(String event) {
        this.Event = Event;
    }

    public void setDate_end(Date date_end) {
        this.dateend = dateend;
    }

    public void setDate_start(Date date_start) {
        this.Date_start = Date_start;
    }

    public void setStatus(String status) {
        this.Status = Status;
    }
}

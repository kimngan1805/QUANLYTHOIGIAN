package com.example.timemanagement.CONTROLLER;

import java.util.Date;

public class TaskData {
    private int id;
    private Date date;
    private Date date_start;
    private String text;
    private String Status;
    private String List;
    public static String[]status={"Complete","Not finish","In Progress"};
    public TaskData( Date date, String text, Date date_start, String Status){
        this.date=date;
        this.date_start=date_start;
        this.text=text;
        this.Status=Status;
    }
    public  TaskData(Date date, String List, String Status){
        this.date=date;
       this.List=List;
       this.Status=Status;
    }
    public  TaskData( String List, String Status){
        this.List=List;
        this.Status=Status;
    }


    public void setList(String list) {
        List = list;
    }

    public String getList() {
        return List;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public String getStatus() {
        return Status;
    }

    public Date getDate_start() {
        return date_start;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setStatus(String status) {
        this.Status = Status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

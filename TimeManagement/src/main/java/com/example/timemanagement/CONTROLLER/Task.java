package com.example.timemanagement.CONTROLLER;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Task {
    private String list;
    private Date date;

    public Task(String list, Date date){
        this.list=list;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public String getList() {
        return list;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

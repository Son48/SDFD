package com.example.sdfd.models;

public class HistoryModel {
    String name;
    String curentDate;
    String curentTime;

    public HistoryModel() {
    }

    public HistoryModel(String name, String curentDate, String curentTime) {
        this.name = name;
        this.curentDate = curentDate;
        this.curentTime = curentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurentDate() {
        return curentDate;
    }

    public void setCurentDate(String curentDate) {
        this.curentDate = curentDate;
    }

    public String getCurentTime() {
        return curentTime;
    }

    public void setCurentTime(String curentTime) {
        this.curentTime = curentTime;
    }
}

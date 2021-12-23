package com.example.sdfd.models;

import java.io.Serializable;

public class HistoryModel implements Serializable {
    String name;
    int calo;
    String description;
    String img_url;
    String curentDate;
    String curentTime;

    public HistoryModel() {
    }

    public HistoryModel(String name, int calo, String description, String img_url, String curentDate, String curentTime) {
        this.name = name;
        this.calo = calo;
        this.description = description;
        this.img_url = img_url;
        this.curentDate = curentDate;
        this.curentTime = curentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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
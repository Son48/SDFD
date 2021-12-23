package com.example.sdfd.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class HearModel implements Serializable {
    String name;
    String description;
    int calo;
    String img_url;
    String type;
    String time;
    @Exclude
    private String hearid;

    public String getHearid() {
        return hearid;
    }

    public void setHearid(String hearid) {
        this.hearid = hearid;
    }

    public HearModel(String name, String description, int calo, String img_url) {
        this.name = name;
        this.description = description;
        this.calo = calo;
        this.img_url = img_url;

    }

    public HearModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

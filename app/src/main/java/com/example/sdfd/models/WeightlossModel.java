package com.example.sdfd.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class WeightlossModel implements Serializable {
    String description;
    String name;
    String calo;
    String img_url;
    String type;
    String time;

    @Exclude
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WeightlossModel() {
    }

    public WeightlossModel(String description, String name, String calo, String img_url, String type, String time) {
        this.description = description;
        this.name = name;
        this.calo = calo;
        this.img_url = img_url;
        this.type = type;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalo() {
        return calo;
    }

    public void setCalo(String calo) {
        this.calo = calo;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
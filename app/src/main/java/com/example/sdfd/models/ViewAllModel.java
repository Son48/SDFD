package com.example.sdfd.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String description;
    String name;
    String type;
    String calo;
    String img_url;

    public ViewAllModel() {
    }

    public ViewAllModel(String description, String name, String type, String calo, String img_url) {
        this.description = description;
        this.name = name;
        this.type = type;
        this.calo = calo;
        this.img_url=img_url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCalo() {
        return calo;
    }

    public void setCalo(String calo) {
        calo = calo;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

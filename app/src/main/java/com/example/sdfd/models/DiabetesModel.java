package com.example.sdfd.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

import javax.annotation.meta.Exclusive;

public class DiabetesModel implements Serializable {
    String name;
    String description;
    int calo;
    String img_url;
    String type;
    String time;
    @Exclude
    private String iddia;

    public String getIddia() {
        return iddia;
    }

    public void setIddia(String iddia) {
        this.iddia = iddia;
    }

    public DiabetesModel() {
    }

    public DiabetesModel(String name, String description, int calo, String img_url) {
        this.name = name;
        this.description = description;
        this.calo = calo;
        this.img_url = img_url;
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

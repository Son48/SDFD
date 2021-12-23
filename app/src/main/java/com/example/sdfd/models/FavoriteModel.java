package com.example.sdfd.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class FavoriteModel implements Serializable {
    String name;
    String description;
    int calo;
    String img_url;
    String ingredient;
    String type;
    String time;
    String instruction;
    @Exclude
    private String idfa;

    public FavoriteModel() {
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public FavoriteModel(String name, String description, int calo, String img_url, String ingredient, String type, String time, String instruction) {
        this.name = name;
        this.description = description;
        this.calo = calo;
        this.img_url = img_url;
        this.ingredient = ingredient;
        this.type = type;
        this.time = time;
        this.instruction = instruction;
    }
}

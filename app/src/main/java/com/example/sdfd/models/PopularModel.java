package com.example.sdfd.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class PopularModel implements Serializable {
    String name;
    String description;
    double calo;
    String img_url;
    String ingredient;
    String time;
    String type;
    String instruction;
    @Exclude
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PopularModel() {
    }

    public PopularModel(String name, String description, double calo, String img_url, String ingredient, String time, String type, String instruction) {
        this.name = name;
        this.description = description;
        this.calo = calo;
        this.img_url = img_url;
        this.ingredient = ingredient;
        this.time = time;
        this.type = type;
        this.instruction = instruction;
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

    public double getCalo() {
        return calo;
    }

    public void setCalo(double calo) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}

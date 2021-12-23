package com.example.sdfd.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String description;
    String name;
    double calo;
    String img_url;
    String type;
    String time;
    String ingredient;
    String instruction;

    public ViewAllModel() {
    }

    public ViewAllModel(String description, String name, double calo, String img_url, String type, String time, String ingredient, String instruction) {
        this.description = description;
        this.name = name;
        this.calo = calo;
        this.img_url = img_url;
        this.type = type;
        this.time = time;
        this.ingredient = ingredient;
        this.instruction = instruction;
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}

package com.example.sdfd.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class MenuModel implements Serializable {
   int calo;
   String name;
   String description;
   String img_url;

    @Exclude
    private String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public MenuModel(int calo, String name, String description, String img_url) {
        this.calo = calo;
        this.name = name;
        this.description = description;
        this.img_url = img_url;
    }

    public MenuModel() {
    }


}

package com.example.sdfd.models;

public class UserClass {
    String name,email,phone,pass,profileImg;
    int TDEE,age,height,weight;
    int calointoday;
    public UserClass() {

    }
    public UserClass(String name, String email, String phone, String pass) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.profileImg = profileImg;
        this.TDEE=TDEE;
        this.age=age;
        this.height=height;
        this.weight=weight;
    }

    public int getCalointoday() {
        return calointoday;
    }

    public void setCalointoday(int calointoday) {
        this.calointoday = calointoday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getTDEE() {
        return TDEE;
    }

    public void setTDEE(int TDEE) {
        this.TDEE = TDEE;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

package com.example.patel.dotaretrofit.Model;

import com.google.gson.annotations.SerializedName;

public class Heroes {

    @SerializedName("localized_name")
    private String name;

    @SerializedName("primary_attr")
    private String attribute;

    @SerializedName("attack_type")
    private String attackType;

    @SerializedName("img")
    private String image;

    public Heroes(String name, String attribute, String attackType, String image) {
        this.name = name;
        this.attribute = attribute;
        this.attackType = attackType;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

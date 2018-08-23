package com.example.patel.dotaretrofit.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "heroes_table")
public class Heroes implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("localized_name")
    private String name;

    @SerializedName("primary_attr")
    private String attribute;

    @SerializedName("attack_type")
    private String attackType;

    @SerializedName("img")
    private String image;


    public Heroes(int id, String name, String attribute, String attackType, String image) {
        this.id = id;
        this.name = name;
        this.attribute = attribute;
        this.attackType = attackType;
        this.image = image;
    }

    @Ignore
    public Heroes(String name, String attribute, String attackType, String image) {
        this.name = name;
        this.attribute = attribute;
        this.attackType = attackType;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.attribute);
        dest.writeString(this.attackType);
        dest.writeString(this.image);
    }

    protected Heroes(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.attribute = in.readString();
        this.attackType = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Heroes> CREATOR = new Parcelable.Creator<Heroes>() {
        @Override
        public Heroes createFromParcel(Parcel source) {
            return new Heroes(source);
        }

        @Override
        public Heroes[] newArray(int size) {
            return new Heroes[size];
        }
    };
}

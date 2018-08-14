package com.yaratech.yaratube.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("is_default")
    @Expose
    private boolean isDefault;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("is_enable")
    @Expose
    private boolean isEnable;
    @SerializedName("is_visible")
    @Expose
    private boolean isVisible;
    @SerializedName("parent")
    @Expose
    private int parent;

    protected Category(Parcel in) {
        id = in.readInt();
        isDefault = in.readByte() != 0;
        title = in.readString();
        avatar = in.readString();
        position = in.readInt();
        isEnable = in.readByte() != 0;
        isVisible = in.readByte() != 0;
        parent = in.readInt();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar){this.avatar=avatar;}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeByte((byte) (isDefault ? 1 : 0));
        parcel.writeString(title);
        parcel.writeString(avatar);
        parcel.writeInt(position);
        parcel.writeByte((byte) (isEnable ? 1 : 0));
        parcel.writeByte((byte) (isVisible ? 1 : 0));
        parcel.writeInt(parent);
    }
}
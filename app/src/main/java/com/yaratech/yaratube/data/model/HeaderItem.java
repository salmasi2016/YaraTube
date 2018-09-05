package com.yaratech.yaratube.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeaderItem implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_english")
    @Expose
    private String nameEnglish;
    @SerializedName("product_type")
    @Expose
    private int productType;
    @SerializedName("producer_name")
    @Expose
    private String producerName;
    @SerializedName("payment_type")
    @Expose
    private List<Object> paymentType = null;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;
    @SerializedName("feature_avatar")
    @Expose
    private FeatureAvatar featureAvatar;
    @SerializedName("rank")
    @Expose
    private double rank;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("is_purchased")
    @Expose
    private boolean isPurchased;
    @SerializedName("comments")
    @Expose
    private int comments;
    @SerializedName("is_bookmarked")
    @Expose
    private boolean isBookmarked;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price_unit")
    @Expose
    private String priceUnit;
    @SerializedName("total_view")
    @Expose
    private int totalView;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("invest_goal")
    @Expose
    private Object investGoal;
    @SerializedName("product_staff")
    @Expose
    private List<Object> productStaff = null;
    @SerializedName("support")
    @Expose
    private Support support;
    @SerializedName("is_special")
    @Expose
    private boolean isSpecial;
    @SerializedName("additional_attributes")
    @Expose
    private List<Object> additionalAttributes = null;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("customjson")
    @Expose
    private Object customjson;

    protected HeaderItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        nameEnglish = in.readString();
        productType = in.readInt();
        producerName = in.readString();
        price = in.readInt();
        rank = in.readDouble();
        shortDescription = in.readString();
        isPurchased = in.readByte() != 0;
        comments = in.readInt();
        isBookmarked = in.readByte() != 0;
        sku = in.readString();
        priceUnit = in.readString();
        totalView = in.readInt();
        dateAdded = in.readString();
        isSpecial = in.readByte() != 0;
        datePublished = in.readString();
    }

    public static final Creator<HeaderItem> CREATOR = new Creator<HeaderItem>() {
        @Override
        public HeaderItem createFromParcel(Parcel in) {
            return new HeaderItem(in);
        }

        @Override
        public HeaderItem[] newArray(int size) {
            return new HeaderItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public int getProductType() {
        return productType;
    }

    public String getProducerName() {
        return producerName;
    }

    public List<Object> getPaymentType() {
        return paymentType;
    }

    public int getPrice() {
        return price;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public FeatureAvatar getFeatureAvatar() {
        return featureAvatar;
    }

    public double getRank() {
        return rank;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isIsPurchased() {
        return isPurchased;
    }

    public int getComments() {
        return comments;
    }

    public boolean isIsBookmarked() {
        return isBookmarked;
    }

    public String getSku() {
        return sku;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public int getTotalView() {
        return totalView;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public Object getInvestGoal() {
        return investGoal;
    }

    public List<Object> getProductStaff() {
        return productStaff;
    }

    public Support getSupport() {
        return support;
    }

    public boolean isIsSpecial() {
        return isSpecial;
    }

    public List<Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public Object getCustomjson() {
        return customjson;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(nameEnglish);
        parcel.writeInt(productType);
        parcel.writeString(producerName);
        parcel.writeInt(price);
        parcel.writeDouble(rank);
        parcel.writeString(shortDescription);
        parcel.writeByte((byte) (isPurchased ? 1 : 0));
        parcel.writeInt(comments);
        parcel.writeByte((byte) (isBookmarked ? 1 : 0));
        parcel.writeString(sku);
        parcel.writeString(priceUnit);
        parcel.writeInt(totalView);
        parcel.writeString(dateAdded);
        parcel.writeByte((byte) (isSpecial ? 1 : 0));
        parcel.writeString(datePublished);
    }
}
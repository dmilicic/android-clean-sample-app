package com.kodelabs.mycosts.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by dmilicic on 2/14/16.
 */
public class RESTCost {

    @SerializedName("id")
    private long mId;

    @SerializedName("category")
    private String mCategory;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("date")
    private Date mDate;

    @SerializedName("amount")
    private double mAmount;


    public RESTCost(long id, String category, String description, Date date, double amount) {
        mId = id;
        mCategory = category;
        mDescription = description;
        mDate = date;
        mAmount = amount;
    }

    public long getId() {
        return mId;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDescription() {
        return mDescription;
    }

    public Date getDate() {
        return mDate;
    }

    public double getAmount() {
        return mAmount;
    }
}

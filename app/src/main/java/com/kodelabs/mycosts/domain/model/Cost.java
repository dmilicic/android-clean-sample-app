package com.kodelabs.mycosts.domain.model;

import java.util.Date;

/**
 * Created by dmilicic on 12/10/15.
 */
public class Cost {
    private long   mId;
    private String mCategory;
    private String mDescription;
    private Date   mDate;
    private double mAmount;

    public Cost(String category, String description, Date date, double amount) {

        // cost will be uniquely identified by the current timestamp
        mId = new Date().getTime();

        mCategory = category;
        mDescription = description;
        mDate = date;
        mAmount = amount;
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

    @Override
    public String toString() {
        return "Cost{" +
                "mId=" + mId +
                ", mCategory='" + mCategory + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mDate=" + mDate +
                ", mAmount=" + mAmount +
                '}';
    }
}

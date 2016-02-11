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

        // cost will be "uniquely" identified by the current timestamp
        mId = new Date().getTime();

        mCategory = category;
        mDescription = description;
        mDate = date;
        mAmount = amount;
    }


    /**
     * This constructor should be used when we are converting an already existing cost item to this POJO, so we already have
     * an id variable.
     */
    public Cost(String category, String description, Date date, double amount, long id) {
        mId = id;
        mCategory = category;
        mDescription = description;
        mDate = date;
        mAmount = amount;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setAmount(double amount) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cost cost = (Cost) o;

        return mId == cost.mId;

    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
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

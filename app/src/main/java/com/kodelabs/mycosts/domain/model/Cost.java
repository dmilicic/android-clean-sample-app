package com.kodelabs.mycosts.domain.model;

import java.util.Date;

/**
 * Created by dmilicic on 12/10/15.
 */
public class Cost {
    private String mCategory;
    private String mDescription;
    private Date   mDate;
    private double mAmount;

    public Cost(String category, String description, Date date, double amount) {
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
}

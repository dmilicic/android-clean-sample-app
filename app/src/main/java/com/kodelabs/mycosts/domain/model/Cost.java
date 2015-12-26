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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cost cost = (Cost) o;

        if (mId != cost.mId) return false;
        if (Math.abs(cost.mAmount - mAmount) > 10E-9) return false;
        if (!mCategory.equals(cost.mCategory)) return false;
        if (mDescription != null ? !mDescription.equals(cost.mDescription) : cost.mDescription != null) return false;
        return mDate.equals(cost.mDate);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + mCategory.hashCode();
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        result = 31 * result + mDate.hashCode();
        temp = Double.doubleToLongBits(mAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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

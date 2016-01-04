package com.kodelabs.mycosts.presentation.model;

import com.kodelabs.mycosts.domain.model.Cost;

import java.util.Date;
import java.util.List;

/**
 * Created by dmilicic on 1/4/16.
 */
public class DailyTotalCost {

    private List<Cost> mCostList;

    private Date   mDate;
    private double mTotalCost;

    public DailyTotalCost(List<Cost> costList, Date date) {
        mCostList = costList;
        mDate = date;

        // eagerly calculate the total cost
        mTotalCost = 0.0;
        for (int idx = 0; idx < costList.size(); idx++) {
            mTotalCost += costList.get(idx).getAmount();
        }
    }

    public List<Cost> getCostList() {
        return mCostList;
    }

    public Date getDate() {
        return mDate;
    }

    public double getTotalCost() {
        return mTotalCost;
    }

    @Override
    public String toString() {
        return "DailyTotalCost{" +
                "mCostList=" + mCostList +
                ", mDate=" + mDate +
                ", mTotalCost=" + mTotalCost +
                '}';
    }
}

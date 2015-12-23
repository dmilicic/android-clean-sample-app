package com.kodelabs.mycosts.storage;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class CostRepositoryImpl implements CostRepository {

    // this represents are temporary database
    private static List<Cost> mCosts;

    static {
        mCosts = new ArrayList<>();
        mCosts.add(new Cost("Transportation", "ZET", new Date(), 100.0));
        mCosts.add(new Cost("Groceries", "ZET", new Date(), 100.0));
        mCosts.add(new Cost("Entertainment", "ZET", new Date(), 100.0));
        mCosts.add(new Cost("Bills", "HEP struja", new Date(), 100.0));
    }

    @Override
    public void insert(Cost item) {
        mCosts.add(item);
    }

    @Override
    public List<Cost> getCostsByDate(Date date) {
        return new ArrayList<>();
    }

    @Override
    public List<Cost> getCostsInRange(Date startDate, Date endDate) {
        return mCosts;
    }
}

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

    // this represents a temporary database
    private static volatile List<Cost> mCosts;

    static {
        mCosts = new ArrayList<>();
        mCosts.add(new Cost("Transportation", "ZET", new Date(), 100.0));
        mCosts.add(new Cost("Groceries", "ZET", new Date(), 100.0));
        mCosts.add(new Cost("Entertainment", "ZET", new Date(), 100.0));
        mCosts.add(new Cost("Bills", "HEP struja", new Date(), 100.0));
    }

    private CostRepositoryImpl() {
        // private constructor
    }

    private static CostRepository sCostRepository;

    public static CostRepository getInstance() {
        if (sCostRepository == null) {
            sCostRepository = new CostRepositoryImpl();
        }

        return sCostRepository;
    }

    @Override
    public void insert(Cost item) {
        mCosts.add(item);
    }

    @Override
    public void update(Cost cost) {
        // remove the old one with the same id
        mCosts.remove(cost);

        // add the new one back as the "edited" version
        mCosts.add(cost);
    }

    @Override
    public Cost getCostById(long id) {
        Cost currentCost = null;

        // find cost by id
        for (int i = 0; i < mCosts.size(); i++) {
            currentCost = mCosts.get(i);
            if (currentCost.getId() == id)
                return currentCost;
        }

        return null;
    }

    @Override
    public List<Cost> getCostsByDate(Date date) {
        return new ArrayList<>();
    }

    @Override
    public List<Cost> getCostsInRange(Date startDate, Date endDate) {

        // return a copy of the items in "database"
        List<Cost> copy = new ArrayList<>();
        for (int i = 0; i < mCosts.size(); i++) {
            copy.add(mCosts.get(i));
        }

        return copy;
    }

    @Override
    public void delete(Cost cost) {
        mCosts.remove(cost);
    }
}

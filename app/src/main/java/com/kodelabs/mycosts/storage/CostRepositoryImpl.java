package com.kodelabs.mycosts.storage;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;
import com.kodelabs.mycosts.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
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

        // get the today's date for some sample cost items
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        today = DateUtils.truncateHours(today); // set hours, minutes and seconds to 0 for simplicity

        // get yesterday as well
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        yesterday = DateUtils.truncateHours(yesterday); // set hours, minutes and seconds to 0 for simplicity

        // Since each cost is uniquely identified by a timestamp, we should make sure that the sample costs are
        // not created in the same millisecond, we simply pause a bit after each cost creation.
        try {
            mCosts.add(new Cost("Groceries", "Bought some X and some Y", today, 100.0));
            Thread.sleep(100);
            mCosts.add(new Cost("Bills", "Bill for electricity", today, 50.0));
            Thread.sleep(100);


            Thread.sleep(100);
            mCosts.add(new Cost("Transportation", "I took an Uber ride", yesterday, 10.0));
            Thread.sleep(100);
            mCosts.add(new Cost("Entertainment", "I went to see Star Wars!", yesterday, 50.0));


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CostRepositoryImpl() {
        // private constructor
    }

    private static volatile CostRepository sCostRepository;

    // we will make this a singleton
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
    public List<Cost> getAllCosts() {

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

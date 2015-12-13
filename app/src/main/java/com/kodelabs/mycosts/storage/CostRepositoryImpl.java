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
    @Override
    public List<Cost> getCostsByDate(Date date) {
        return new ArrayList<>();
    }

    @Override
    public List<Cost> getCostsInRange(Date startDate, Date endDate) {
        ArrayList<Cost> costs = new ArrayList<>();

        costs.add(new Cost("Transportation", "ZET", new Date(), 100.0));
        costs.add(new Cost("Groceries", "ZET", new Date(), 100.0));
        costs.add(new Cost("Entertainment", "ZET", new Date(), 100.0));
        costs.add(new Cost("Bills", "HEP struja", new Date(), 100.0));

        return costs;
    }
}

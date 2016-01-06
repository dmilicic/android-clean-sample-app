package com.kodelabs.mycosts.presentation.converter;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.model.DailyTotalCost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dmilicic on 1/4/16.
 */
public class DailyTotalCostConverter {

    public static List<DailyTotalCost> convertCostsToDailyCosts(List<Cost> costList) {

        List<DailyTotalCost> result = new ArrayList<>();

        if (costList == null || costList.size() == 0) {
            // return an empty array if we have nothing to convert
            return result;
        }

        // declare and initialize data vars
        List<Cost> dailyCosts = new ArrayList<>();
        Date currentDate = costList.get(0).getDate();
        Cost cost;

        // iterate over all cost items received
        for (int idx = 0; idx < costList.size(); idx++) {

            cost = costList.get(idx);

            if (idx == 0) { // in case this is the first element

                // initialize the process
                dailyCosts = new ArrayList<>();
                currentDate = cost.getDate();
            }

            // add the current element to the list of daily costs - for the current date
            dailyCosts.add(cost);

            // check if this is the last element
            if (idx == costList.size() - 1) {
                // create a new daily total match
                DailyTotalCost dailyTotalCost = new DailyTotalCost(dailyCosts, currentDate);
                result.add(dailyTotalCost);

                continue;
            }

            // get the next element
            Cost nextCost = costList.get(idx + 1);

            // check if the next element is from a different day
            if (!nextCost.getDate().equals(currentDate)) {
                // create a new daily total match
                DailyTotalCost dailyTotalCost = new DailyTotalCost(dailyCosts, currentDate);
                result.add(dailyTotalCost);

                // repeat the process with the next item
                dailyCosts = new ArrayList<>();
                currentDate = nextCost.getDate();
            }
        }

        return result;
    }
}

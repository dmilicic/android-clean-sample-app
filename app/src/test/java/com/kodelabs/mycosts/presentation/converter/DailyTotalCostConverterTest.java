package com.kodelabs.mycosts.presentation.converter;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.model.DailyTotalCost;
import com.kodelabs.mycosts.util.TestDateUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmilicic on 1/5/16.
 */
public class DailyTotalCostConverterTest {

    private static List<Cost> mCosts;

    @Test
    public void testDailyCostConversion() throws Exception {

        // init test
        mCosts = new ArrayList<>();
        mCosts.add(new Cost("Transportation", "ZET", TestDateUtil.getDate(2016, Calendar.JANUARY, 4), 100.0));
        mCosts.add(new Cost("Groceries", "ZET", TestDateUtil.getDate(2016, Calendar.JANUARY, 4), 200.0));
        mCosts.add(new Cost("Entertainment", "ZET", TestDateUtil.getDate(2016, Calendar.JANUARY, 4), 300.0));
        mCosts.add(new Cost("Bills", "HEP struja", TestDateUtil.getDate(2016, Calendar.JANUARY, 4), 400.0));

        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 2), 150.0));
        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 2), 110.0));
        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 2), 240.0));

        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 1), 130.0));
        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 1), 230.0));

        List<DailyTotalCost> dailyTotalCosts = DailyTotalCostConverter.convertCostsToDailyCosts(mCosts);

        // there should be 3 daily cost objects created for 3 different days
        assertEquals(3, dailyTotalCosts.size());

        // first day should have 4 cost items and a total sum of 1000
        assertEquals(4, dailyTotalCosts.get(0).getCostList().size());
        assertEquals(1000.0, dailyTotalCosts.get(0).getTotalCost(), 0.00001);

        // second day should have 3 cost items and a total sum of 500
        assertEquals(3, dailyTotalCosts.get(1).getCostList().size());
        assertEquals(500.0, dailyTotalCosts.get(1).getTotalCost(), 0.00001);

        // third day should have 2 cost items and a total sum of 360
        assertEquals(2, dailyTotalCosts.get(2).getCostList().size());
        assertEquals(360.0, dailyTotalCosts.get(2).getTotalCost(), 0.00001);
    }

    @Test
    public void testDailyCostConversion2() throws Exception {

        // init test
        mCosts = new ArrayList<>();
        mCosts.add(new Cost("Transportation", "ZET", TestDateUtil.getDate(2016, Calendar.JANUARY, 4), 100.0));

        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 2), 150.0));
        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 2), 110.0));
        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 2), 240.0));

        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 1), 130.0));
        mCosts.add(new Cost("Transportation", "Description", TestDateUtil.getDate(2016, Calendar.JANUARY, 1), 230.0));

        List<DailyTotalCost> dailyTotalCosts = DailyTotalCostConverter.convertCostsToDailyCosts(mCosts);

        // there should be 3 daily cost objects created for 3 different days
        assertEquals(3, dailyTotalCosts.size());

        assertEquals(1, dailyTotalCosts.get(0).getCostList().size());
        assertEquals(100.0, dailyTotalCosts.get(0).getTotalCost(), 0.00001);

        assertEquals(3, dailyTotalCosts.get(1).getCostList().size());
        assertEquals(500.0, dailyTotalCosts.get(1).getTotalCost(), 0.00001);

        // third day should have 2 cost items and a total sum of 360
        assertEquals(2, dailyTotalCosts.get(2).getCostList().size());
        assertEquals(360.0, dailyTotalCosts.get(2).getTotalCost(), 0.00001);
    }
}

package com.kodelabs.mycosts.utils;

import android.content.Context;

import com.kodelabs.mycosts.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dmilicic on 9/20/15.
 */
public class DateUtils {

    /**
     * Converts a date to the textual representation of dates used by people.
     *
     * @param date
     * @return If the date is of today, then this method will return 'Today's'. If its yesterday then 'Yesterday' is returned.
     * Otherwise it returns the date in the form of dd.mm
     */
    public static String dateToText(Context context, Date date) {
        String textDate;

        // clear hours, minutes and smaller time units from the date
        date = truncateHours(date);

        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        Date today = c.getTime();

        // get yesterday
        c.add(Calendar.DATE, -1);
        Date yesterday = c.getTime();


        if (date.equals(today)) { // test if today
            textDate = context.getString(R.string.today_s);
        } else if (date.equals(yesterday)) {  // test if yesterday
            textDate = context.getString(R.string.yesterday_s);
        } else {
            textDate = formatDate(date, new SimpleDateFormat("dd.MM"));
        }

        return textDate;
    }

    public static Date createDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // setup the date
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        // and get that as a Date
        Date resultDate = c.getTime();
        return resultDate;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);
        return sdf.format(date);
    }

    public static String formatDate(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }

    public static Date getToday() {

        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        Date today = c.getTime();

        return today;
    }

    public static Date truncateHours(Date date) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        return c.getTime();
    }
}

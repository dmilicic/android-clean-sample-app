package com.kodelabs.mycosts.storage.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by dmilicic on 2/11/16.
 */
@Database(name = CostDatabase.NAME, version = CostDatabase.VERSION)
public class CostDatabase {
    public static final String NAME = "Costs_db";

    public static final int VERSION = 1;
}

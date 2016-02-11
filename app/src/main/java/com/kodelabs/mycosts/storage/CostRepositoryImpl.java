package com.kodelabs.mycosts.storage;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;
import com.kodelabs.mycosts.storage.converters.StorageModelConverter;
import com.kodelabs.mycosts.storage.model.Cost_Table;
import com.kodelabs.mycosts.utils.AuthUtils;
import com.kodelabs.mycosts.utils.DateUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class CostRepositoryImpl implements CostRepository {

    private Context mContext;

    // let's generate some dummy data
    static {

        List<com.kodelabs.mycosts.storage.model.Cost> costs = SQLite.select()
                .from(com.kodelabs.mycosts.storage.model.Cost.class)
                .queryList();

        // if the database is empty, let's add some dummies
        if (costs.size() == 0) {

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
                com.kodelabs.mycosts.storage.model.Cost cost = new com.kodelabs.mycosts.storage.model.Cost("Groceries", "Bought some X and some Y", today, 100.0);
                cost.insert();
                Thread.sleep(100);
                cost = new com.kodelabs.mycosts.storage.model.Cost("Bills", "Bill for electricity", today, 50.0);
                cost.insert();
                Thread.sleep(100);


                Thread.sleep(100);
                cost = new com.kodelabs.mycosts.storage.model.Cost("Transportation", "I took an Uber ride", yesterday, 10.0);
                cost.insert();
                Thread.sleep(100);
                cost = new com.kodelabs.mycosts.storage.model.Cost("Entertainment", "I went to see Star Wars!", yesterday, 50.0);
                cost.insert();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public CostRepositoryImpl(Context context) {
        mContext = context;
    }

    /**
     * This method will start a sync adapter that will upload data to the server.
     */
    private void triggerSync() {
        // TODO sync adapter is forced for debugging purposes, remove this in production
        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        // request a sync using sync adapter
        Account account = AuthUtils.getAccount(mContext);
        ContentResolver.requestSync(account, mContext.getString(R.string.stub_content_authority), settingsBundle);
    }

    @Override
    public void insert(Cost item) {
        com.kodelabs.mycosts.storage.model.Cost dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.insert();

        triggerSync();
    }

    @Override
    public void update(Cost cost) {
        com.kodelabs.mycosts.storage.model.Cost dbItem = StorageModelConverter.convertToStorageModel(cost);
        dbItem.update();

        triggerSync();
    }

    @Override
    public Cost getCostById(long id) {
        com.kodelabs.mycosts.storage.model.Cost cost = SQLite
                .select()
                .from(com.kodelabs.mycosts.storage.model.Cost.class)
                .where(Cost_Table.id.eq(id))
                .querySingle();

        return StorageModelConverter.convertToDomainModel(cost);
    }

    @Override
    public List<Cost> getAllCosts() {

        List<com.kodelabs.mycosts.storage.model.Cost> costs = SQLite
                .select()
                .from(com.kodelabs.mycosts.storage.model.Cost.class)
                .queryList();

        return StorageModelConverter.convertListToDomainModel(costs);
    }

    @Override
    public void delete(Cost cost) {
        com.kodelabs.mycosts.storage.model.Cost dbItem = StorageModelConverter.convertToStorageModel(cost);
        dbItem.delete();

        triggerSync();
    }
}

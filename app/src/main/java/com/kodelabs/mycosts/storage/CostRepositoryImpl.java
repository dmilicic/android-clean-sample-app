package com.kodelabs.mycosts.storage;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;
import com.kodelabs.mycosts.storage.converters.StorageModelConverter;
import com.kodelabs.mycosts.storage.model.Cost_Table;
import com.kodelabs.mycosts.utils.DateUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class CostRepositoryImpl implements CostRepository {

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
        com.kodelabs.mycosts.storage.model.Cost dbItem = StorageModelConverter.convertToStorageModel(item);
        dbItem.save();
    }

    @Override
    public void update(Cost cost) {
        com.kodelabs.mycosts.storage.model.Cost dbItem = StorageModelConverter.convertToStorageModel(cost);
        dbItem.update();
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
    }
}

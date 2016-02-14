package com.kodelabs.mycosts.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;
import com.kodelabs.mycosts.network.RestClient;
import com.kodelabs.mycosts.network.converters.RESTModelConverter;
import com.kodelabs.mycosts.network.model.Payload;
import com.kodelabs.mycosts.network.model.RESTCost;
import com.kodelabs.mycosts.network.services.SyncService;
import com.kodelabs.mycosts.storage.CostRepositoryImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 *  * Handle the transfer of data between a server and an
 *  * app, using the Android sync adapter framework.
 *  
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private Context mContext;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContext = context;
    }


    private Callback<Void> mResponseCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Timber.i("UPLOAD SUCCESS: %d", response.code());
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Timber.e("UPLOAD FAIL");
            Timber.e(t.getMessage());
        }
    };

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider,
                              SyncResult syncResult) {
        Timber.i("IT'S WORKING!");

        // initialize the services we will use
        SyncService syncService = RestClient.getService(SyncService.class);
        CostRepository costRepository = new CostRepositoryImpl(mContext);

        // sync everything we have
        List<Cost> costs = costRepository.getAllCosts();
        for (Cost cost : costs) {

            // convert to models suitable for transferring over network
            RESTCost restCost = RESTModelConverter.convertToRestModel(cost);
            Payload payload = new Payload("default", restCost);

            // run the upload
            syncService.uploadData(payload)
                    .enqueue(mResponseCallback);

            break;
        }
    }
}
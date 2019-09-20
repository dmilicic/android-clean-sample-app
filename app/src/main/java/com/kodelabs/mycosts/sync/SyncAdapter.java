package com.kodelabs.mycosts.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;
import com.kodelabs.mycosts.network.RestClient;
import com.kodelabs.mycosts.network.converters.RESTModelConverter;
import com.kodelabs.mycosts.network.model.Payload;
import com.kodelabs.mycosts.network.model.RESTCost;
import com.kodelabs.mycosts.network.services.SyncService;
import com.kodelabs.mycosts.storage.CostRepositoryImpl;
import com.kodelabs.mycosts.utils.AuthUtils;

import java.io.IOException;
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

    private CostRepository mCostRepository;

    private List<Cost> mUnsyncedCosts;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
        mCostRepository = new CostRepositoryImpl(mContext);
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContext = context;
        mCostRepository = new CostRepositoryImpl(mContext);
    }

    /**
     * This method will start a sync adapter that will upload data to the server.
     */
    public static void triggerSync(Context context) {
        // TODO sync adapter is forced for debugging purposes, remove this in production
        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        // request a sync using sync adapter
        Account account = AuthUtils.getAccount(context);
        ContentResolver.requestSync(account, context.getString(R.string.stub_content_authority), settingsBundle);
    }


    private Callback<Void> mResponseCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Timber.i("UPLOAD SUCCESS: %d", response.code());

            if (response.isSuccessful()) {
                mCostRepository.markSynced(mUnsyncedCosts);
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Timber.e("UPLOAD FAIL");
            t.printStackTrace();

            // try to sync again
            SyncResult syncResult = new SyncResult();
        }
    };

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider,
                              SyncResult syncResult) {
        Timber.i("STARTING SYNC...");

        // initialize the services we will use
        SyncService syncService = RestClient.getService(SyncService.class);

        // TODO: get the real user's name
        Payload payload = new Payload("default");

        // get all unsynced data
        mUnsyncedCosts = mCostRepository.getAllUnsyncedCosts();
        for (Cost cost : mUnsyncedCosts) {

            // convert to models suitable for transferring over network
            RESTCost restCost = RESTModelConverter.convertToRestModel(cost);
            payload.addCost(restCost);
        }

        // run the upload
        try {
            Response<Void> response = syncService.uploadData(payload).execute();

            Timber.i("UPLOAD SUCCESS: %d", response.code());

            // everything went well, mark local cost items as synced
            if (response.isSuccessful()) {
                mCostRepository.markSynced(mUnsyncedCosts);
            }

        } catch (IOException e) { // something went wrong
            Timber.e("UPLOAD FAIL");
            // make it a soft error so the framework does the exponential backoff
            syncResult.stats.numIoExceptions += 1;

            Timber.d("Restarting sync in %d seconds", syncResult.delayUntil);
        }
    }
}
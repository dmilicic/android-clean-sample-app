package com.kodelabs.mycosts.network.services;

import com.kodelabs.mycosts.network.model.Payload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * A REST interface describing how data will be synced with the backend.
 * <p/>
 */
public interface SyncService {

    /**
     * This endpoint will be used to send new costs created on this device.
     */
    @Headers("Connection: close")
    @POST("/costs")
    Call<Void> uploadData(@Body Payload data);
}
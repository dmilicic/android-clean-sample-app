package com.kodelabs.mycosts.network.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by dmilicic on 2/14/16.
 */
public class Payload {

    @SerializedName("user")
    private String mUsername;

    @SerializedName("cost")
    private RESTCost mCost;

    public Payload(String username, RESTCost cost) {
        mUsername = username;
        mCost = cost;
    }

    public String getUsername() {
        return mUsername;
    }

    public RESTCost getCost() {
        return mCost;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        RESTCost cost = new RESTCost(100, "category", "desc", new Date(), 100.0);
        String username = "user";

        Payload data = new Payload(username, cost);

        String json = gson.toJson(data);

        System.out.println(json);
    }

}

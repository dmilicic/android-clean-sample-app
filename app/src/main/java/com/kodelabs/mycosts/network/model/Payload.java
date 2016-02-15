package com.kodelabs.mycosts.network.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dmilicic on 2/14/16.
 */
public class Payload {

    @SerializedName("user")
    private String mUsername;

    @SerializedName("costs")
    private List<RESTCost> mCosts;

    public Payload(String username) {
        mUsername = username;
        mCosts = new ArrayList<>();
    }

    public String getUsername() {
        return mUsername;
    }

    public List<RESTCost> getCosts() {
        return mCosts;
    }

    public void addCost(RESTCost cost) {
        mCosts.add(cost);
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        RESTCost cost = new RESTCost(100, "category", "desc", new Date(), 100.0);
        String username = "user";

        Payload data = new Payload(username);
        data.addCost(cost);

        cost = new RESTCost(200, "category", "desc", new Date(), 100.0);
        data.addCost(cost);

        cost = new RESTCost(300, "category", "desc", new Date(), 100.0);
        data.addCost(cost);

        String json = gson.toJson(data);

        System.out.println(json);
    }

}

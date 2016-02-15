package com.kodelabs.mycosts.storage.model;

import com.kodelabs.mycosts.storage.database.CostDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by dmilicic on 2/11/16.
 */
@Table(database = CostDatabase.class)
public class Cost extends BaseModel {


    @PrimaryKey
    private long id; // our base model already has an id, let's use it as a primary key

    @Column
    private String category;

    @Column
    private String description;

    @Column
    private Date date;

    @Column
    private double amount;

    @Column
    public boolean synced;

    public Cost() {
    }

    /**
     * This constructor is only used to create some dummy objects when the app starts.
     */
    public Cost(String category, String description, Date date, double amount) {
        // cost will be "uniquely" identified by the current timestamp
        this.id = new Date().getTime();
        this.category = category;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.synced = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}

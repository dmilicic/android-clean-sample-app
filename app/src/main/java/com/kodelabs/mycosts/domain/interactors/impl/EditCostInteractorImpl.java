package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.EditCostInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

import java.util.Date;

/**
 * Created by dmilicic on 12/26/15.
 */
public class EditCostInteractorImpl extends AbstractInteractor implements EditCostInteractor {


    private EditCostInteractor.Callback mCallback;
    private CostRepository              mCostRepository;

    private Cost mUpdatedCost;

    private String mCategory;
    private String mDescription;
    private Date   mDate;
    private double mAmount;


    public EditCostInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                  Callback callback, CostRepository costRepository,
                                  Cost updatedCost, String category, String description, Date date, double amount) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mCostRepository = costRepository;
        mUpdatedCost = updatedCost;
        mCategory = category;
        mDescription = description;
        mDate = date;
        mAmount = amount;
    }

    @Override
    protected void run() {

        // update the cost
        mUpdatedCost.setAmount(mAmount);
        mUpdatedCost.setCategory(mCategory);
        mUpdatedCost.setDate(mDate);
        mUpdatedCost.setDescription(mDescription);

        // update in the db
        mCostRepository.update(mUpdatedCost);

        // notify on main thread that the update was successful
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCostUpdated(mUpdatedCost);
            }
        });

    }
}

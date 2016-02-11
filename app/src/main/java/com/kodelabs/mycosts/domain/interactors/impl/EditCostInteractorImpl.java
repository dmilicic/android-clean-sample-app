package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.EditCostInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

import java.util.Date;

/**
 * This interactor handles editing a cost item. It should update it if it exists in the database, otherwise it should insert it.
 * <p/>
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
    public void run() {

        // check if it exists in the database
        long costId = mUpdatedCost.getId();
        Cost costToEdit = mCostRepository.getCostById(costId);

        // there is no item with this ID in the database, lets insert it
        if (costToEdit == null) {
            costToEdit = new Cost(mCategory, mDescription, mDate, mAmount);
            mCostRepository.insert(costToEdit);
        } else { // we found the item in the database, lets update it

            // update the cost
            costToEdit.setAmount(mAmount);
            costToEdit.setCategory(mCategory);
            costToEdit.setDate(mDate);
            costToEdit.setDescription(mDescription);

            // update in the db
            mCostRepository.update(costToEdit);
        }

        // notify on main thread that the update was successful
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCostUpdated(mUpdatedCost);
            }
        });

    }
}

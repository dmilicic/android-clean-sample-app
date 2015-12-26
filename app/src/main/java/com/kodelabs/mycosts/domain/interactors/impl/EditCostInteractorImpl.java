package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.EditCostInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

/**
 * Created by dmilicic on 12/26/15.
 */
public class EditCostInteractorImpl extends AbstractInteractor implements EditCostInteractor {


    private EditCostInteractor.Callback mCallback;
    private CostRepository              mCostRepository;

    private Cost mUpdatedCost;

    public EditCostInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                  EditCostInteractor.Callback callback, CostRepository costRepository,
                                  Cost updatedCost) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mCostRepository = costRepository;
        mUpdatedCost = updatedCost;
    }

    @Override
    protected void run() {

        // update the cost
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

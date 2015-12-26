package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.DeleteCostInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

/**
 * Created by dmilicic on 12/26/15.
 */
public class DeleteCostInteractorImpl extends AbstractInteractor implements DeleteCostInteractor {

    private Cost                          mCost;
    private DeleteCostInteractor.Callback mCallback;
    private CostRepository                mCostRepository;


    public DeleteCostInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                    Cost cost,
                                    Callback callback, CostRepository costRepository) {
        super(threadExecutor, mainThread);
        mCost = cost;
        mCallback = callback;
        mCostRepository = costRepository;
    }

    @Override
    protected void run() {

        // delete this cost item
        mCostRepository.delete(mCost);

        // notify on the main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCostDeleted(mCost);
            }
        });
    }
}

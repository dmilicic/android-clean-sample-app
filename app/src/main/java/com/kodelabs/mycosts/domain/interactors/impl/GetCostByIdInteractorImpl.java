package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.GetCostByIdInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

/**
 * Created by dmilicic on 12/27/15.
 */
public class GetCostByIdInteractorImpl extends AbstractInteractor implements GetCostByIdInteractor {

    private long                           mCostId;
    private CostRepository                 mCostRepository;
    private GetCostByIdInteractor.Callback mCallback;


    public GetCostByIdInteractorImpl(Executor threadExecutor, MainThread mainThread, long costId,
                                     CostRepository costRepository,
                                     Callback callback) {
        super(threadExecutor, mainThread);
        mCostId = costId;
        mCostRepository = costRepository;
        mCallback = callback;
    }

    @Override
    protected void run() {
        final Cost cost = mCostRepository.getCostById(mCostId);

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCostRetrieved(cost);
            }
        });
    }
}

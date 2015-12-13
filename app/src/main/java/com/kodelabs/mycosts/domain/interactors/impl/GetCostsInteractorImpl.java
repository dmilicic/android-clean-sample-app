package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.interactors.GetCostsInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by dmilicic on 12/10/15.
 */
public class GetCostsInteractorImpl extends AbstractInteractor implements GetCostsInteractor {

    private final Date           mStartDate;
    private final Date           mEndDate;
    private       Callback       mCallback;
    private       CostRepository mCostRepository;

    public GetCostsInteractorImpl(Executor threadExecutor, MainThread mainThread, CostRepository costRepository,
                                  Callback callback, Date startDate, Date endDate) {
        super(threadExecutor, mainThread);

        if (costRepository == null || callback == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }

        mStartDate = startDate;
        mEndDate = endDate;
        mCostRepository = costRepository;
        mCallback = callback;
    }

    @Override
    protected void run() {
        // retrieve the costs from the database
        final List<Cost> costs = mCostRepository.getCostsInRange(mStartDate, mEndDate);

        // Show costs on the main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCostsRetrieved(costs);
            }
        });
    }
}

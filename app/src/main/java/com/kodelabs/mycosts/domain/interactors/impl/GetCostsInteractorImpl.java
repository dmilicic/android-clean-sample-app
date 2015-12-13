package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.interactors.GetCostsInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.interactors.executor.Executor;
import com.kodelabs.mycosts.domain.interactors.executor.MainThread;
import com.kodelabs.mycosts.domain.repository.CostRepository;

import java.util.Date;

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

        if (mCostRepository == null || mCallback == null) {
            throw new IllegalArgumentException("Repository and callback objects can not be null!");
        }

        mStartDate = startDate;
        mEndDate = endDate;
        mCostRepository = costRepository;
        mCallback = callback;
    }

    @Override
    protected void run() {
        // TODO: get costs
    }
}

package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.GetAllCostsInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This interactor handles getting all costs from the database in a sorted manner. Costs should be sorted by date with
 * the most recent one coming first and the oldest one coming last.
 * <p/>
 * Created by dmilicic on 12/10/15.
 */
public class GetAllCostsInteractorImpl extends AbstractInteractor implements GetAllCostsInteractor {

    private Callback       mCallback;
    private CostRepository mCostRepository;

    private Comparator<Cost> mCostComparator = new Comparator<Cost>() {
        @Override
        public int compare(Cost lhs, Cost rhs) {

            if (lhs.getDate().before(rhs.getDate()))
                return 1;

            if (rhs.getDate().before(lhs.getDate()))
                return -1;

            return 0;
        }
    };

    public GetAllCostsInteractorImpl(Executor threadExecutor, MainThread mainThread, CostRepository costRepository,
                                     Callback callback) {
        super(threadExecutor, mainThread);

        if (costRepository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }

        mCostRepository = costRepository;
        mCallback = callback;
    }

    @Override
    public void run() {
        // retrieve the costs from the database
        final List<Cost> costs = mCostRepository.getAllCosts();

        // sort them so the most recent cost items come first, and oldest comes last
        Collections.sort(costs, mCostComparator);

        // Show costs on the main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCostsRetrieved(costs);
            }
        });
    }
}

package com.kodelabs.mycosts.domain.interactors.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.GetCostsInteractor;
import com.kodelabs.mycosts.domain.interactors.base.AbstractInteractor;

/**
 * Created by dmilicic on 12/10/15.
 */
public class GetCostsInteractorImpl extends AbstractInteractor implements GetCostsInteractor {

    private Callback mCallback;

    public GetCostsInteractorImpl(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    @Override
    public void run() {
        // TODO: get the data
    }

    @Override
    public void execute(Callback callback) {

        if (callback == null) {
            throw new IllegalArgumentException("Callback can not be null!");
        }

        this.mIsRunning = true;
        this.mCallback = callback;
        mThreadExecutor.execute(this);
    }
}

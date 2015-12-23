package com.kodelabs.mycosts.presentation.presenters.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.GetCostsInteractor;
import com.kodelabs.mycosts.domain.interactors.impl.GetCostsInteractorImpl;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.presenters.MainPresenter;
import com.kodelabs.mycosts.storage.CostRepositoryImpl;

import java.util.Date;
import java.util.List;

import timber.log.Timber;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl implements MainPresenter, GetCostsInteractor.Callback {

    private Executor           mExecutor;
    private MainThread         mMainThread;
    private MainPresenter.View mView;

    public MainPresenterImpl(Executor executor, MainThread mainThread,
                             View view) {
        mExecutor = executor;
        mMainThread = mainThread;
        mView = view;
    }

    @Override
    public void resume() {

        Timber.w("GETTING COST LIST");

        // get latest cost list
        GetCostsInteractor getCostsInteractor = new GetCostsInteractorImpl(mExecutor, mMainThread,
                new CostRepositoryImpl(), this, new Date(), new Date());
        getCostsInteractor.execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onCostsRetrieved(List<Cost> costList) {
        Timber.w("COST LIST RETRIEVED");
        mView.showCosts(costList);
    }
}

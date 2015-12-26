package com.kodelabs.mycosts.presentation.presenters.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.DeleteCostInteractor;
import com.kodelabs.mycosts.domain.interactors.GetCostsInteractor;
import com.kodelabs.mycosts.domain.interactors.impl.DeleteCostInteractorImpl;
import com.kodelabs.mycosts.domain.interactors.impl.GetCostsInteractorImpl;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.presenters.AbstractPresenter;
import com.kodelabs.mycosts.presentation.presenters.MainPresenter;
import com.kodelabs.mycosts.storage.CostRepositoryImpl;

import java.util.Date;
import java.util.List;

import timber.log.Timber;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        GetCostsInteractor.Callback,
        DeleteCostInteractor.Callback {

    private MainPresenter.View mView;

    public MainPresenterImpl(Executor executor, MainThread mainThread,
                             View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void resume() {
        // get latest cost list
        GetCostsInteractor getCostsInteractor = new GetCostsInteractorImpl(mExecutor, mMainThread,
                CostRepositoryImpl.getInstance(), this, new Date(), new Date());
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

    @Override
    public void deleteCost(Cost cost) {

        // delete this cost item in a background thread
        DeleteCostInteractor deleteCostInteractor = new DeleteCostInteractorImpl(mExecutor,
                mMainThread, cost, this, CostRepositoryImpl.getInstance());
        deleteCostInteractor.execute();
    }

    @Override
    public void onCostDeleted(Cost cost) {
        mView.onCostDeleted(cost);
    }
}

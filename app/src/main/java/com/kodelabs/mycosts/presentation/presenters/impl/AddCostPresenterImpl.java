package com.kodelabs.mycosts.presentation.presenters.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.AddCostInteractor;
import com.kodelabs.mycosts.domain.interactors.EditCostInteractor;
import com.kodelabs.mycosts.domain.interactors.impl.AddCostInteractorImpl;
import com.kodelabs.mycosts.domain.interactors.impl.EditCostInteractorImpl;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.presenters.AbstractPresenter;
import com.kodelabs.mycosts.presentation.presenters.AddCostPresenter;
import com.kodelabs.mycosts.storage.CostRepositoryImpl;

import java.util.Date;

/**
 * Created by dmilicic on 12/23/15.
 */
public class AddCostPresenterImpl extends AbstractPresenter implements AddCostPresenter,
        AddCostInteractor.Callback,
        EditCostInteractor.Callback {

    private AddCostPresenter.View mView;

    public AddCostPresenterImpl(Executor executor,
                                MainThread mainThread,
                                AddCostPresenter.View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void addNewCost(Date date, double amount, String description, String category) {
        AddCostInteractor addCostInteractor = new AddCostInteractorImpl(mExecutor,
                mMainThread,
                this,
                CostRepositoryImpl.getInstance(),
                category,
                description,
                date,
                amount);
        addCostInteractor.execute();
    }

    @Override
    public void onCostAdded() {
        mView.onCostAdded();
    }


    @Override
    public void editCost(Cost cost) {
        EditCostInteractor editCostInteractor = new EditCostInteractorImpl(mExecutor,
                mMainThread,
                this,
                CostRepositoryImpl.getInstance(),
                cost);
        editCostInteractor.execute();
    }

    @Override
    public void onCostUpdated(Cost cost) {
    }


    @Override
    public void resume() {

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
}

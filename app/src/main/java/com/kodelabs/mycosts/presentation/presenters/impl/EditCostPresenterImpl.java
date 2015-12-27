package com.kodelabs.mycosts.presentation.presenters.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.EditCostInteractor;
import com.kodelabs.mycosts.domain.interactors.GetCostByIdInteractor;
import com.kodelabs.mycosts.domain.interactors.impl.EditCostInteractorImpl;
import com.kodelabs.mycosts.domain.interactors.impl.GetCostByIdInteractorImpl;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.presenters.AbstractPresenter;
import com.kodelabs.mycosts.presentation.presenters.EditCostPresenter;
import com.kodelabs.mycosts.storage.CostRepositoryImpl;

import java.util.Date;

/**
 * Created by dmilicic on 12/27/15.
 */
public class EditCostPresenterImpl extends AbstractPresenter
        implements EditCostPresenter, GetCostByIdInteractor.Callback, EditCostInteractor.Callback {

    private EditCostPresenter.View mView;


    public EditCostPresenterImpl(Executor executor, MainThread mainThread,
                                 View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void getCostById(long id) {
        GetCostByIdInteractor getCostByIdInteractor = new GetCostByIdInteractorImpl(mExecutor, mMainThread,
                id, CostRepositoryImpl.getInstance(), this);
        getCostByIdInteractor.execute();
    }


    @Override
    public void onCostRetrieved(Cost cost) {
        mView.onCostRetrieved(cost);
    }

    @Override
    public void editCost(Cost cost, Date date, double amount, String description, String category) {
        EditCostInteractor editCostInteractor = new EditCostInteractorImpl(mExecutor,
                mMainThread, this, CostRepositoryImpl.getInstance(), cost,
                category, description, date, amount);
        editCostInteractor.execute();
    }

    @Override
    public void onCostUpdated(Cost cost) {
        mView.onCostUpdated(cost);
    }

}

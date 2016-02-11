package com.kodelabs.mycosts.presentation.presenters.impl;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.DeleteCostInteractor;
import com.kodelabs.mycosts.domain.interactors.GetAllCostsInteractor;
import com.kodelabs.mycosts.domain.interactors.impl.DeleteCostInteractorImpl;
import com.kodelabs.mycosts.domain.interactors.impl.GetAllCostsInteractorImpl;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;
import com.kodelabs.mycosts.presentation.converter.DailyTotalCostConverter;
import com.kodelabs.mycosts.presentation.model.DailyTotalCost;
import com.kodelabs.mycosts.presentation.presenters.AbstractPresenter;
import com.kodelabs.mycosts.presentation.presenters.MainPresenter;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        GetAllCostsInteractor.Callback,
        DeleteCostInteractor.Callback {

    private MainPresenter.View mView;
    private CostRepository     mCostRepository;

    public MainPresenterImpl(Executor executor, MainThread mainThread,
                             View view, CostRepository costRepository) {
        super(executor, mainThread);
        mView = view;
        mCostRepository = costRepository;
    }

    @Override
    public void resume() {
        getAllCosts();
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
    public void getAllCosts() {
        // get all costs
        GetAllCostsInteractor getCostsInteractor = new GetAllCostsInteractorImpl(
                mExecutor,
                mMainThread,
                mCostRepository,
                this
        );
        getCostsInteractor.execute();
    }

    @Override
    public void onCostsRetrieved(List<Cost> costList) {
        List<DailyTotalCost> dailyTotalCosts = DailyTotalCostConverter.convertCostsToDailyCosts(costList);
        mView.showCosts(dailyTotalCosts);
    }

    @Override
    public void deleteCost(long costId) {

        // delete this cost item in a background thread
        DeleteCostInteractor deleteCostInteractor = new DeleteCostInteractorImpl(
                mExecutor,
                mMainThread,
                costId,
                this,
                mCostRepository
        );
        deleteCostInteractor.execute();
    }


    @Override
    public void onCostDeleted(Cost cost) {
        mView.onCostDeleted(cost);
    }
}

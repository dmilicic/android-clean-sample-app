package com.kodelabs.mycosts.presentation.presenters;

import com.kodelabs.mycosts.presentation.ui.BaseView;

import java.util.Date;

/**
 * Created by dmilicic on 12/21/15.
 */
public interface AddCostPresenter extends BasePresenter {


    interface View extends BaseView {

        void onCostAdded();
    }

    void addNewCost(Date date, double amount, String description, String category);

}

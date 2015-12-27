package com.kodelabs.mycosts.presentation.presenters;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.ui.BaseView;

import java.util.Date;

/**
 * Created by dmilicic on 12/27/15.
 */
public interface EditCostPresenter {

    interface View extends BaseView {

        void onCostRetrieved(Cost cost);

        void onCostUpdated(Cost cost);
    }

    void getCostById(long id);

    void editCost(Cost cost, Date date, double amount, String description, String category);
}

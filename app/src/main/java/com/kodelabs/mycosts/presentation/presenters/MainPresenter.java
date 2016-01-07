package com.kodelabs.mycosts.presentation.presenters;

import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.model.DailyTotalCost;
import com.kodelabs.mycosts.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by dmilicic on 12/10/15.
 */
public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {

        void showCosts(List<DailyTotalCost> costs);

        void onClickDeleteCost(long costId);

        void onClickEditCost(long costId, int position);

        void onCostDeleted(Cost cost);
    }

    void getAllCosts();

    void deleteCost(long costId);
}

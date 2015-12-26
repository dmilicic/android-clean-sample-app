package com.kodelabs.mycosts.domain.interactors;

import com.kodelabs.mycosts.domain.interactors.base.Interactor;
import com.kodelabs.mycosts.domain.model.Cost;

/**
 * Created by dmilicic on 12/26/15.
 */
public interface DeleteCostInteractor extends Interactor {

    interface Callback {
        void onCostDeleted(Cost cost);
    }
}

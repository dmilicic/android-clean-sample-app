package com.kodelabs.mycosts.domain.interactors;

import com.kodelabs.mycosts.domain.interactors.base.Interactor;
import com.kodelabs.mycosts.domain.model.Cost;

/**
 * Created by dmilicic on 12/27/15.
 */
public interface GetCostByIdInteractor extends Interactor {

    interface Callback {
        void onCostRetrieved(Cost cost);

        void noCostFound();
    }
}

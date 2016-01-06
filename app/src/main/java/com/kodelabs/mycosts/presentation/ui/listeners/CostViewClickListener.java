package com.kodelabs.mycosts.presentation.ui.listeners;

/**
 * Created by dmilicic on 12/26/15.
 */
public interface CostViewClickListener {

    void onClickView(int position);

    void onClickDelete(int position);

    void onClickEdit(int position);
}

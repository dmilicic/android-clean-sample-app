package com.kodelabs.mycosts.presentation.ui.listeners;

/**
 * Created by dmilicic on 12/26/15.
 */
public interface RecyclerViewClickListener {

    void onClickView(int position);

    void onClickEdit(int position, long costId);

    void onClickDelete(int position, long costId);
}

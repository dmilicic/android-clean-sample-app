package com.kodelabs.mycosts.presentation.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.presenters.MainPresenter;
import com.kodelabs.mycosts.presentation.ui.listeners.CostViewClickListener;
import com.kodelabs.mycosts.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmilicic on 12/13/15.
 */
public class CostItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CostViewClickListener {

    private      List<Cost>         mCostList;
    private      Context            mContext;
    public final MainPresenter.View mView;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cost_item_title)
        public TextView mTitle;

        @Bind(R.id.cost_item_value)
        public TextView mTotalCost;

        @Bind(R.id.edit_btn)
        public ImageButton mEditBtn;

        @Bind(R.id.delete_btn)
        public ImageButton mDeleteBtn;

        public ViewHolder(View v, final CostViewClickListener listener) {
            super(v);
            ButterKnife.bind(this, v);

            // setup the delete button listener
            mDeleteBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(ViewHolder.this.getAdapterPosition());
                }
            });
        }
    }

    public CostItemAdapter(MainPresenter.View view, Context context) {
        mCostList = new ArrayList<>();
        mView = view;
        mContext = context;
    }

    @Override
    public void onClick(int position) {
        Cost cost = mCostList.get(position);
        mView.onCostItemClick(cost);
    }

    public void addNewCosts(@NonNull List<Cost> costList) {
        mCostList = costList;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.card_individual_cost_item, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Cost cost = mCostList.get(position);

        ViewHolder holder = (ViewHolder) viewHolder;

        final String dateText = DateUtils.dateToText(mContext, cost.getDate());
        final String title = String.format(mContext.getString(R.string.cost), dateText);
        holder.mTitle.setText(title);
        holder.mTotalCost.setText(String.valueOf(cost.getAmount()));
    }

    @Override
    public int getItemCount() {
        return mCostList.size();
    }
}

package com.kodelabs.mycosts.presentation.ui.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.ui.listeners.IndividualCostViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dmilicic on 1/6/16.
 */
public class CostItemView extends RelativeLayout implements OnMenuItemClickListener {

    @BindView(R.id.cost_item_title)
    TextView mCategoryView;

    @BindView(R.id.cost_item_total_value)
    TextView mValueView;

    @BindView(R.id.cost_item_description)
    TextView mDescriptionView;

    @BindView(R.id.button_menu)
    ImageButton mMenuButton;

    private IndividualCostViewClickListener mCostViewClickListener;

    private Cost mCost;

    public CostItemView(Context context,
                        IndividualCostViewClickListener costViewClickListener, Cost cost) {
        super(context);
        mCostViewClickListener = costViewClickListener;
        mCost = cost;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.individual_cost_item, this);

        ButterKnife.bind(this, view);

        setCategory(mCost.getCategory());
        setDescription(mCost.getDescription());
        setValue(mCost.getAmount());
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        // since the listener is set after this object is created it is possible that it can be null, avoid that :)
        if (mCostViewClickListener == null)
            return false;

        switch (item.getItemId()) {
            case R.id.item_edit:
                mCostViewClickListener.onClickEdit(mCost.getId());
                return true;
            case R.id.item_delete:
                mCostViewClickListener.onClickDelete(mCost.getId());
                return true;
            default:
                return false;
        }
    }


    @OnClick(R.id.button_menu)
    void onClickMenu() {
        PopupMenu popupMenu = new PopupMenu(getContext(), mMenuButton);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_cost_item);
        popupMenu.show();
    }

    private void setCategory(String category) {
        mCategoryView.setText(category);
    }

    private void setValue(double value) {
        String val = String.format("%.2f$", value);
        mValueView.setText(val);
    }

    private void setDescription(String description) {
        mDescriptionView.setText(description);
    }
}

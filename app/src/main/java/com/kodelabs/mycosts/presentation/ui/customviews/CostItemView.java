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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dmilicic on 1/6/16.
 */
public class CostItemView extends RelativeLayout implements OnMenuItemClickListener {

    @Bind(R.id.cost_item_title)
    TextView mCategoryView;

    @Bind(R.id.cost_item_total_value)
    TextView mValueView;

    @Bind(R.id.cost_item_description)
    TextView mDescriptionView;

    @Bind(R.id.button_menu)
    ImageButton mMenuButton;

    public CostItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.individual_cost_item, this);

        ButterKnife.bind(this, view);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_edit:
                // TODO edit item
                return true;
            case R.id.item_delete:
                // TODO delete item
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

    public void setCost(Cost cost) {
        setCategory(cost.getCategory());
        setDescription(cost.getDescription());
        setValue(cost.getAmount());
    }
}

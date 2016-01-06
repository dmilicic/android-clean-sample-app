package com.kodelabs.mycosts.presentation.ui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.model.Cost;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmilicic on 1/6/16.
 */
public class CostItemView extends RelativeLayout {

    @Bind(R.id.cost_item_title)
    TextView mCategoryView;

    @Bind(R.id.cost_item_total_value)
    TextView mValueView;

    @Bind(R.id.cost_item_description)
    TextView mDescriptionView;

    public CostItemView(Context context) {
        super(context);
        init(context);
    }

    public CostItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CostItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.individual_cost_item, this);

        ButterKnife.bind(this, view);
    }

    private void setCategory(String category) {
        mCategoryView.setText(category);
    }

    private void setValue(double value) {
        String val = String.valueOf(value);
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

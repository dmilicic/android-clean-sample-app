package com.kodelabs.mycosts.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.presentation.ui.fragments.DatePickerFragment;
import com.kodelabs.mycosts.utils.DateUtils;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.codetail.widget.RevealFrameLayout;

/**
 * Created by dmilicic on 12/26/15.
 */
public abstract class AbstractCostActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener {


    @Bind(R.id.reveal_layout)
    RevealFrameLayout mRevealLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.input_date)
    TextView mDateTextView;

    @Bind(R.id.input_amount)
    EditText mAmountEditText;

    @Bind(R.id.input_description)
    EditText mDescriptionEditText;

    @Bind(R.id.input_cost_category)
    Spinner mCategorySpinner;

    protected Date   mSelectedDate;
    protected String mDescription;
    protected String mCategory;
    protected double mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);
        ButterKnife.bind(this);

        mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mRevealLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.input_date)
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setListener(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_cost, menu);
        return true;
    }

    protected void extractFormData() {
        // extract data from the form
        try {
            mAmount = Double.valueOf(mAmountEditText.getText().toString());
        } catch (NumberFormatException e) {
            mAmount = 0.0;
        }

        // extract description and category
        mDescription = mDescriptionEditText.getText().toString();
        mCategory = mCategorySpinner.getSelectedItem().toString();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mSelectedDate = DateUtils.createDate(year, monthOfYear, dayOfMonth);
        mDateTextView.setText(DateUtils.formatDate(mSelectedDate));
    }
}

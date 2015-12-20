package com.kodelabs.mycosts.presentation.ui.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.presentation.ui.fragments.DatePickerFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.codetail.widget.RevealFrameLayout;

public class AddCostActivity extends AppCompatActivity {

    @Bind(R.id.reveal_layout)
    RevealFrameLayout mRevealLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.input_date)
    TextView mDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRevealLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.input_date)
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

}

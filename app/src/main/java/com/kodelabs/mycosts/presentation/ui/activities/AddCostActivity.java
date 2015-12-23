package com.kodelabs.mycosts.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.kodelabs.mycosts.MainThreadImpl;
import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.interactors.base.ThreadExecutor;
import com.kodelabs.mycosts.presentation.presenters.AddCostPresenter;
import com.kodelabs.mycosts.presentation.presenters.impl.AddCostPresenterImpl;
import com.kodelabs.mycosts.presentation.ui.fragments.DatePickerFragment;
import com.kodelabs.mycosts.utils.DateUtils;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.codetail.widget.RevealFrameLayout;
import timber.log.Timber;

public class AddCostActivity extends AppCompatActivity
        implements AddCostPresenter.View, DatePickerDialog.OnDateSetListener {

    @Bind(R.id.reveal_layout)
    RevealFrameLayout mRevealLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.input_date)
    TextView mDateTextView;

    private AddCostPresenter mPresenter;

    private Date   mSelectedDate;
    private String mDescription;
    private String mCategory;
    private double mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);
        ButterKnife.bind(this);

        Timber.w("ON CREATE ADDCOST");

        mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.w("BACK PRESSED");
                onBackPressed();
            }
        });


        mRevealLayout.setVisibility(View.VISIBLE);

        // setup the presenter
        mPresenter = new AddCostPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.w("ON RESUME ADDCOST");

        // default day should be today
        mSelectedDate = DateUtils.getToday();
        mDateTextView.setText(DateUtils.formatDate(mSelectedDate));
    }

    @OnClick(R.id.input_date)
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_cost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            mPresenter.addNewCost(new Date(), 150.25, "Description", "categoryx");
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCostAdded() {
        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mSelectedDate = DateUtils.createDate(year, monthOfYear, dayOfMonth);
        mDateTextView.setText(DateUtils.formatDate(mSelectedDate));
    }

}

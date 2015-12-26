package com.kodelabs.mycosts.presentation.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.kodelabs.mycosts.MainThreadImpl;
import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.interactors.base.ThreadExecutor;
import com.kodelabs.mycosts.presentation.presenters.AddCostPresenter;
import com.kodelabs.mycosts.presentation.presenters.impl.AddCostPresenterImpl;
import com.kodelabs.mycosts.utils.DateUtils;

import butterknife.ButterKnife;
import timber.log.Timber;

public class AddCostActivity extends AbstractCostActivity
        implements AddCostPresenter.View {

    private AddCostPresenter mPresenter;

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

            // extract data from the form
            try {
                mAmount = Double.valueOf(mAmountEditText.getText().toString());
            } catch (NumberFormatException e) {
                mAmount = 0.0;
            }

            // extract description and category
            mDescription = mDescriptionEditText.getText().toString();
            mCategory = mCategorySpinner.getSelectedItem().toString();

            // pass the data onto the presenter
            mPresenter.addNewCost(mSelectedDate, mAmount, mDescription, mCategory);
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

}

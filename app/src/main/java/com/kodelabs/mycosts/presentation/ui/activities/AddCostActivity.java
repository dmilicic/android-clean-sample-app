package com.kodelabs.mycosts.presentation.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.executor.impl.ThreadExecutor;
import com.kodelabs.mycosts.presentation.presenters.AddCostPresenter;
import com.kodelabs.mycosts.presentation.presenters.impl.AddCostPresenterImpl;
import com.kodelabs.mycosts.storage.CostRepositoryImpl;
import com.kodelabs.mycosts.threading.MainThreadImpl;
import com.kodelabs.mycosts.utils.DateUtils;

public class AddCostActivity extends AbstractCostActivity
        implements AddCostPresenter.View {

    private AddCostPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup the presenter
        mPresenter = new AddCostPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new CostRepositoryImpl(this)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // default day should be today
        mSelectedDate = DateUtils.getToday();
        mDateTextView.setText(DateUtils.formatDate(mSelectedDate));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {

            extractFormData();

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

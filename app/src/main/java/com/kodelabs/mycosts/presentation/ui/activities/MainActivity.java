package com.kodelabs.mycosts.presentation.ui.activities;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.kodelabs.mycosts.MainThreadImpl;
import com.kodelabs.mycosts.R;
import com.kodelabs.mycosts.domain.interactors.base.ThreadExecutor;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.presentation.presenters.MainPresenter;
import com.kodelabs.mycosts.presentation.presenters.impl.MainPresenterImpl;
import com.kodelabs.mycosts.presentation.ui.adapters.CostItemAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.codetail.widget.RevealFrameLayout;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @Bind(R.id.expenses_list)
    RecyclerView mRecyclerView;

    @Bind(R.id.reveal_layout)
    RevealFrameLayout mRevealLayout;

//    @Bind(R.id.reveal_view)
//    RecyclerView mRevealView;

    private MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterReveal();
            }
        });

        mMainPresenter = new MainPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), this);
    }


    private void enterReveal() {
        Timber.d("FAB clicked!");

        // get the center for the clipping circle
        int cx = mRecyclerView.getWidth() / 2;
        int cy = mRecyclerView.getHeight() / 2;


        // get the final radius for the clipping circle
        int finalRadius = Math.max(mRecyclerView.getWidth(), mRecyclerView.getHeight());

        Timber.w(String.valueOf(cx) + " " + String.valueOf(cy) + " " + String.valueOf(finalRadius));

        // create the animator for this view (the start radius is zero)
//                SupportAnimator anim =
//                        io.codetail.animation.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, finalRadius);

        Animator anim =
                ViewAnimationUtils.createCircularReveal(mRecyclerView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        mRevealLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

//                anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(1500);
        anim.start();


        // TODO SKUZI ZASTO OVO FAKING NE RADI

        Timber.d(String.valueOf(anim.isRunning()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.resume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            enterReveal();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCosts(List<Cost> costs) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CostItemAdapter(costs, this));
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

package com.kodelabs.mycosts.domain.interactors;

import com.kodelabs.mycosts.domain.executor.Executor;
import com.kodelabs.mycosts.domain.executor.MainThread;
import com.kodelabs.mycosts.domain.interactors.impl.GetCostByIdInteractorImpl;
import com.kodelabs.mycosts.domain.model.Cost;
import com.kodelabs.mycosts.domain.repository.CostRepository;
import com.kodelabs.mycosts.threading.TestMainThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.when;


/**
 * Created by dmilicic on 1/8/16.
 */
public class GetCostByIdTest {

    private       MainThread                     mMainThread;
    @Mock private Executor                       mExecutor;
    @Mock private CostRepository                 mCostRepository;
    @Mock private GetCostByIdInteractor.Callback mMockedCallback;

    private long mCostId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
        mCostId = 100; // any number will do as cost ID
    }

    @Test
    public void testCostNotFound() throws Exception {
        GetCostByIdInteractorImpl interactor = new GetCostByIdInteractorImpl(mExecutor, mMainThread, mCostId, mCostRepository, mMockedCallback);
        interactor.run();

        Mockito.verify(mCostRepository).getCostById(mCostId);
        Mockito.verifyNoMoreInteractions(mCostRepository);
        Mockito.verify(mMockedCallback).noCostFound();
    }

    @Test
    public void testCostFound() throws Exception {

        Cost dummyCost = new Cost("Category", "description", new Date(), 100.0);
        when(mCostRepository.getCostById(mCostId))
                .thenReturn(dummyCost);

        GetCostByIdInteractorImpl interactor = new GetCostByIdInteractorImpl(mExecutor, mMainThread, mCostId, mCostRepository, mMockedCallback);
        interactor.run();

        Mockito.verify(mCostRepository).getCostById(mCostId);
        Mockito.verifyNoMoreInteractions(mCostRepository);
        Mockito.verify(mMockedCallback).onCostRetrieved(dummyCost);
    }
}

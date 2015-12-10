package com.kodelabs.mycosts.domain.interactors.base;

/**
 * Common interface to every Interactor declared in the application. This interface represents a
 * execution unit for different use cases.
 * <p/>
 * By convention every interactor implementation will return the result using a Callback. That
 * callback should be executed over the UI thread.
 * <p/>
 * Created by dmilicic on 7/29/15.
 */
public interface Interactor {
    /**
     * This represents a starting point of a particular use case/interactor.
     */
    void run();

    /**
     * This method is used if for some reason an interactor should be canceled.
     */
    void cancel();

    /**
     * This method allows checking if a particular interactor is running.
     *
     * @return Returns true if the interactor is running/active, false otherwise.
     */
    boolean isRunning();

    /**
     * This represents the end of a particular use case/interactor.
     */
    void onFinished();
}

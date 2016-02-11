package com.kodelabs.mycosts.sync.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.kodelabs.mycosts.R;

/**
 * Created by dmilicic on 2/11/16.
 */
public class DummyAccountProvider {

    public static Account getDummyAccount(Context context) {
        final String ACCOUNT = "dummyaccount";
        final String ACCOUNT_TYPE = context.getString(R.string.account_type);
        final String AUTHORITY = context.getString(R.string.stub_content_authority);

        // Create the account type and default account
        return new Account(ACCOUNT, ACCOUNT_TYPE);
    }

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static boolean CreateSyncAccount(Context context) {

        Account newAccount = getDummyAccount(context);

        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        Context.ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        return accountManager.addAccountExplicitly(newAccount, null, null);
    }
}

package com.kritartha.blacklanechallenge.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public class AppUtils {

    public static void addFragment(@NonNull FragmentManager fragmentManager,
                                   @NonNull Fragment fragment, int frameId,
                                   boolean shouldAddToBackStack,
                                   String fragmentTag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, fragmentTag);
        if (shouldAddToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }
        transaction.commitAllowingStateLoss();
    }
}

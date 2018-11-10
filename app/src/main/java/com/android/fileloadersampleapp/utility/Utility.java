package com.android.fileloadersampleapp.utility;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.fileloadersampleapp.R;

public class Utility {

    public static void makeFragmentTransaction(Context context, int viewContainerId, Fragment fragment, boolean addFragment, boolean addToBackStack) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String tag = fragment.getClass().getName();
        if (addFragment) {
            fragmentTransaction.add(viewContainerId, fragment, tag);
        } else {
            fragmentTransaction.replace(viewContainerId, fragment, tag);
        }

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }

        try {
            fragmentTransaction.commit();
        } catch (Exception e) {
            if (addFragment) {
                fragmentTransaction.add(viewContainerId, fragment, tag);
                fragmentTransaction.addToBackStack(tag);
            } else {
                fragmentTransaction.replace(viewContainerId, fragment, tag);
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}

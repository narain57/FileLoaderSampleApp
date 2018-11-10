package com.android.fileloadersampleapp;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.fileloadersampleapp.ui.HomeActivity;
import com.android.fileloadersampleapp.ui.ListFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
// 2
@RunWith(RobolectricCustomRunner.class)
public class HomeActivityTest {

    private Activity activity;

    @Before
    public void setup() {
    }

    @Test
    public void startFragmentTest(){
        HomeActivity activity = Robolectric.setupActivity(HomeActivity.class);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(new ListFragment(), null);
        fragmentTransaction.commit();
    }
}

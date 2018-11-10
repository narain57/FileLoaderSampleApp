package com.android.fileloadersampleapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.fileloadersampleapp.R;
import com.android.fileloadersampleapp.utility.Utility;
import com.android.imageloader.utils.Utils;

public class HomeActivity extends AppCompatActivity implements CallBackListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        startFragment();
    }

    private void startFragment() {
        Fragment fragment = new ListFragment();
        Utility.makeFragmentTransaction(this,R.id.fragment_layout,fragment,false,false);
    }

    @Override
    public void onCallback() {

    }
}

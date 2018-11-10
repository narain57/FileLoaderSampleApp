package com.android.fileloadersampleapp;

import org.junit.Test;

import static org.junit.Assert.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.fileloadersampleapp.model.Profile;
import com.android.fileloadersampleapp.model.ProfileImage;
import com.android.fileloadersampleapp.model.User;
import com.android.fileloadersampleapp.ui.FileLoaderFragment;
import com.android.fileloadersampleapp.ui.HomeActivity;
import com.android.imageloader.builder.FileLoaderBuilder;
import com.android.imageloader.loader.MyFileLoader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricCustomRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FileLoaderFragmentTest {

    FileLoaderFragment fragment;
    private RecyclerView recyclerView;


    @Before
    public void setUp() throws Exception {
        // TODO Auto-generated method stub
        fragment = new FileLoaderFragment();
        startFragment(fragment);

    }

    public static void startFragment(Fragment fragment) {
        HomeActivity activity = Robolectric.setupActivity(HomeActivity.class);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }

    @Test
    public void testOnCreateView()  {
        assertNotNull(fragment);
    }

    @Test
    public void validateTextViewContent() {
        TextView textView = fragment.getView().findViewById(R.id.content);

        assertNotNull("TextView is null", textView);
    }

    @Test
    public void validateButtonClick() {
        Button button = fragment.getView().findViewById(R.id.load);
        TextView textView = fragment.getView().findViewById(R.id.content);
        EditText edit = fragment.getView().findViewById(R.id.content);
        edit.setText("http://pastebin.com/raw/wgkJgazE");
        button.performClick();

        assertNotNull(textView.getText().toString());
    }

}
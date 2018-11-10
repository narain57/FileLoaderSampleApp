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

import com.android.fileloadersampleapp.model.Profile;
import com.android.fileloadersampleapp.model.ProfileImage;
import com.android.fileloadersampleapp.model.User;
import com.android.fileloadersampleapp.ui.HomeActivity;

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
public class ListFragmentTest {

    com.android.fileloadersampleapp.ui.ListFragment fragment;
    private RecyclerView recyclerView;
    private HomeActivity homeActivity;


    @Before
    public void setUp() throws Exception {
        // TODO Auto-generated method stub
        fragment = new com.android.fileloadersampleapp.ui.ListFragment();
        startFragment(fragment);
        recyclerView = fragment.getView().findViewById(R.id.recycler_view);

    }

    public void startFragment(Fragment fragment) {
        homeActivity = Robolectric.setupActivity(HomeActivity.class);
        FragmentManager fragmentManager = homeActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }

    @Test
    public void testOnCreateView() throws Exception {
        assertNotNull(fragment);
    }

    @Test
    public void testRecyclerView() throws Exception {
        ArrayList<Profile> profileList = new ArrayList<>();
        Profile profile = new Profile();
        User user = new User();
        user.setUsername("abc");
        user.setId("1234");
        ProfileImage image = new ProfileImage();
        image.setImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5\\u0026q=80\\u0026fm=jpg\\u0026crop=faces\\u0026fit=crop\\u0026h=64\\u0026w=64\\u0026s=ef631d113179b3137f911a05fea56d23");
        user.setImageUrl(image);
        profile.setUser(user);
        profileList.add(profile);
        fragment.setProfileList(profileList);

    }

}
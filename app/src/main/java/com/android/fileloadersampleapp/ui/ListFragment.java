package com.android.fileloadersampleapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fileloadersampleapp.R;
import com.android.fileloadersampleapp.model.Profile;
import com.android.fileloadersampleapp.network.ProfileInteractorImpl;
import com.android.fileloadersampleapp.presenter.ProfilePresenter;
import com.android.fileloadersampleapp.presenter.ProfilePresenterImpl;
import com.android.fileloadersampleapp.ui.adapter.RecyclerViewAdapter;
import com.android.fileloadersampleapp.utility.Utility;
import com.android.fileloadersampleapp.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements MainView {

    private RecyclerViewAdapter mAdapter;
    private ProfilePresenter presenter;
    private ArrayList<Profile> profileList;
    private ProgressBar progressBar;
    private View v;
    private RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    int index =0,end =10;
    private List<Profile> subList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.list_layout, container, false);
        showSnackBar();
        return v;
    }

    private void showSnackBar() {
        CoordinatorLayout rootlayout = (CoordinatorLayout) v.findViewById(R.id.rootLayout);

        Snackbar snackbar = Snackbar
                .make(rootlayout, "Click on FAB button to load JSON/PDF files", Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initProgressBar();
        presenter = new ProfilePresenterImpl(this,new ProfileInteractorImpl());
        presenter.fetchProfileDetails();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void setProfileList(ArrayList<Profile> profiles) {
        this.profileList = profiles;
        initRecyclerView();
        mAdapter.setLoaded();
    }

    private void initRecyclerView() {
        if(recyclerView==null) {
            recyclerView = v.findViewById(R.id.recycler_view);
            mSwipeRefreshLayout = v.findViewById(R.id.swipeToRefresh);
            mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            subList = profileList.subList(index,end);
            mAdapter = new RecyclerViewAdapter(recyclerView,getActivity(),subList);
            int resId = R.anim.fallback_anim_layout;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(mAdapter);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    presenter.fetchProfileDetails();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });

            //Load JSOn/Pdf file to view the content
            FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FileLoaderFragment nextFrag= new FileLoaderFragment();
                    Utility.makeFragmentTransaction(getActivity(),R.id.fragment_layout,nextFrag,true,true);

                }
            });
            mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    end = end +10;
                    if(profileList.size() >= end) {
                        if(profileList.size()- end <10)
                            end = profileList.size();
                        subList = profileList.subList(index, end);
                        mAdapter.notifyDataSetChanged();
                    }

                }
            });
        }else{
            runLayoutAnimation(recyclerView);
        }
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.fallback_anim_layout);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong... ",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        getActivity().addContentView(relativeLayout, params);
    }
}

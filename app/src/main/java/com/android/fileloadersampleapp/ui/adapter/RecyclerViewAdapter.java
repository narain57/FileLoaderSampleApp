package com.android.fileloadersampleapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.fileloadersampleapp.R;
import com.android.fileloadersampleapp.model.Profile;
import com.android.fileloadersampleapp.ui.OnLoadMoreListener;
import com.android.imageloader.builder.FileLoaderBuilder;
import com.android.imageloader.loader.MyFileLoader;
import com.android.imageloader.utils.FileType;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private List<Profile> profileList;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private MyFileLoader loader;

    public void setLoaded() {
        isLoading = false;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, id;
        private ImageView image;
        private ImageButton cancel;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            id = view.findViewById(R.id.id);
            image =  view.findViewById(R.id.image);
            cancel = view.findViewById(R.id.cancel);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }


    public RecyclerViewAdapter(RecyclerView recyclerView , Context context , List<Profile> profileList) {
        this.profileList = profileList;
        this.context = context;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount-1 == lastVisibleItem && dy>0) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return profileList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_details, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }
    private OnLoadMoreListener onLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            final Profile profile = profileList.get(position);
            myViewHolder.name.setText(profile.getUser().getUsername());
            myViewHolder.id.setText(profile.getUser().getId());
            if(loader==null) {
                loader = new FileLoaderBuilder().setCacheLimit(Runtime.getRuntime().maxMemory() / 4).
                        setContext(context).build();
            }
            loader.load(FileType.IMAGE,profile.getUser().getImageUrl().getImage(),myViewHolder.image,null);
            myViewHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loader.cancel(profile.getUser().getImageUrl().getImage());
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return Math.min(profileList.size(), 10);
    }
}

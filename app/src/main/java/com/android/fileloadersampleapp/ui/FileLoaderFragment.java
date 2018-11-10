package com.android.fileloadersampleapp.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.fileloadersampleapp.R;
import com.android.imageloader.builder.FileLoaderBuilder;
import com.android.imageloader.callback.FutureCallBack;
import com.android.imageloader.loader.MyFileLoader;
import com.android.imageloader.utils.FileType;

import static com.android.imageloader.utils.FileType.JSON;

public class FileLoaderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.load_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText url = view.findViewById(R.id.url);
        url.setText("http://pastebin.com/raw/wgkJgazE");
        final Button load = view.findViewById(R.id.load);
        final TextView content = view.findViewById(R.id.content);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFileLoader loader = new FileLoaderBuilder().setCacheLimit(Runtime.getRuntime().maxMemory() / 4).
                        setContext(getActivity()).build();
                if(!TextUtils.isEmpty(url.getText().toString()))
                    loader.load(FileType.JSON,url.getText().toString(),null, new FutureCallBack<Object>() {
                    @Override
                    public void onCompleted(Object s) {
                        content.setText(((String)s));
                    }
                });
            }
        });
    }
}

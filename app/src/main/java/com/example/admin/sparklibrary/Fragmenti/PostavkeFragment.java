package com.example.admin.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.sparklibrary.R;

/**
 * Created by Admin on 24.05.2017..
 */

public class PostavkeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_postavke, container, false);

        return v;
    }

    public static Fragment getInstance() {
        return new PostavkeFragment();
    }

}

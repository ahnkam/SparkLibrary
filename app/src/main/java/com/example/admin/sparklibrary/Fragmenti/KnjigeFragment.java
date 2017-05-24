package com.example.admin.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.sparklibrary.Adapteri.KnjigeAdapter;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.List;

/**
 * Created by Admin on 24.05.2017..
 */

public class KnjigeFragment extends Fragment {
    RecyclerView rvKnjige;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_knjige, container, false);

        rvKnjige = (RecyclerView) v.findViewById(R.id.rvKnjige);

        List<KnjigeViewModel> knjige = KnjigeKontroler.SelectKnjige(getContext());
        KnjigeAdapter adapter = new KnjigeAdapter(getContext(), knjige);
        rvKnjige.setAdapter(adapter);
        rvKnjige.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    public static Fragment getInstance() {
        return new KnjigeFragment();
    }
}

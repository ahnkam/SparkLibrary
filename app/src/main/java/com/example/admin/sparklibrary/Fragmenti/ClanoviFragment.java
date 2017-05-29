package com.example.admin.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.sparklibrary.Adapteri.ClanoviAdapter;
import com.example.admin.sparklibrary.EvidencijaClanaActivity;
import com.example.admin.sparklibrary.Kontroleri.ClanoviKontroler;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.ClanoviViewModel;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.List;

/**
 * Created by Admin on 24.05.2017..
 */

public class ClanoviFragment extends Fragment implements ClanoviAdapter.IClanClick, View.OnCreateContextMenuListener {
    private RecyclerView rvClanovi;
    private ClanoviAdapter adapter;
    FloatingActionButton fabAddClan;
    private List<ClanoviViewModel> clanovi;
    private ClanoviViewModel longClickItemClan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_clanovi, container, false);
        setHasOptionsMenu(true);

        rvClanovi = (RecyclerView) v.findViewById(R.id.rvClanovi);


        clanovi = ClanoviKontroler.SelectClanovi(getContext());
        adapter = new ClanoviAdapter(clanovi, this);
        rvClanovi.setAdapter(adapter);
        rvClanovi.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration decoration = new DividerItemDecoration(rvClanovi.getContext(),
                DividerItemDecoration.VERTICAL);
        rvClanovi.addItemDecoration(decoration);

        fabAddClan = (FloatingActionButton) v.findViewById(R.id.fabAddClan);
        fabAddClan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnFabAddClanClick();
            }
        });
        rvClanovi.setOnCreateContextMenuListener(this);

        return v;
    }

    private void doBtnFabAddClanClick() {
        startActivity(EvidencijaClanaActivity.getInstance(getContext()));
    }


    @Override
    public void onResume() {
        super.onResume();
        clanovi.clear();
        clanovi.addAll(ClanoviKontroler.SelectClanovi(getContext()));
        adapter.updateData(clanovi);
    }

    public static Fragment getInstance() {
        return new ClanoviFragment();
    }


    //recycle view click
    @Override
    public void onClanLongClick(ClanoviViewModel clan) {
        longClickItemClan = clan;
    }

    @Override
    public void onClanClick(ClanoviViewModel clan) {

    }

    //context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        for (int i = 0; i < clanovi.size(); i++) {
            if (clanovi.get(i).getClanID() == longClickItemClan.getClanID())
                position = i;
        }
        if (position == -1)
            return false;
        switch (item.getItemId()) {
            case R.id.contextMenuDelete:
                for (KnjigeViewModel k : longClickItemClan.getPosudjeneKnjige()) {
                    KnjigeKontroler.VratiPosudjenuKnjigu(getContext(), k);
                }
                ClanoviKontroler.DeleteClanovi(longClickItemClan.getClanID(), getContext());
                clanovi.remove(position);
                adapter.updateData(clanovi);

                return true;
            case R.id.contextMenuEdit:
                return true;
        }

        return super.onContextItemSelected(item);
    }
}

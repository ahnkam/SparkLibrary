package com.example.admin.sparklibrary.Fragmenti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.sparklibrary.Adapteri.KnjigeAdapter;
import com.example.admin.sparklibrary.Dialogs.VratiPosudjenuKnjiguDialog;
import com.example.admin.sparklibrary.EvidencijaKnjigaActivity;
import com.example.admin.sparklibrary.Dialogs.FilterBooksDialog;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.PosudjivanjeKnjiga;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class KnjigeFragment extends Fragment implements FilterBooksDialog.IFilterSelected, KnjigeAdapter.IKnjigeClick, View.OnCreateContextMenuListener, VratiPosudjenuKnjiguDialog.IVratiPosudjenuKnjiguDialog {
    RecyclerView rvKnjige;
    FloatingActionButton fabAddKnjige;
    private List<KnjigeViewModel> knjige;
    private KnjigeAdapter adapter;

    private int longKnjigaKlikSelected = -1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_knjige, container, false);
        setHasOptionsMenu(true);

        rvKnjige = (RecyclerView) v.findViewById(R.id.rvKnjige);
        fabAddKnjige = (FloatingActionButton) v.findViewById(R.id.fabAddKnjige);
        knjige = new ArrayList<>();
        knjige.addAll(KnjigeKontroler.SelectKnjige(getContext()));
        adapter = new KnjigeAdapter(getContext(), knjige, this);
        rvKnjige.setAdapter(adapter);
        rvKnjige.setLayoutManager(new LinearLayoutManager(getContext()));
        rvKnjige.setOnCreateContextMenuListener(this);
        fabAddKnjige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnFabAddKnjige();
            }
        });
        return v;
    }

    private void doBtnFabAddKnjige() {
        startActivity(EvidencijaKnjigaActivity.getInstance(getContext(), null));
    }

    public static Fragment getInstance() {
        return new KnjigeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        knjige.clear();
        knjige.addAll(KnjigeKontroler.SelectKnjige(getContext()));
        adapter.updateData(knjige);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuFilterBook:
                DialogFragment f = FilterBooksDialog.getInstance(getContext());
                f.setTargetFragment(this, 0);
                f.show(getChildFragmentManager(), null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void filterItems(String s) {
        adapter.getFilter().filter(s);
    }

    //
    @Override
    public void onKnjigaLongClick(int position) {
        longKnjigaKlikSelected = position;
    }

    @Override
    public void onKnjigaClick(int position) {
        if (knjige.get(position).isIznajmljena()) {
            DialogFragment f = VratiPosudjenuKnjiguDialog.getInstance(knjige.get(position));
            f.setTargetFragment(this, 0);
            f.show(getChildFragmentManager(), null);
        } else
            startActivityForResult(PosudjivanjeKnjiga.getInstance(getContext(), knjige.get(position)), PosudjivanjeKnjiga.REQUEST_CODE);
    }

    //kreiranje context menia

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (longKnjigaKlikSelected == -1)
            return false;
        switch (item.getItemId()) {
            case R.id.contextMenuDelete:
                KnjigeKontroler.DeleteKnjige(getContext(), knjige.get(longKnjigaKlikSelected).getKnjigaID());
                knjige.remove(longKnjigaKlikSelected);
                adapter.updateData(knjige);
                return true;
            case R.id.contextMenuEdit:
                startActivity(EvidencijaKnjigaActivity.getInstance(getContext(), knjige.get(longKnjigaKlikSelected)));
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PosudjivanjeKnjiga.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bundle b = data.getExtras();
            Clan c = b.getParcelable(PosudjivanjeKnjiga.KEY_PARCELABLE_CLAN);
            KnjigeViewModel k = b.getParcelable(PosudjivanjeKnjiga.KEY_PARCELABLE_KNJIGA);
            createSnackBar("Knjiga " + k.getNaslov() + " posudjena korisniku " + c.getIme() + " " + c.getPrezime());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createSnackBar(String message) {
        //"Knjiga {naslov} posuđena korisniku {ime prezime}",
        Snackbar snackbar = Snackbar
                .make(rvKnjige, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void VratiPosudjenuDialogOkClicked(KnjigeViewModel knjiga) {
        //„Vraćena je knjiga {naslov}“
        for (int i = 0; i < knjige.size(); ++i)
            if (knjige.get(i).getKnjigaID() == knjiga.getKnjigaID())
                knjige.get(i).setIznajmljena(false);
        adapter.updateData(knjige);

        createSnackBar("Vracena je knjige " + knjiga.getNaslov());
    }
}

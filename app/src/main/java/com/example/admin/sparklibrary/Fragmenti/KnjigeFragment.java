package com.example.admin.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sparklibrary.Adapteri.KnjigeAdapter;
import com.example.admin.sparklibrary.AddBookActivity;
import com.example.admin.sparklibrary.Dialogs.FilterBooksDialog;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.ArrayList;
import java.util.List;


public class KnjigeFragment extends Fragment implements FilterBooksDialog.IFilterSelected, KnjigeAdapter.IKnjigeClick, View.OnCreateContextMenuListener {
    RecyclerView rvKnjige;
    FloatingActionButton fabAddKnjige;
    private List<KnjigeViewModel> knjige;
    private KnjigeAdapter adapter;

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
        startActivity(AddBookActivity.getInstance(getContext()));
    }

    public static Fragment getInstance() {
        return new KnjigeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.updateData(KnjigeKontroler.SelectKnjige(getContext()));
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
        Toast.makeText(getContext(), "Test " + position, Toast.LENGTH_SHORT).show();

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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.contextMenuDelete:
                int knjigaId = knjige.get(info.position).getKnjigaID();
                KnjigeKontroler.DeleteKnjige(getContext(), knjigaId);
                knjige.remove(info.position);
                adapter.updateData(knjige);
                return true;
            case R.id.contextMenuEdit:
                return true;
        }
        return super.onContextItemSelected(item);
    }
}

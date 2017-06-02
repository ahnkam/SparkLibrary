package com.example.admin.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.sparklibrary.Adapteri.PosudjeneKnjigeAdapter;
import com.example.admin.sparklibrary.Dialogs.VratiPosudjenuKnjiguDialog;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.ClanoviKnjigeViewModel;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 24.05.2017..
 */

public class PosudjeneKnjigeFragment extends Fragment implements PosudjeneKnjigeAdapter.IPosudjeneKnjigeClicked, VratiPosudjenuKnjiguDialog.IVratiPosudjenuKnjiguDialog {

    private List<ClanoviKnjigeViewModel> clanoviKnjigeViewModels;
    private RecyclerView rvPosudjeneKnjige;
    private PosudjeneKnjigeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_posudjene_knjige, container, false);

        clanoviKnjigeViewModels = KnjigeKontroler.SelectPosudjeneKnjige(getContext());
        rvPosudjeneKnjige = (RecyclerView) v.findViewById(R.id.rvPosudjeneKnjige);
        adapter = new PosudjeneKnjigeAdapter(clanoviKnjigeViewModels, this);
        rvPosudjeneKnjige.setAdapter(adapter);
        rvPosudjeneKnjige.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    public static Fragment getInstance() {
        return new PosudjeneKnjigeFragment();
    }

    @Override
    public void OnPosudjeneKnjigeItemClick(int position) {
        ClanoviKnjigeViewModel ck = clanoviKnjigeViewModels.get(position);
        KnjigeViewModel kvm = new KnjigeViewModel(ck.getKnjigaID());
        DialogFragment df = VratiPosudjenuKnjiguDialog.getInstance(kvm);
        df.setTargetFragment(this, 0);
        df.show(getFragmentManager(), null);
    }

    @Override
    public void VratiPosudjenuDialogOkClicked(KnjigeViewModel knjiga) {
        int position = -1;
        for (int i = 0; i < clanoviKnjigeViewModels.size(); i++)
            if (clanoviKnjigeViewModels.get(i).getKnjigaID() == knjiga.getKnjigaID())
                position = i;
        clanoviKnjigeViewModels.remove(position);
        adapter.updateData(clanoviKnjigeViewModels);
    }
}

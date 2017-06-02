package com.example.admin.sparklibrary.Adapteri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.ClanoviKnjigeViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PosudjeneKnjigeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IPosudjeneKnjigeClicked callback;
    private List<ClanoviKnjigeViewModel> clanoviKnjige;

    public interface IPosudjeneKnjigeClicked {
        void OnPosudjeneKnjigeItemClick(int position);
    }

    public void updateData(List<ClanoviKnjigeViewModel> newData) {
        clanoviKnjige.clear();
        clanoviKnjige.addAll(newData);
        Collections.sort(clanoviKnjige);
        notifyDataSetChanged();
    }

    public PosudjeneKnjigeAdapter(List<ClanoviKnjigeViewModel> clanoviKnjigeViewModel, IPosudjeneKnjigeClicked callback) {
        this.callback = callback;
        clanoviKnjige = new ArrayList<>(clanoviKnjigeViewModel);

        Collections.sort(clanoviKnjige);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_posudjene_knjige, parent, false);
        return new PosudjeneKnjigeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PosudjeneKnjigeViewHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return clanoviKnjige.size();
    }

    private class PosudjeneKnjigeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvNaslovKnjige;
        private TextView tvImePrezimeClana;
        private TextView tvClanskiBroj;
        private TextView tvDatumIznajmljivanja;
        private int position;

        PosudjeneKnjigeViewHolder(View itemView) {
            super(itemView);

            tvNaslovKnjige = (TextView) itemView.findViewById(R.id.tvNaslovKnjige);
            tvImePrezimeClana = (TextView) itemView.findViewById(R.id.tvImePrezimeClana);
            tvClanskiBroj = (TextView) itemView.findViewById(R.id.tvClanskiBroj);
            tvDatumIznajmljivanja = (TextView) itemView.findViewById(R.id.tvDatumIznajmljivanja);
            itemView.setOnClickListener(this);
        }

        void bindData(int position) {
            this.position = position;
            ClanoviKnjigeViewModel c = clanoviKnjige.get(position);
            tvNaslovKnjige.setText(c.getNaslov());
            tvImePrezimeClana.setText(c.getImePrezime());
            tvClanskiBroj.setText(c.getClanskiBroj());

            tvDatumIznajmljivanja.setText(c.getDatumPosudjivanja().toString());

        }

        @Override
        public void onClick(View v) {
            callback.OnPosudjeneKnjigeItemClick(position);
        }
    }
}

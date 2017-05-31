package com.example.admin.sparklibrary.Adapteri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.ClanoviViewModel;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Admin on 29.05.2017..
 */

public class ClanoviAdapter extends RecyclerView.Adapter<ClanoviAdapter.ClanoviViewHolder> {

    public void updateData(List<ClanoviViewModel> clanovi) {
        this.clanovi.clear();
        this.clanovi.addAll(clanovi);
        Collections.sort(this.clanovi);
        notifyDataSetChanged();
    }

    public interface IClanClick {
        void onClanLongClick(ClanoviViewModel clan);

        void onClanClick(ClanoviViewModel clan);
    }

    private IClanClick callback;
    private List<ClanoviViewModel> clanovi;

    public ClanoviAdapter(List<ClanoviViewModel> clanovi, IClanClick c) {
        callback = c;
        this.clanovi = new ArrayList<>(clanovi);
        Collections.sort(this.clanovi);


    }

    @Override
    public ClanoviViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_clanovi, parent, false);
        return new ClanoviViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return clanovi.size();
    }

    @Override
    public void onBindViewHolder(ClanoviViewHolder holder, int position) {
        holder.bindItem(position);
    }

    public class ClanoviViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tvItemClanoviImePrezime;
        TextView tvItemClanoviClanskiBroj;
        TextView tvItemClanoviBrojPosudjenihKnjige;
        private int position;

        public ClanoviViewHolder(View itemView) {
            super(itemView);
            tvItemClanoviClanskiBroj = (TextView) itemView.findViewById(R.id.tvItemClanoviClanskiBroj);
            tvItemClanoviImePrezime = (TextView) itemView.findViewById(R.id.tvItemClanoviImePrezime);
            tvItemClanoviBrojPosudjenihKnjige = (TextView) itemView.findViewById(R.id.tvListItemClanoviPosudjeneKnjige);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void bindItem(int position) {
            this.position = position;
            ClanoviViewModel clan = clanovi.get(position);
            tvItemClanoviImePrezime.setText(clan.getIme() + " " + clan.getPrezime());
            tvItemClanoviClanskiBroj.setText(clan.getClanskiBroj());
            tvItemClanoviBrojPosudjenihKnjige.setText(clan.getPosudjeneKnjige().size() + "");
        }

        @Override
        public void onClick(View v) {
            callback.onClanClick(clanovi.get(position));
        }

        @Override
        public boolean onLongClick(View v) {
            callback.onClanLongClick(clanovi.get(position));
            v.showContextMenu();
            return true;
        }
    }
}
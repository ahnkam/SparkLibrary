package com.example.admin.sparklibrary.Adapteri;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.ClanoviViewModel;

/**
 * Created by Admin on 31.05.2017..
 */

public class ClanDetaljiAdapter extends KnjigeAdapter {
    ClanoviViewModel clan;

    public ClanDetaljiAdapter(Context ctx, ClanoviViewModel clan, IKnjigeClick callback) {
        super(ctx, clan.getPosudjeneKnjige(), callback);
        this.clan = clan;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_clan_detalji, parent, false);
            return new ClanDetailsViewHolder(v);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0)
            ((ClanDetailsViewHolder) holder).bindData(position);
        else
            super.onBindViewHolder(holder, position - 1);
    }

    public int getItemCount() {
        return clan.getPosudjeneKnjige().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ClanDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView liClanImePrezime;
        TextView liClanClanskiBroj;
        TextView liClanBrojTelefona;
        TextView liClanAdresa;

        public ClanDetailsViewHolder(View itemView) {
            super(itemView);
            liClanImePrezime = (TextView) itemView.findViewById(R.id.liClanDetaljiImePrezime);
            liClanClanskiBroj = (TextView) itemView.findViewById(R.id.liClanDetaljiClanskiBroj);
            ;
            liClanBrojTelefona = (TextView) itemView.findViewById(R.id.liClanDetaljiBrojTelefona);
            liClanAdresa = (TextView) itemView.findViewById(R.id.liClanDetaljiAdresa);
        }

        public void bindData(int position) {
            liClanImePrezime.setText(clan.getImePrezime());
            liClanClanskiBroj.setText(clan.getClanskiBroj());
            liClanBrojTelefona.setText(clan.getBrojTelefona());
            liClanAdresa.setText(clan.getAdresa());
        }
    }
}

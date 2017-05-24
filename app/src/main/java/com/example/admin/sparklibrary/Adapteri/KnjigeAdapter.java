package com.example.admin.sparklibrary.Adapteri;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.List;

/**
 * Created by Admin on 24.05.2017..
 */

public class KnjigeAdapter extends RecyclerView.Adapter<KnjigeAdapter.KnjigeViewHolder> {

    Context ctx;
    List<KnjigeViewModel> knjige;

    public KnjigeAdapter(Context ctx, List<KnjigeViewModel> knjige) {
        this.ctx = ctx;
        this.knjige = knjige;
    }

    @Override
    public KnjigeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_item_knjige, parent, false);
        return new KnjigeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KnjigeViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return knjige.size();
    }

    public class KnjigeViewHolder extends RecyclerView.ViewHolder {
        TextView tvNaslovKnjige;
        TextView tvNazivAutora;
        TextView tvNazivKlasifikacije;
        TextView tvGodinaIzdanja;
        ImageView ivIsAvailable;

        public KnjigeViewHolder(View itemView) {
            super(itemView);

            tvNaslovKnjige = (TextView) itemView.findViewById(R.id.tvKnjigaNaslov);
            tvNazivAutora = (TextView) itemView.findViewById(R.id.tvKnjigaAutorIme);
            tvNazivKlasifikacije = (TextView) itemView.findViewById(R.id.tvKnjigaAutorKlasifikacijaNaziv);
            tvGodinaIzdanja = (TextView) itemView.findViewById(R.id.tvKnjigaGodinaIzdanja);

            ivIsAvailable = (ImageView) itemView.findViewById(R.id.ivKnjigaIsIznajmljena);
        }

        public void bindData(int position) {
            tvNaslovKnjige.setText(knjige.get(position).getNaslov());
            tvNazivAutora.setText(knjige.get(position).getImeAutora());
            tvNazivKlasifikacije.setText(knjige.get(position).getNazivKlasifikacije());
            tvGodinaIzdanja.setText(knjige.get(position).getGodinaIzdanja() + "");

            if (knjige.get(position).isIznajmljena())
                ivIsAvailable.setImageResource(R.drawable.ic_knjiga_not_available);
            else
                ivIsAvailable.setImageResource(R.drawable.ic_knjiga_available);

        }
    }
}

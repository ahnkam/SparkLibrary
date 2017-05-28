package com.example.admin.sparklibrary.Adapteri;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 24.05.2017..
 */

public class KnjigeAdapter extends RecyclerView.Adapter<KnjigeAdapter.KnjigeViewHolder> implements Filterable {

    public interface IKnjigeClick {
        void onKnjigaLongClick(int position);

        void onKnjigaClick(int position);
    }
    Context ctx;
    List<KnjigeViewModel> knjige;
    List<KnjigeViewModel> filteredKnjige;
    private ItemFilter mFilter = new ItemFilter();
    IKnjigeClick callback;

    public KnjigeAdapter(Context ctx, List<KnjigeViewModel> knjige, IKnjigeClick callback) {
        this.ctx = ctx;
        this.knjige = new ArrayList<>();
        this.filteredKnjige = new ArrayList<>();
        //nije puno samo 2 sata na ovome.. SAMO POINTER BAJO MOJ
        this.knjige.addAll(knjige);
        this.filteredKnjige.addAll(knjige);
        this.callback = callback;

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
        return filteredKnjige.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public void updateData(List<KnjigeViewModel> knjigeViewModels) {
        knjige.clear();
        filteredKnjige.clear();
        knjige.addAll(knjigeViewModels);
        filteredKnjige.addAll(knjigeViewModels);
        this.notifyDataSetChanged();
    }

    public class KnjigeViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        TextView tvNaslovKnjige;
        TextView tvNazivAutora;
        TextView tvNazivKlasifikacije;
        TextView tvGodinaIzdanja;
        ImageView ivIsAvailable;
        int position;

        public KnjigeViewHolder(View itemView) {
            super(itemView);
            tvNaslovKnjige = (TextView) itemView.findViewById(R.id.tvKnjigaNaslov);
            tvNazivAutora = (TextView) itemView.findViewById(R.id.tvKnjigaAutorIme);
            tvNazivKlasifikacije = (TextView) itemView.findViewById(R.id.tvKnjigaAutorKlasifikacijaNaziv);
            tvGodinaIzdanja = (TextView) itemView.findViewById(R.id.tvKnjigaGodinaIzdanja);
            ivIsAvailable = (ImageView) itemView.findViewById(R.id.ivKnjigaIsIznajmljena);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);

        }

        public void bindData(int position) {
            this.position = position;
            tvNaslovKnjige.setText(filteredKnjige.get(position).getNaslov());
            tvNazivAutora.setText(filteredKnjige.get(position).getImeAutora());
            tvNazivKlasifikacije.setText(filteredKnjige.get(position).getNazivKlasifikacije());
            tvGodinaIzdanja.setText(filteredKnjige.get(position).getGodinaIzdanja() + "");

            if (filteredKnjige.get(position).isIznajmljena())
                ivIsAvailable.setImageResource(R.mipmap.ic_not_available);
            else
                ivIsAvailable.setImageResource(R.mipmap.ic_available);

        }

        @Override
        public boolean onLongClick(View v) {

            callback.onKnjigaLongClick(position);
//            v.showContextMenu();
            return false;
        }


        @Override
        public void onClick(View v) {
            callback.onKnjigaClick(position);
        }
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.toString().isEmpty() || constraint.toString().length() == 0) {
                results.values = knjige;
                return results;
            }
            String filterString = constraint.toString().toLowerCase();


            ;

            int count = knjige.size();
            ArrayList<KnjigeViewModel> resultList = new ArrayList<>();

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = knjige.get(i).getNazivKlasifikacije();
                if (filterableString.toLowerCase().contains(filterString)) {
                    resultList.add(knjige.get(i));
                }
            }

            results.values = resultList;
            filteredKnjige.clear();
            filteredKnjige.addAll(resultList);

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredKnjige.clear();
            //BAJO MOJ I OVO TI JE UKLJUCENO U ONIH 2 sata
            filteredKnjige.addAll((Collection<KnjigeViewModel>) results.values);

            notifyDataSetChanged();
        }

    }
}

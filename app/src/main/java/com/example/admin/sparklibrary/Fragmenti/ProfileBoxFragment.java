package com.example.admin.sparklibrary.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.sparklibrary.Config.Sesija;
import com.example.admin.sparklibrary.Model.Korisnik;
import com.example.admin.sparklibrary.R;

/**
 * Created by Admin on 24.05.2017..
 */

public class ProfileBoxFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_box, container, false);
        Korisnik logiraniKorisnik = Sesija.getLogiraniKorisnik(getContext());
        TextView tvKorisnikImePrezime = (TextView) v.findViewById(R.id.tvKorisnikImePrezime);
        TextView tvKorisnikKorisnickoIme = (TextView) v.findViewById(R.id.tvKorisnikKorisnickoIme);

        tvKorisnikImePrezime.setText(logiraniKorisnik.getImePrezime());
        tvKorisnikKorisnickoIme.setText(logiraniKorisnik.getKorisnickoIme());

        return v;
    }

    public static Fragment getInstance() {
        return new ProfileBoxFragment();
    }

}

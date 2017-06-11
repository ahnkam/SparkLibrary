package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.sparklibrary.Config.Sesija;
import com.example.admin.sparklibrary.Kontroleri.ClanoviKontroler;
import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.Model.Korisnik;

public class EvidencijaClanaActivity extends AppCompatActivity {

    EditText etEvidencijaClanIme;
    EditText etEvidencijaClanPrezime;
    EditText etEvidencijaClanAdresa;
    EditText etEvidencijaClanBrojTelefona;
    EditText etEvidencijaClanClanskiBroj;
    Button btnSaveClan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidencija_clana);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        etEvidencijaClanIme = (EditText) findViewById(R.id.etEvidencijaClanIme);
        etEvidencijaClanPrezime = (EditText) findViewById(R.id.etEvidencijaClanPrezime);
        etEvidencijaClanAdresa = (EditText) findViewById(R.id.etEvidencijaClanAdresa);
        etEvidencijaClanBrojTelefona = (EditText) findViewById(R.id.etEvidencijaClanBrojTelefona);
        etEvidencijaClanClanskiBroj = (EditText) findViewById(R.id.etEvidencijaClanClanskiBroj);
        etEvidencijaClanClanskiBroj.setText(ClanoviKontroler.generateNewID(this));

        btnSaveClan = (Button) findViewById(R.id.btnSaveClan);
        btnSaveClan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnSaveClan();
            }
        });
    }

    private void doBtnSaveClan() {
        if (!validateIme() || !validatePrezime() || !validateAdresa() || !validateBrojTelefona())
            return;

        Korisnik k = Sesija.getLogiraniKorisnik(this);
        Clan clan = new Clan
                (-1, etEvidencijaClanIme.getText().toString(),
                        etEvidencijaClanPrezime.getText().toString(),
                        etEvidencijaClanAdresa.getText().toString(),
                        etEvidencijaClanBrojTelefona.getText().toString(),
                        etEvidencijaClanClanskiBroj.getText().toString(),
                        k.getKorisnikID());
        ClanoviKontroler.InsertClanovi(clan, this);

    }

    private boolean validateBrojTelefona() {
        if (etEvidencijaClanBrojTelefona.getText().toString().isEmpty()) {
            etEvidencijaClanBrojTelefona.setError(getResources().getString(R.string.evidencijaClanBrojTelefonaError));
            return false;
        }
        return true;
    }

    private boolean validateAdresa() {
        if (etEvidencijaClanAdresa.getText().toString().isEmpty()) {
            etEvidencijaClanAdresa.setError(getResources().getString(R.string.evidencijaClanAdresaError));
            return false;
        }
        return true;
    }

    private boolean validatePrezime() {
        if (etEvidencijaClanPrezime.getText().toString().isEmpty()) {
            etEvidencijaClanPrezime.setError(getResources().getString(R.string.evidencijaClanPrezimeError));
            return false;
        }
        return true;
    }

    private boolean validateIme() {
        if (etEvidencijaClanIme.getText().toString().isEmpty()) {
            etEvidencijaClanIme.setError(getResources().getString(R.string.evidencijaClanImeError));
            return false;
        }
        return true;
    }

    public static Intent getInstance(Context ctx) {
        return new Intent(ctx, EvidencijaClanaActivity.class);
    }
}

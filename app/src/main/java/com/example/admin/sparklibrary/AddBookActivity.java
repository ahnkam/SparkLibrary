package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.admin.sparklibrary.Config.Sesija;
import com.example.admin.sparklibrary.Dialogs.AddKlasifikacijaDialog;
import com.example.admin.sparklibrary.Fragmenti.KnjigeFragment;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Klasifikacija;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.Model.Korisnik;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity implements AddKlasifikacijaDialog.IAddKlasifikacija {

    private EditText etAddBookNaziv;
    private EditText etAddBookNaklada;
    private EditText etAddBookAutorIme;
    private Spinner spAddBookKlasifikacija;
    private EditText etAddBookGodinaIzdanja;
    private EditText etAddBookBrojStranica;
    private TextView tvAddBookWarning;
    private Button btnAddBook;


    private ImageView ivAddKlasifikacija;

    private List<Klasifikacija> klasifikacije;
    private List<String> klasifikacijeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);


        etAddBookNaziv = (EditText) findViewById(R.id.etAddBookTitle);
        etAddBookNaklada = (EditText) findViewById(R.id.etAddBookNaklada);
        etAddBookAutorIme = (EditText) findViewById(R.id.etAddBookAutorIme);
        spAddBookKlasifikacija = (Spinner) findViewById(R.id.spAddBookKlasifikacija);
        etAddBookGodinaIzdanja = (EditText) findViewById(R.id.etAddBookGodinaIzdanja);
        etAddBookBrojStranica = (EditText) findViewById(R.id.etAddBookBrojStranica);
        tvAddBookWarning = (TextView) findViewById(R.id.tvAddBookWarning);
        btnAddBook = (Button) findViewById(R.id.btnAddBook);

        //postavljanje vrijednosti u spinner


        ivAddKlasifikacija = (ImageView) findViewById(R.id.ivAddKlasifikacija);

        setupKlasifikacijeSpinner();


        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnAddBook();
            }
        });
        ivAddKlasifikacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnAddKlasifikacija();
            }
        });

    }

    @NonNull
    private void setupKlasifikacijeSpinner() {
        klasifikacije = KnjigeKontroler.SelectKlasifikacijeKnjiga(this);
        klasifikacijeValue = new ArrayList<>();
        for (Klasifikacija k : klasifikacije) {
            klasifikacijeValue.add(k.getNaziv());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, klasifikacijeValue);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddBookKlasifikacija.setAdapter(adapter);

    }


    private void doBtnAddKlasifikacija() {
        AddKlasifikacijaDialog.getInstance(klasifikacijeValue).show(getSupportFragmentManager(), null);
    }

    private void doBtnAddBook() {


        String naziv = etAddBookNaziv.getText().toString();
        String naklada = etAddBookNaklada.getText().toString();
        String autorIme = etAddBookAutorIme.getText().toString();
        int klasifikacijaID = klasifikacije.get(spAddBookKlasifikacija.getSelectedItemPosition()).getKlasifikacijaID();
        int godinaIzdanja = Integer.parseInt(etAddBookGodinaIzdanja.getText().toString());
        int brojStranica = Integer.parseInt(etAddBookBrojStranica.getText().toString());
        tvAddBookWarning.setText("");
        if (naziv.isEmpty() || naklada.isEmpty() || autorIme.isEmpty() || klasifikacijaID == 0 || godinaIzdanja == 0 || brojStranica == 0) {
            tvAddBookWarning.setText(getResources().getString(R.string.AddBookWarning));
            return;
        }

        Korisnik k = Sesija.getLogiraniKorisnik(this);
        Knjige knjiga = new Knjige(naziv, naklada, autorIme, godinaIzdanja, brojStranica, klasifikacijaID, k.getKorisnikID());


        if (KnjigeKontroler.InsertKnjige(knjiga, this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.Success));
            builder.setMessage(getResources().getString(R.string.AddBookSuccesTitleMessage)).setPositiveButton("OK", null);
            builder.show();
            etAddBookNaziv.setText("");
            etAddBookNaklada.setText("");
            etAddBookAutorIme.setText("");
            etAddBookGodinaIzdanja.setText("");
            etAddBookBrojStranica.setText("");

        } else {
            tvAddBookWarning.setText(getResources().getString(R.string.Error));
        }
    }

    public static Intent getInstance(Context ctx) {
        return new Intent(ctx, AddBookActivity.class);
    }

    @Override
    public void KlasifikacijaInserted() {
        setupKlasifikacijeSpinner();
        spAddBookKlasifikacija.setSelection(klasifikacijeValue.size() - 1);
    }
}


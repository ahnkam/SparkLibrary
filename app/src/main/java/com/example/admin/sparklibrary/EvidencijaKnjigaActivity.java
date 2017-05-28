package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.sparklibrary.Config.Sesija;
import com.example.admin.sparklibrary.Dialogs.AddKlasifikacijaDialog;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Klasifikacija;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.Model.Korisnik;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.ArrayList;
import java.util.List;

public class EvidencijaKnjigaActivity extends AppCompatActivity implements AddKlasifikacijaDialog.IAddKlasifikacija {

    private static final String KEY_PARCERABLE_KNJIGA = "KEY_PARCELABLE_KNJIGA";
    private EditText etAddBookNaziv;
    private EditText etAddBookNaklada;
    private EditText etAddBookAutorIme;
    private Spinner spAddBookKlasifikacija;
    private EditText etAddBookGodinaIzdanja;
    private EditText etAddBookBrojStranica;
    private TextView tvAddBookWarning;
    private Button btnAddBook;
    private KnjigeViewModel knjigeViewModel;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
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

        knjigeViewModel = getIntent().getParcelableExtra(KEY_PARCERABLE_KNJIGA);
        if (knjigeViewModel != null)
            setupEditBook();

    }

    private void setupEditBook() {
        etAddBookNaziv.setText(knjigeViewModel.getNaslov());
        etAddBookNaklada.setText(knjigeViewModel.getNaklada());
        etAddBookAutorIme.setText(knjigeViewModel.getImeAutora());
        int position = 0;
        for (int i = 0; i < klasifikacije.size(); ++i) {
            if (klasifikacije.get(i).getKlasifikacijaID() == knjigeViewModel.getKlasifikacijaID())
                position = i;
        }
        spAddBookKlasifikacija.setSelection(position);
        etAddBookGodinaIzdanja.setText(knjigeViewModel.getGodinaIzdanja() + "");
        etAddBookBrojStranica.setText(knjigeViewModel.getBrojStranica() + "");
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
        String godinaIzdanjaIDString = etAddBookGodinaIzdanja.getText().toString();
        String brojStranicaString = etAddBookBrojStranica.getText().toString();

        tvAddBookWarning.setText("");
        if (naziv.isEmpty() || naklada.isEmpty() || autorIme.isEmpty() || godinaIzdanjaIDString.isEmpty() || brojStranicaString.isEmpty()) {
            tvAddBookWarning.setText(getResources().getString(R.string.AddBookWarning));
            return;
        }
        int brojStranica = Integer.parseInt(brojStranicaString);
        int godinaIzdanja = Integer.parseInt(godinaIzdanjaIDString);

        Korisnik k = Sesija.getLogiraniKorisnik(this);
        Knjige novaKnjiga = new Knjige(naziv, naklada, autorIme, godinaIzdanja, brojStranica, klasifikacijaID, k.getKorisnikID());
        //ako je knjigeViewModel razlicita od null znaci da samo iz pozivnog activitya poslali knjigu koju treba urediti
        //i tad izvrsiti samo update knjige
        if (knjigeViewModel != null) {
            novaKnjiga.setKnjigaID(knjigeViewModel.getKnjigaID());
            novaKnjiga.setIznajmljena(knjigeViewModel.isIznajmljena());
            KnjigeKontroler.UpdateKnjiga(novaKnjiga, this);
            showDialog(getResources().getString(R.string.Success), getResources().getString(R.string.BookUpdateSuccess));

        }
        //ako je knjigeViewModel null izvrsiti insert nove knjige
        else if (KnjigeKontroler.InsertKnjige(novaKnjiga, this)) {
            showDialog(getResources().getString(R.string.Success), getResources().getString(R.string.AddBookSuccesTitleMessage));
            etAddBookNaziv.setText("");
            etAddBookNaklada.setText("");
            etAddBookAutorIme.setText("");
            etAddBookGodinaIzdanja.setText("");
            etAddBookBrojStranica.setText("");

        }


    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message).setPositiveButton("OK", null);
        builder.show();
    }

    public static Intent getInstance(Context ctx, KnjigeViewModel k) {
        Bundle b = new Bundle();
        b.putParcelable(KEY_PARCERABLE_KNJIGA, k);
        Intent i = new Intent(ctx, EvidencijaKnjigaActivity.class);
        i.putExtras(b);
        return i;
    }

    @Override
    public void KlasifikacijaInserted() {
        setupKlasifikacijeSpinner();
        spAddBookKlasifikacija.setSelection(klasifikacijeValue.size() - 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}


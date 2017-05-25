package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.sparklibrary.Kontroleri.KorisniciKontroler;
import com.example.admin.sparklibrary.Model.Korisnik;

public class RegisterActivity extends AppCompatActivity {

    EditText etKorisnikIme;
    EditText etKorisnikPrezime;
    EditText etKorisnikKorisnickoIme;
    EditText etKorisnikPassword;

    Button btnRegisterSave;
    TextView tvRegisterSaveWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etKorisnikIme = (EditText) findViewById(R.id.etKorisnikIme);
        etKorisnikPrezime = (EditText) findViewById(R.id.etKorisnikPrezime);
        etKorisnikKorisnickoIme = (EditText) findViewById(R.id.etKorisnikKorisnickoIme);
        etKorisnikPassword = (EditText) findViewById(R.id.etKorisnikPassword);

        btnRegisterSave = (Button) findViewById(R.id.btnRegisterSave);
        tvRegisterSaveWarning = (TextView) findViewById(R.id.tvRegisterSaveWarning);


        btnRegisterSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnRegisterMe();
            }
        });
    }

    private void doBtnRegisterMe() {
        String ime = etKorisnikIme.getText().toString();
        String prezime = etKorisnikPrezime.getText().toString();
        String korisnickoIme = etKorisnikKorisnickoIme.getText().toString();
        String password = etKorisnikPassword.getText().toString();
        tvRegisterSaveWarning.setText("");

        if (ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty() || password.isEmpty()) {
            tvRegisterSaveWarning.setText(getResources().getString(R.string.RegisterRequiredFields));
            return;
        }

        if (KorisniciKontroler.KorisnikExists(korisnickoIme, this)) {
            tvRegisterSaveWarning.setText(getResources().getString(R.string.RegisterUserExists));
            return;
        }

        if (password.length() < 8) {
            tvRegisterSaveWarning.setText(getResources().getString(R.string.PasswordMinLength));
            return;
        }

        Korisnik k = new Korisnik(-1, ime, prezime, korisnickoIme, password);
        KorisniciKontroler.InsertKorisnici(k, this);
        showSuccesDialog(k);


    }


    public void showSuccesDialog(Korisnik k) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        StringBuilder sb = new StringBuilder();
        sb.append(k.getIme());
        sb.append(" - ");
        sb.append(k.getPrezime());
        sb.append(" - ");
        sb.append(k.getKorisnickoIme());

        sb.append(" " + getResources().getString(R.string.UserCreateSucces));
        builder.setMessage(sb.toString()).setTitle(getResources().getString(R.string.Success));
        builder.show();

    }

    public static Intent getInstance(Context ctx) {
        return new Intent(ctx, RegisterActivity.class);
    }
}

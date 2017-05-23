package com.example.admin.sparklibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.sparklibrary.Config.Sesija;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (Sesija.getLogiraniKorisnik(this) == null)
            startActivity(LoginActivity.getInstance(this));
        else
            startActivity(MainActivity.getInstance(this));
        finish();

    }
}

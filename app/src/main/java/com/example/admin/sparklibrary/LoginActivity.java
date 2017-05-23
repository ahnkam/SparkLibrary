package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.sparklibrary.Config.Sesija;
import com.example.admin.sparklibrary.Kontroleri.KorisniciKontroler;
import com.example.admin.sparklibrary.Model.Korisnik;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;

    Button btnRegister;
    Button btnLogin;

    TextView tvResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);


        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnLogin();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnRegister();
            }
        });
        tvResultText = (TextView) findViewById(R.id.tvResultText);
    }

    private void doBtnRegister() {
        startActivity(RegisterActivity.getInstance(this));
    }

    private void doBtnLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            tvResultText.setText(getResources().getString(R.string.LoginRequiredFields));
            return;
        }
        Korisnik k = KorisniciKontroler.Login(username, password, this);
        if (k != null) {
            Sesija.setLogiraniKorisnik(k, this);
            startActivity(MainActivity.getInstance(this));
            finish();
        } else {
            tvResultText.setText(getResources().getString(R.string.LoginResultWarning));
        }

    }

    public static Intent getInstance(Context ctx) {
        Intent intent = new Intent(ctx, LoginActivity.class);
        return intent;
    }
}

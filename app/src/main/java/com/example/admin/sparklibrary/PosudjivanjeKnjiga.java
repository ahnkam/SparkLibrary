package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.sparklibrary.Dialogs.PosudiKnjiguDialog;
import com.example.admin.sparklibrary.Kontroleri.ClanoviKontroler;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.Model.ClanoviKnjige;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

public class PosudjivanjeKnjiga extends AppCompatActivity implements PosudiKnjiguDialog.IPosudiKnjiguDialog {


    public static final String KEY_PARCELABLE_KNJIGA = "KEY_PARCELABLE_KNJIGA";
    public static final String KEY_PARCELABLE_CLAN = "KEY_PARCELABLE_CLAN";
    public static final int REQUEST_CODE = 1;

    private KnjigeViewModel knjigaViewModel;

    private TextView tvNaslov;
    private TextView tvAutor;
    private TextView tvNaklada;
    private TextView tvGodinaIzdanja;
    private TextView tvBrojStranica;
    private TextView tvKlasifikacijskiBroj;
    private Button btnPosudiKnjigu;
    EditText etClanskiBroj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posudjivanje_knjiga);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        knjigaViewModel = getIntent().getParcelableExtra(KEY_PARCELABLE_KNJIGA);
        tvNaslov = (TextView) findViewById(R.id.tvPosudjivanjeKnjigeNaslovKnjige);
        tvAutor = (TextView) findViewById(R.id.tvPosudjivanjeKnjigeAutor);
        tvNaklada = (TextView) findViewById(R.id.tvPosudjivanjeKnjigeNaklada);
        tvGodinaIzdanja = (TextView) findViewById(R.id.tvPosudjivanjeKnjigeGodinaIzdanja);
        tvBrojStranica = (TextView) findViewById(R.id.tvPosudjivanjeKnjigeBrojStranica);
        tvKlasifikacijskiBroj = (TextView) findViewById(R.id.tvPosudjivanjeKnjigeKlasifikacijskiBroj);
        etClanskiBroj = (EditText) findViewById(R.id.etClanskiBroj);
        btnPosudiKnjigu = (Button) findViewById(R.id.btnPosudiKnjigu);

        btnPosudiKnjigu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBtnPosudiKnjigu();
            }
        });

        setUpBookDetails();

    }

    private void doBtnPosudiKnjigu() {
        String clanskiBroj = etClanskiBroj.getText().toString();
        if (clanskiBroj.isEmpty()) {
            etClanskiBroj.setError(getResources().getString(R.string.EnterClanskiBroj));
            etClanskiBroj.requestFocus();
            return;
        }
        Clan clan = ClanoviKontroler.SelectClanByClanskiBroj(this, clanskiBroj);
        if (clan == null) {
            etClanskiBroj.setError(getResources().getString(R.string.EnterValidClanskiBroj));
            etClanskiBroj.requestFocus();
            return;
        }




     /*   KnjigeKontroler.PosudiKnjigu(this,ck);
*/
        PosudiKnjiguDialog.getInstance(clan).show(getSupportFragmentManager(), null);
    }

    private void setUpBookDetails() {
        tvNaslov.setText(knjigaViewModel.getNaslov());
        tvAutor.setText(knjigaViewModel.getImeAutora());
        tvNaklada.setText(knjigaViewModel.getNaklada());
        tvGodinaIzdanja.setText(knjigaViewModel.getGodinaIzdanja() + "");
        tvBrojStranica.setText(knjigaViewModel.getBrojStranica() + "");
        tvKlasifikacijskiBroj.setText(knjigaViewModel.getNazivKlasifikacije());
    }

    public static Intent getInstance(Context ctx, KnjigeViewModel knjigeViewModel) {
        Bundle b = new Bundle();
        b.putParcelable(KEY_PARCELABLE_KNJIGA, knjigeViewModel);
        Intent i = new Intent(ctx, PosudjivanjeKnjiga.class);
        i.putExtras(b);
        return i;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void PosudiKnjiguDialogOkClicked(Clan clan) {
        ClanoviKnjige ck = new ClanoviKnjige(clan.getClanID(), knjigaViewModel.getKnjigaID());
        KnjigeKontroler.PosudiKnjigu(this, ck);
        Intent result = new Intent();
        Bundle b = new Bundle();
        b.putParcelable(KEY_PARCELABLE_KNJIGA, knjigaViewModel);
        b.putParcelable(KEY_PARCELABLE_CLAN, clan);
        result.putExtras(b);
        setResult(RESULT_OK, result);
        finish();
    }
}

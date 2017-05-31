package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.admin.sparklibrary.Adapteri.ClanDetaljiAdapter;
import com.example.admin.sparklibrary.Adapteri.KnjigeAdapter;
import com.example.admin.sparklibrary.Dialogs.VratiPosudjenuKnjiguDialog;
import com.example.admin.sparklibrary.ViewModeli.ClanoviViewModel;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.BitSet;
import java.util.List;

public class DetaljiClanaActivity extends AppCompatActivity implements KnjigeAdapter.IKnjigeClick, VratiPosudjenuKnjiguDialog.IVratiPosudjenuKnjiguDialog {

    private static final String KEY_PARCELABLE_CLANOVI = "KEY_PARCELABLE_CLANOVI";
    private ClanoviViewModel clan;
    private RecyclerView rvKnjige;
    private KnjigeAdapter adapter;

    public static Intent getInstance(Context ctx, ClanoviViewModel clanoviViewModel) {
        Bundle b = new Bundle();
        b.putParcelable(KEY_PARCELABLE_CLANOVI, clanoviViewModel);
        Intent i = new Intent(ctx, DetaljiClanaActivity.class);
        i.putExtras(b);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_clana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        clan = getIntent().getParcelableExtra(KEY_PARCELABLE_CLANOVI);
        toolbar.setTitle(clan.getIme() + " " + clan.getPrezime());
        appBarLayout.setTitle(clan.getIme() + " " + clan.getPrezime());


        rvKnjige = (RecyclerView) findViewById(R.id.rvKnjige);
        adapter = new ClanDetaljiAdapter(this, clan, this);
        rvKnjige.setAdapter(adapter);
        rvKnjige.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onKnjigaLongClick(KnjigeViewModel knjigaSelected) {

    }

    @Override
    public void onKnjigaClick(KnjigeViewModel knjigaSelected) {

        DialogFragment f = VratiPosudjenuKnjiguDialog.getInstance(knjigaSelected);
        f.show(getSupportFragmentManager(), null);
    }

    @Override
    public void VratiPosudjenuDialogOkClicked(KnjigeViewModel knjiga) {
        List<KnjigeViewModel> knjige = clan.getPosudjeneKnjige();
        for (int i = 0; i < knjige.size(); ++i)
            if (knjige.get(i).getKnjigaID() == knjiga.getKnjigaID()) {
                knjige.remove(i);
                break;
            }
        adapter.updateData(knjige);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}

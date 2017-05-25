package com.example.admin.sparklibrary;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admin.sparklibrary.Config.Sesija;
import com.example.admin.sparklibrary.Fragmenti.ClanoviFragment;
import com.example.admin.sparklibrary.Fragmenti.KnjigeFragment;
import com.example.admin.sparklibrary.Fragmenti.PostavkeFragment;
import com.example.admin.sparklibrary.Fragmenti.PosudjeneKnjigeFragment;
import com.example.admin.sparklibrary.Fragmenti.ProfileBoxFragment;
import com.example.admin.sparklibrary.Model.Korisnik;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    private Korisnik prijavljeniKnjiznicar;
    private TextView tvKnjiznicarIme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (savedInstanceState == null)
            toolbar.setTitle(R.string.navTitleKnjige);
        setSupportActionBar(toolbar);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Find our drawer view
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setupDrawerContent(nvDrawer);
        drawerToggle = setupDrawerToggle();
        drawer.addDrawerListener(drawerToggle);

        prijavljeniKnjiznicar = Sesija.getLogiraniKorisnik(this);


        tvKnjiznicarIme = (TextView) nvDrawer.getHeaderView(0).findViewById(R.id.tvKnjiznicarIme);
        tvKnjiznicarIme.setText(prijavljeniKnjiznicar.getIme() + " " + prijavljeniKnjiznicar.getPrezime());

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmentPlaceholder, KnjigeFragment.getInstance()).commit();
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
     /*   switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }*/
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
//        Fragment fragment = null;
//        Class fragmentClass;
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_knjige:
                fragment = KnjigeFragment.getInstance();
                break;
            case R.id.nav_clanovi:
                fragment = ClanoviFragment.getInstance();
                break;
            case R.id.nav_posudjene_knjige:
                fragment = PosudjeneKnjigeFragment.getInstance();
                break;

            case R.id.nav_profile_box:
                fragment = ProfileBoxFragment.getInstance();
                break;

            case R.id.nav_postavke:
                fragment = PostavkeFragment.getInstance();
                break;

            case R.id.nav_odjavi_se:
                Sesija.setLogiraniKorisnik(null, this);
                startActivity(LoginActivity.getInstance(this));
                finish();
                break;
        }

        //TODO Postimati otvaranje fragmenta
        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmentPlaceholder, fragment).commit();
        }
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }
    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
    public static Intent getInstance(Context ctx) {
        return new Intent(ctx, MainActivity.class);
    }


}


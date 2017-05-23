package com.example.admin.sparklibrary.Config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.sparklibrary.Model.Korisnik;
import com.example.admin.sparklibrary.Model.KorisnikClanovi;
import com.google.gson.Gson;


public class Sesija {
    private static String LogiraniKorisnikSharedPreffs = "logiraniKorisnikSharredPreffs";
    private static String KorisnikKey = "KorisnikKey";

    public static void setLogiraniKorisnik(Korisnik k, Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(LogiraniKorisnikSharedPreffs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String jsonSave = gson.toJson(k);
        editor.putString(KorisnikKey, jsonSave);
        editor.apply();

    }

    public static Korisnik getLogiraniKorisnik(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(LogiraniKorisnikSharedPreffs, Context.MODE_PRIVATE);
        String korisnikJson = sharedPref.getString(KorisnikKey, null);
        Gson gson = new Gson();
        return gson.fromJson(korisnikJson, Korisnik.class);
    }
}

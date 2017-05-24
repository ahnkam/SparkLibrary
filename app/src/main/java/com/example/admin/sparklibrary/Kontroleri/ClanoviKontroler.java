package com.example.admin.sparklibrary.Kontroleri;

import android.content.Context;
import android.util.Log;

import com.example.admin.sparklibrary.DB.MojDbContext;
import com.example.admin.sparklibrary.Model.Clan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.05.2017..
 */

public class ClanoviKontroler {
    private static final String ClanoviKontrolerTag = "ClanoviKontrolerTag";

    public static boolean InsertClanovi(Clan clan, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_Clanovi_Insert(clan.getIme(), clan.getPrezime(), clan.getAdresa(),
                clan.getBrojTelefona(), clan.getClanskiBroj(), clan.getKorisnikID());
    }

    public static List<Clan> SelectClanovi(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_SelectClanovi();
    }
}


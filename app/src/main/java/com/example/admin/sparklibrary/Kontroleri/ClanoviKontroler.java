package com.example.admin.sparklibrary.Kontroleri;

import android.content.Context;
import android.util.Log;

import com.example.admin.sparklibrary.DB.MojDbContext;
import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.PosudjivanjeKnjiga;
import com.example.admin.sparklibrary.ViewModeli.ClanoviViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.05.2017..
 */

public class ClanoviKontroler {
    public static boolean InsertClanovi(Clan clan, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_Clanovi_Insert(clan.getIme(), clan.getPrezime(), clan.getAdresa(),
                clan.getBrojTelefona(), clan.getClanskiBroj(), clan.getKorisnikID());
    }

    public static List<ClanoviViewModel> SelectClanovi(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_SelectClanovi();
    }

    public static Clan SelectClanByClanskiBroj(Context ctx, String clanskiBroj) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_SelectClanByClanskiBroj(clanskiBroj);
    }

    public static String generateNewID(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        int clanID = db.usp_getLastClanID();
        clanID++;
        clanID += 170000;
        String clanskiBroj = "CB" + Integer.toString(clanID);
        return clanskiBroj;
    }

    public static void DeleteClanovi(int clanID, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);

        db.usp_DeleteClanovi(clanID);
    }
}


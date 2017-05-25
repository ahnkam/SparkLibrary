package com.example.admin.sparklibrary.Kontroleri;


import android.content.Context;

import com.example.admin.sparklibrary.DB.MojDbContext;
import com.example.admin.sparklibrary.Helper.DateTimeFormater;
import com.example.admin.sparklibrary.Model.Klasifikacija;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.List;

public class KnjigeKontroler {


    public static List<KnjigeViewModel> SelectKnjige(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);

        return db.usp_SelectKnjige();

    }


    public static boolean InsertKnjige(Knjige knjiga, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_InsertKnjige(knjiga.getKnjigaID(), knjiga.getBrojStranica(),
                DateTimeFormater.getStringFromDate(knjiga.getDatumDodavanja()),
                knjiga.getGodinaIzdanja(), knjiga.getImeAutora(), knjiga.isIznajmljena(),
                knjiga.getNaklada(), knjiga.getNaslov(), knjiga.getKorisnikID(), knjiga.getKlasifikacijaID());
    }


    public static List<Klasifikacija> SelectKlasifikacijeKnjiga(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_SelectKlasifikacije();
    }

    public static boolean InsertKlasifikacijaKnjige(Context ctx, Klasifikacija k) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_InsertKlasifikacija(k.getNaziv());

    }

    public static boolean DeleteKnjige(Context ctx, int knjigaID) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_DeleteKnjige(knjigaID);
    }
}

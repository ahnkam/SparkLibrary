package com.example.admin.sparklibrary.Kontroleri;


import android.content.Context;

import com.example.admin.sparklibrary.DB.MojDbContext;
import com.example.admin.sparklibrary.Helper.DateTimeFormater;
import com.example.admin.sparklibrary.Model.ClanoviKnjige;
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

    public static void UpdateKnjiga(Knjige knjiga, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        db.usp_UpdateKnjiga(knjiga.getKnjigaID(), knjiga.getBrojStranica(),
                DateTimeFormater.getStringFromDate(knjiga.getDatumDodavanja()),
                knjiga.getGodinaIzdanja(), knjiga.getImeAutora(), knjiga.isIznajmljena(),
                knjiga.getNaklada(), knjiga.getNaslov(), knjiga.getKorisnikID(), knjiga.getKlasifikacijaID());
    }

    public static void VratiPosudjenuKnjigu(Context ctx, KnjigeViewModel k) {
        MojDbContext db = new MojDbContext(ctx);
        db.usp_Knjige_SetPosudjenja(k.getKnjigaID(), 0);
        db.usp_ClanoviKnjige_VratiPosudjeneKnjige(k.getKnjigaID());
    }

    public static boolean PosudiKnjigu(Context ctx, ClanoviKnjige ck) {
        MojDbContext db = new MojDbContext(ctx);
        //postavi knjigu posudjena
        db.usp_Knjige_SetPosudjenja(ck.getKnjigaID(), 1);
        //dodaj knjigu odredjenom clanu
        return db.usp_ClanoviKnjige_PosudiKnjigu(ck.getKnjigaID(), ck.getClanID(),
                DateTimeFormater.getStringFromDate(ck.getDatumPosudjivanja()), ck.isVracena() ? 1 : 0);
    }

    public static List<KnjigeViewModel> SelectPosudjeneKnjige(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        //TODO : URADITI SVASTA
        return null;
    }
}

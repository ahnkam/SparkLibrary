package com.example.admin.sparklibrary.Kontroleri;

import android.content.Context;

import com.example.admin.sparklibrary.DB.MojDbContext;
import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.Model.Korisnik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 23.05.2017..
 */

public class KorisniciKontroler {
    public static boolean InsertKorisnici(Korisnik korisnik, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.uspKorisniciInsert(korisnik.getIme(), korisnik.getPrezime(), korisnik.getKorisnickoIme(),
                korisnik.getPassword());
    }

    public static List<Korisnik> SelectKorisnici(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        try {
            return db.usp_SelectKorisnici();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Korisnik>();
    }


    public static Korisnik Login(String username, String password, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_SelectKorisnikByUsernameAndPass(username, password);


    }

    public static boolean KorisnikExists(String korisnickoIme, Context ctx) {
        MojDbContext db = new MojDbContext(ctx);
        return db.usp_GetKorisnikByKorisnickoIme(korisnickoIme) != null;
    }


}

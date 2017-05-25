package com.example.admin.sparklibrary.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.Model.Klasifikacija;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.Model.Korisnik;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.05.2017..
 */

public class MojDbContext extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "sparklibrary.db";
    //--------------------------------------------------------------------------

    //Tabele
    private static final String TABLE_Korisnici = "Korisnici";
    private static final String TABLE_Clanovi = "Clanovi";
    private static final String TABLE_Knjige = "Knjige";
    private static final String TABLE_Postavke = "Postavke";
    private static final String TABLE_Klasifikacija = "Klasifikacija";
    //--------------------------------------------------------------------------

    //MedjuTabele
    private static final String TABLE_ClanoviKnjige = "ClanoviKnjige";
    //--------------------------------------------------------------------------

    //Korisnici
    private static final String Korisnici_KorisnikID = "KorisnikID";
    private static final String Korisnici_Ime = "Ime";
    private static final String Korisnici_Prezime = "Prezime";
    private static final String Korisnici_KorisnickoIme = "KorisnickoIme";
    private static final String Korisnici_Password = "Password";

    //FK NA POSTAVKE KORISNIKA
    private static final String Korisnici_PostavkeID = "PostavkeID";

    //--------------------------------------------------------------------------
    //Clanovi
    private static final String Clanovi_ClanID = "ClanID";
    private static final String Clanovi_Ime = "Ime";
    private static final String Clanovi_Prezime = "Prezime";
    private static final String Clanovi_Adresa = "Adresa";
    private static final String Clanovi_BrojTelefona = "BrojTelefona";
    private static final String Clanovi_ClanskiBroj = "ClanskiBroj";

    //FK NA KORISNIKA KOJI JE DODAO OVOG CLANA
    private static final String Clanovi_KorisnikID = "KorisnikID";
    //--------------------------------------------------------------------------

    //Knjige
    private static final String Knjige_KnjigaID = "KnjigaID";
    private static final String Knjige_Naslov = "Naslov";
    private static final String Knjige_ImeAutora = "ImeAutora";
    private static final String Knjige_Naklada = "Naklada";
    private static final String Knjige_GodinaIzdanja = "GodinaIzdanja";
    private static final String Knjige_BrojStranica = "BrojStranica";
    private static final String Knjige_IsIznajmljena = "IsIznajmljena";
    private static final String Knjige_DatumDodavanja = "DatumDodavanja";

    //FK NA KORISNIKA KOJI JE DODAO KNJIGU
    private static final String Knjige_KorisnikID = "KorisnikID";
    //FK NA KLASIFIKACIJU KNJIGE
    private static final String Knjige_KlasifikacijaID = "KlasifikacijaID";

    //--------------------------------------------------------------------------

    //Postavke
    private static final String Postavke_PostavkeID = "PostavkeID";
    private static final String Postavke_FontStyle = "FontStyle";
    //--------------------------------------------------------------------------

    //Klasifikacije
    private static final String Klasifikacije_KlasifikacijaID = "KlasifikacijaID";
    private static final String Klasifikacije_Naziv = "Naziv";
    //--------------------------------------------------------------------------

    //ClanoviKnjige
    private static final String ClanoviKnjige_ClanoviKnjigeID = "ClanoviKnjigeID";
    //FK NA CLANA KOJI JE IZNAJMIO KNJIGU
    private static final String ClanoviKnjige_ClanID = "ClanID";
    //FK NA KNJIGU KOJA JE IZNAJMLJENA
    private static final String ClanoviKnjige_KnjigaID = "KnjigaID";

    public MojDbContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE TABLES

        //TABLE POSTAVKE
        db.execSQL("CREATE TABLE " + TABLE_Postavke +
                "("
                + Postavke_PostavkeID + " integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + Postavke_FontStyle + " TEXT NOT NULL"
                + ")"
        );
        //Klasifikacije
        db.execSQL("CREATE TABLE " + TABLE_Klasifikacija +
                "("
                + Klasifikacije_KlasifikacijaID + " integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + Klasifikacije_Naziv + " TEXT NOT NULL"
                + ")"
        );


        //TABLE Korisnici
        db.execSQL("CREATE TABLE " + TABLE_Korisnici +
                "("
                + Korisnici_KorisnikID + " integer  PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + Korisnici_Ime + " TEXT NOT NULL,"
                + Korisnici_Prezime + " TEXT NOT NULL,"
                + Korisnici_KorisnickoIme + " TEXT NOT NULL,"
                + Korisnici_Password + " TEXT NOT NULL,"
                + Korisnici_PostavkeID + " integer," +
                "FOREIGN KEY(" + Korisnici_PostavkeID + ") REFERENCES " + TABLE_Postavke + "(" + Postavke_PostavkeID + ")" +
                ")"
        );

        //TABLE CLANOVI
        db.execSQL("CREATE TABLE " + TABLE_Clanovi +
                "("
                + Clanovi_ClanID + " integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + Clanovi_Ime + " TEXT NOT NULL,"
                + Clanovi_Prezime + " TEXT NOT NULL,"
                + Clanovi_Adresa + " TEXT NOT NULL,"
                + Clanovi_BrojTelefona + " TEXT NOT NULL,"
                + Clanovi_ClanskiBroj + " TEXT NOT NULL,"
                + Clanovi_KorisnikID + " integer not null,"
                + "FOREIGN KEY(" + Clanovi_KorisnikID + ") REFERENCES " + TABLE_Korisnici + "(" + Korisnici_KorisnikID + "),"
                + "CONSTRAINT " + Clanovi_ClanskiBroj + "_UNIQUE UNIQUE (" + Clanovi_ClanskiBroj + ")"
                + ")"
        );

        //TABLE KNJIGe
        db.execSQL("CREATE TABLE " + TABLE_Knjige +
                "("
                + Knjige_KnjigaID + " integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + Knjige_BrojStranica + " integer NOT NULL,"
                + Knjige_DatumDodavanja + " TEXT NOT NULL,"
                + Knjige_GodinaIzdanja + " integer NOT NULL,"
                + Knjige_ImeAutora + " TEXT NOT NULL,"
                + Knjige_IsIznajmljena + " integer DEFAULT 0,"
                + Knjige_Naklada + " TEXT NOT NULL,"
                + Knjige_Naslov + " TEXT NOT NULL,"
                + Knjige_KorisnikID + " INT NOT NULL,"
                + Knjige_KlasifikacijaID + " TEXT NOT NULL,"
                + "FOREIGN KEY(" + Knjige_KlasifikacijaID + ") REFERENCES " + TABLE_Klasifikacija + "(" + Klasifikacije_KlasifikacijaID + "),"
                + "FOREIGN KEY(" + Knjige_KorisnikID + ") REFERENCES " + TABLE_Korisnici + "(" + Korisnici_KorisnikID + ")"
                + ")"
        );


        //Iznajmljene knjige
        db.execSQL("CREATE TABLE " + TABLE_ClanoviKnjige +
                "("
                + ClanoviKnjige_ClanoviKnjigeID + " integer PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + ClanoviKnjige_ClanID + " INT NOT NULL,"
                + ClanoviKnjige_KnjigaID + " INT NOT NULL,"
                + "FOREIGN KEY(" + ClanoviKnjige_ClanID + ") REFERENCES " + TABLE_Clanovi + "(" + Clanovi_ClanID + "),"
                + "FOREIGN KEY(" + ClanoviKnjige_KnjigaID + ") REFERENCES " + TABLE_Knjige + "(" + Knjige_KnjigaID + ")"
                + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Klasifikacija);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Postavke);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClanoviKnjige);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Clanovi);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Knjige);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Korisnici);


        onCreate(db);

    }

    //----------------------------------------------------------------------------------------------------------------
    //CRUD CLANOVI
    public boolean usp_Clanovi_Insert(String ime, String prezime, String adresa, String brojTelefona, String clanskiBroj, int korisnikID) {

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Clanovi_Ime, ime);
        values.put(Clanovi_Prezime, prezime);
        values.put(Clanovi_Adresa, adresa);
        values.put(Clanovi_BrojTelefona, brojTelefona);
        values.put(Clanovi_ClanskiBroj, clanskiBroj);
        values.put(Clanovi_KorisnikID, korisnikID);

        long result = db.insert(TABLE_Clanovi, null, values);
        db.close();

        return result != -1;
    }

    public List<Clan> usp_SelectClanovi() {
        List<Clan> clanovi = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "
                        + Clanovi_ClanID + ","
                        + Clanovi_Ime + ","
                        + Clanovi_Prezime + ","
                        + Clanovi_Adresa + ","
                        + Clanovi_BrojTelefona + ","
                        + Clanovi_ClanskiBroj + ","
                        + Clanovi_KorisnikID + ""
                        + " FROM " + TABLE_Clanovi
                , null);
        if (c.moveToFirst()) {
            do {
                //assing values
                String clanId = c.getString(0);
                String ime = c.getString(1);
                String prezime = c.getString(2);
                String adresa = c.getString(3);
                String brojTelefona = c.getString(4);
                String clanskiBroj = c.getString(5);
                String korisnikID = c.getString(6);
                //Do something Here with values

                clanovi.add(new Clan(Integer.parseInt(clanId), ime, prezime, adresa, brojTelefona, clanskiBroj, Integer.parseInt(korisnikID)));

            } while (c.moveToNext());
        }
        c.close();
        db.close();


        return clanovi;
    }


    //----------------------------------------------------------------------------------------------------------------
    //CRUD KORISNICI

    public boolean uspKorisniciInsert(String ime, String prezime, String korisnickoIme, String password) {
        long result = -1;
        try {
            SQLiteDatabase db = getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(Korisnici_Ime, ime);
            values.put(Korisnici_Prezime, prezime);
            values.put(Korisnici_KorisnickoIme, korisnickoIme);
            values.put(Korisnici_Password, password);

            result = db.insert(TABLE_Korisnici, null, values);
            db.close();
        } catch (Exception e) {
            Log.i("MojDbContext", e.getMessage());
        }

        return result != -1;

    }

    public List<Korisnik> usp_SelectKorisnici() throws Exception {
        List<Korisnik> korisnici = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "
                        + Korisnici_KorisnikID + ","
                        + Korisnici_Ime + ","
                        + Korisnici_Prezime + ","
                        + Korisnici_KorisnickoIme + ","
                        + Korisnici_Password
                        + " FROM " + TABLE_Korisnici
                , null);
        if (c.moveToFirst()) {
            do {
                //assing values
                String korisnikID = c.getString(0);
                String ime = c.getString(1);
                String prezime = c.getString(2);
                String korisnickoIme = c.getString(3);
                String password = c.getString(4);
                //Do something Here with values

                korisnici.add(new Korisnik(Integer.parseInt(korisnikID), ime, prezime, korisnickoIme, password));

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return korisnici;
    }

    public Korisnik usp_SelectKorisnikByUsernameAndPass(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Korisnik k = null;

        String selectQuery = "SELECT "
                + Korisnici_KorisnikID + ","
                + Korisnici_Ime + ","
                + Korisnici_Prezime + ","
                + Korisnici_KorisnickoIme + ","
                + Korisnici_Password
                + " FROM " + TABLE_Korisnici
                + " WHERE " + Korisnici_KorisnickoIme + " = ? AND " + Korisnici_Password + " = ?";

        Cursor c = db.rawQuery(selectQuery, new String[]{username, password});
        if (c.moveToFirst()) {
            String korisnikID = c.getString(0);
            String ime = c.getString(1);
            String prezime = c.getString(2);
            String korisnickoIme = c.getString(3);
            String _password = c.getString(4);
            k = new Korisnik(Integer.parseInt(korisnikID), ime, prezime, korisnickoIme, _password);
        }
        c.close();
        db.close();

        return k;

    }

    public Korisnik usp_GetKorisnikByKorisnickoIme(String korisnickoIme) {
        SQLiteDatabase db = this.getReadableDatabase();
        Korisnik k = null;
        String selectQuery = "SELECT "
                + Korisnici_KorisnikID + ","
                + Korisnici_Ime + ","
                + Korisnici_KorisnickoIme + ","
                + Korisnici_Password
                + " FROM " + TABLE_Korisnici
                + " WHERE " + Korisnici_KorisnickoIme + " = ?";

        Cursor c = db.rawQuery(selectQuery, new String[]{korisnickoIme});
        if (c.moveToFirst()) {
            String korisnikID = c.getString(0);
            String ime = c.getString(1);
            String prezime = c.getString(2);
            String username = c.getString(2);
            String _password = c.getString(3);
            k = new Korisnik(Integer.parseInt(korisnikID), ime, prezime, username, _password);
        }
        c.close();
        db.close();

        return k;


    }


    //TODO : CRUD Knjige

    public List<KnjigeViewModel> usp_SelectKnjige() {

        List<KnjigeViewModel> knjige = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT "
                        + TABLE_Knjige + ".*,"
                        + TABLE_Klasifikacija + "." + Klasifikacije_Naziv
                        + " FROM " + TABLE_Knjige
                        + " JOIN " + TABLE_Klasifikacija + " ON "
                        + TABLE_Knjige + "." + Knjige_KlasifikacijaID + " = "
                        + TABLE_Klasifikacija + "." + Klasifikacije_KlasifikacijaID
                , null);
        if (c.moveToFirst()) {
            do {
                //assing values
                //KnjigaID,BrojStranica,DatumDodavanja,GodinaIzdanja,ImeAutora,IsIznajmljena,Naklada,Naslov,KorisnikID,KlasifikacijaID,Naziv

                String KnjigaID = c.getString(0);
                String BrojStranica = c.getString(1);
                String DatumDodavanja = c.getString(2);
                String GodinaIzdanja = c.getString(3);
                String ImeAutora = c.getString(4);
                String IsIznajmljena = c.getString(5);
                String Naklada = c.getString(6);
                String Naslov = c.getString(7);
                String KorisnikID = c.getString(8);
                String KlasifikacijaID = c.getString(9);
                String Naziv = c.getString(10);

                //Do something Here with values

                knjige.add(new KnjigeViewModel(KnjigaID, BrojStranica, DatumDodavanja, GodinaIzdanja, ImeAutora,
                        IsIznajmljena, Naklada, Naslov, KorisnikID, KlasifikacijaID, Naziv));

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return knjige;

    }


    public List<Klasifikacija> usp_SelectKlasifikacije() {
        List<Klasifikacija> klasifikacije = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_Klasifikacija
                , null);
        if (c.moveToFirst()) {
            do {
                //assing values
                //KlasifikacijaID,Naziv
                String KlasifikacijaID = c.getString(0);
                String Naziv = c.getString(1);

                //Do something Here with values

                klasifikacije.add(new Klasifikacija(KlasifikacijaID, Naziv));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return klasifikacije;

    }

    public boolean usp_InsertKnjige(int knjigaID, int brojStranica, String datumDodavanja, int godinaIzdanja, String imeAutora, boolean iznajmljena,
                                    String naklada, String naslov, int korisnikID, int klasifikacijaID) {
        ContentValues values = new ContentValues();

        values.put(Knjige_BrojStranica, brojStranica);
        values.put(Knjige_DatumDodavanja, datumDodavanja);
        values.put(Knjige_GodinaIzdanja, godinaIzdanja);
        values.put(Knjige_ImeAutora, imeAutora);
        values.put(Knjige_IsIznajmljena, iznajmljena == true ? 1 : 0);
        values.put(Knjige_Naklada, naklada);
        values.put(Knjige_Naslov, naslov);
        values.put(Knjige_KorisnikID, korisnikID);
        values.put(Knjige_KlasifikacijaID, klasifikacijaID);

        SQLiteDatabase db = getReadableDatabase();
        long result = db.insert(TABLE_Knjige, null, values);
        db.close();

        return result != -1;

    }

    public boolean usp_InsertKlasifikacija(String naziv) {
        ContentValues values = new ContentValues();
        values.put(Klasifikacije_Naziv, naziv);
        SQLiteDatabase db = getReadableDatabase();
        long result = db.insert(TABLE_Klasifikacija, null, values);
        db.close();
        return result != -1;
    }

    public boolean usp_DeleteKnjige(int knjigaID) {

        SQLiteDatabase db = this.getReadableDatabase();
        db.rawQuery("DELETE FROM "
                + TABLE_Knjige + " WHERE " + Knjige_KnjigaID + " = ?", new String[]{knjigaID + ""});
        db.close();
        return true;
    }
}

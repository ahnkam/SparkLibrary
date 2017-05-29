package com.example.admin.sparklibrary.ViewModeli;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29.05.2017..
 */

public class ClanoviViewModel {
    private int ClanID;
    private String Ime;
    private String Prezime;
    private String Adresa;
    private String BrojTelefona;
    private String ClanskiBroj;
    //navigacija do korisnika koji je kreirao clana
    private int KorisnikID;
    List<KnjigeViewModel> posudjeneKnjige;

    public ClanoviViewModel(int clanID, String ime, String prezime, String adresa, String brojTelefona, String clanskiBroj, int korisnikID) {
        ClanID = clanID;
        Ime = ime;
        Prezime = prezime;
        Adresa = adresa;
        BrojTelefona = brojTelefona;
        ClanskiBroj = clanskiBroj;
        KorisnikID = korisnikID;
        posudjeneKnjige = new ArrayList<>();
    }

    public int getClanID() {
        return ClanID;
    }

    public void setClanID(int clanID) {
        ClanID = clanID;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public String getBrojTelefona() {
        return BrojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        BrojTelefona = brojTelefona;
    }

    public String getClanskiBroj() {
        return ClanskiBroj;
    }

    public void setClanskiBroj(String clanskiBroj) {
        ClanskiBroj = clanskiBroj;
    }

    public int getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        KorisnikID = korisnikID;
    }

    public List<KnjigeViewModel> getPosudjeneKnjige() {
        return posudjeneKnjige;
    }

    public void setPosudjeneKnjige(List<KnjigeViewModel> posudjeneKnjige) {
        this.posudjeneKnjige = posudjeneKnjige;
    }
}

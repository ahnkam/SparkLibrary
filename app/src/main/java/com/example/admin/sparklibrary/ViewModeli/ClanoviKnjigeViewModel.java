package com.example.admin.sparklibrary.ViewModeli;

import android.support.annotation.NonNull;

import com.example.admin.sparklibrary.Helper.DateTimeFormater;
import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.Model.Knjige;

import java.util.Date;

public class ClanoviKnjigeViewModel implements Comparable<ClanoviKnjigeViewModel> {
    private int ClanoviKnjigeID;
    private int KnjigaID;
    private int ClanID;
    private String Naslov;
    private String Ime;
    private String Prezime;
    private String ClanskiBroj;
    private Date DatumPosudjivanja;


    public ClanoviKnjigeViewModel
            (String cLanoviKnjigeID, String knjigeID, String clanID,
             String naslov, String ime, String prezime, String clanskiBroj,
             String datumPosudjivanja) {
        ClanoviKnjigeID = Integer.parseInt(cLanoviKnjigeID);
        KnjigaID = Integer.parseInt(knjigeID);
        ClanID = Integer.parseInt(clanID);
        Naslov = naslov;
        Ime = ime;
        Prezime = prezime;
        ClanskiBroj = clanskiBroj;
        DatumPosudjivanja = DateTimeFormater.getDateFromString(datumPosudjivanja);
    }

    public int getClanoviKnjigeID() {
        return ClanoviKnjigeID;
    }

    public void setClanoviKnjigeID(int clanoviKnjigeID) {
        ClanoviKnjigeID = clanoviKnjigeID;
    }

    public int getKnjigaID() {
        return KnjigaID;
    }

    public void setKnjigaID(int knjigaID) {
        KnjigaID = knjigaID;
    }

    public int getClanID() {
        return ClanID;
    }

    public void setClanID(int clanID) {
        ClanID = clanID;
    }

    public String getNaslov() {
        return Naslov;
    }

    public void setNaslov(String naslov) {
        Naslov = naslov;
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

    public String getClanskiBroj() {
        return ClanskiBroj;
    }

    public void setClanskiBroj(String clanskiBroj) {
        ClanskiBroj = clanskiBroj;
    }

    public Date getDatumPosudjivanja() {
        return DatumPosudjivanja;
    }

    public void setDatumPosudjivanja(Date datumPosudjivanja) {
        DatumPosudjivanja = datumPosudjivanja;
    }

    @Override
    public int compareTo(@NonNull ClanoviKnjigeViewModel o) {
        return o.DatumPosudjivanja.compareTo(DatumPosudjivanja);
    }

    public String getImePrezime() {
        return Ime + " " + Prezime;
    }
}

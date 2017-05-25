package com.example.admin.sparklibrary.Model;

import com.example.admin.sparklibrary.Config.Sesija;

import java.util.Date;

public class Knjige {

    private String Naslov;
    private String ImeAutora;
    private String Naklada;
    private int GodinaIzdanja;
    private Date DatumDodavanja;
    private int BrojStranica;
    private boolean IsIznajmljena;
    private int KnjigaID;
    private int KlasifikacijaID;
    private int KorisnikID;

    public Knjige(String naziv, String naklada, String autorIme, int godinaIzdanja, int brojStranica, int klasifikacijaID, int korisnikId) {
        Naslov = naziv;
        ImeAutora = autorIme;
        Naklada = naklada;
        GodinaIzdanja = godinaIzdanja;
        DatumDodavanja = new Date();
        BrojStranica = brojStranica;
        IsIznajmljena = false;
        KlasifikacijaID = klasifikacijaID;
        KorisnikID = korisnikId;
    }

    public String getNaslov() {
        return Naslov;
    }

    public void setNaslov(String naslov) {
        Naslov = naslov;
    }

    public String getImeAutora() {
        return ImeAutora;
    }

    public void setImeAutora(String imeAutora) {
        ImeAutora = imeAutora;
    }

    public String getNaklada() {
        return Naklada;
    }

    public void setNaklada(String naklada) {
        Naklada = naklada;
    }

    public int getGodinaIzdanja() {
        return GodinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        GodinaIzdanja = godinaIzdanja;
    }

    public Date getDatumDodavanja() {
        return DatumDodavanja;
    }

    public void setDatumDodavanja(Date datumDodavanja) {
        DatumDodavanja = datumDodavanja;
    }

    public int getBrojStranica() {
        return BrojStranica;
    }

    public void setBrojStranica(int brojStranica) {
        BrojStranica = brojStranica;
    }

    public boolean isIznajmljena() {
        return IsIznajmljena;
    }

    public void setIznajmljena(boolean iznajmljena) {
        IsIznajmljena = iznajmljena;
    }

    public int getKnjigaID() {
        return KnjigaID;
    }

    public void setKnjigaID(int knjigaID) {
        KnjigaID = knjigaID;
    }

    public int getKlasifikacijaID() {
        return KlasifikacijaID;
    }

    public void setKlasifikacijaID(int klasifikacijaID) {
        KlasifikacijaID = klasifikacijaID;
    }

    public int getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        KorisnikID = korisnikID;
    }
}
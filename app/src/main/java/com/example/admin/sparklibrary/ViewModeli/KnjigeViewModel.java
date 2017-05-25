package com.example.admin.sparklibrary.ViewModeli;

import com.example.admin.sparklibrary.Helper.DateTimeFormater;
import com.example.admin.sparklibrary.Kontroleri.KorisniciKontroler;

import java.util.Date;

/**
 * Created by Admin on 24.05.2017..
 */

public class KnjigeViewModel {
    private int KnjigaID;
    private int BrojStranica;

    private String Naslov;
    private String ImeAutora;
    private String Naklada;
    private int GodinaIzdanja;
    private Date DatumDodavanja;
    private boolean IsIznajmljena;
    private int KlasifikacijaID;
    private int KorisnikID;
    private String NazivKlasifikacije;

    public KnjigeViewModel(String knjigaID, String brojStranica, String datumDodavanja, String godinaIzdanja, String imeAutora, String isIznajmljena,
                           String naklada, String naslov, String korisnikID, String klasifikacijaID, String nazivKlasifikacije) {
        KnjigaID = Integer.parseInt(knjigaID);
        BrojStranica = Integer.parseInt(brojStranica);
        String s = "03/24/2013 21:54";

        DatumDodavanja = DateTimeFormater.getDateFromString(datumDodavanja);
        GodinaIzdanja = Integer.parseInt(godinaIzdanja);
        ImeAutora = imeAutora;
        IsIznajmljena = Integer.parseInt(isIznajmljena) == 1 ? true : false;
        Naklada = naklada;
        Naslov = naslov;
        KorisnikID = Integer.parseInt(korisnikID);
        KlasifikacijaID = Integer.parseInt(klasifikacijaID);
        NazivKlasifikacije = nazivKlasifikacije;
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

    public int getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        KorisnikID = korisnikID;
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

    public String getNazivKlasifikacije() {
        return NazivKlasifikacije;
    }

    public void setNazivKlasifikacije(String nazivKlasifikacije) {
        NazivKlasifikacije = nazivKlasifikacije;
    }
}

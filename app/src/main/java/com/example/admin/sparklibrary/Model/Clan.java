package com.example.admin.sparklibrary.Model;


public class Clan {
    private int ClanID;
    private String Ime;
    private String Prezime;
    private String Adresa;
    private String BrojTelefona;
    private String ClanskiBroj;
    //navigacija do korisnika koji je kreirao clana
    private int KorisnikID;

    public Clan() {
    }

    public Clan(int clanId, String ime, String prezime, String adresa, String brojTelefona, String clanskiBroj, int korisnikID) {
        ClanID = clanId;
        Ime = ime;
        Prezime = prezime;
        Adresa = adresa;
        BrojTelefona = brojTelefona;
        ClanskiBroj = clanskiBroj;
        KorisnikID = korisnikID;

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

    public int getClanID() {
        return ClanID;
    }

    public void setClanID(int clanID) {
        ClanID = clanID;
    }

    public int getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        KorisnikID = korisnikID;
    }
}
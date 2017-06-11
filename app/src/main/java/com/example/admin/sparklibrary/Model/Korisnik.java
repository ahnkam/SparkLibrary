package com.example.admin.sparklibrary.Model;

public class Korisnik {
    private String Ime;
    private String Prezime;
    private String KorisnickoIme;
    private String Password;
    private int KorisnikID;

    public Korisnik() {
    }

    public Korisnik(int korisnikID, String ime, String prezime, String korisnickoIme, String password) {
        Ime = ime;
        Prezime = prezime;
        KorisnickoIme = korisnickoIme;
        Password = password;
        KorisnikID = korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
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

    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        KorisnickoIme = korisnickoIme;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getKorisnikID() {
        return KorisnikID;
    }

    public String getImePrezime() {
        return Ime + " " + Prezime;
    }
}
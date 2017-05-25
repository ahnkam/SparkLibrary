package com.example.admin.sparklibrary.Model;

public class Klasifikacija {

    private String Naziv;
    private int KlasifikacijaID;

    public Klasifikacija(String klasifikacijaID, String naziv) {
        Naziv = naziv;
        KlasifikacijaID = Integer.parseInt(klasifikacijaID);
    }

    public Klasifikacija(String naziv) {
        Naziv = naziv;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String naziv) {
        Naziv = naziv;
    }

    public int getKlasifikacijaID() {
        return KlasifikacijaID;
    }

    public void setKlasifikacijaID(int klasifikacijaID) {
        KlasifikacijaID = klasifikacijaID;
    }
}
package com.example.admin.sparklibrary.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Clan implements Parcelable {
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


    protected Clan(Parcel in) {
        ClanID = in.readInt();
        Ime = in.readString();
        Prezime = in.readString();
        Adresa = in.readString();
        BrojTelefona = in.readString();
        ClanskiBroj = in.readString();
        KorisnikID = in.readInt();
    }

    public static final Creator<Clan> CREATOR = new Creator<Clan>() {
        @Override
        public Clan createFromParcel(Parcel in) {
            return new Clan(in);
        }

        @Override
        public Clan[] newArray(int size) {
            return new Clan[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ClanID);
        dest.writeString(Ime);
        dest.writeString(Prezime);
        dest.writeString(Adresa);
        dest.writeString(BrojTelefona);
        dest.writeString(ClanskiBroj);
        dest.writeInt(KorisnikID);
    }
}
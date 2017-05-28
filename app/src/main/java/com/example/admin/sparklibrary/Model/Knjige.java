package com.example.admin.sparklibrary.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.admin.sparklibrary.Config.Sesija;

import java.util.Date;

public class Knjige implements Parcelable {

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

    protected Knjige(Parcel in) {
        Naslov = in.readString();
        ImeAutora = in.readString();
        Naklada = in.readString();
        GodinaIzdanja = in.readInt();
        BrojStranica = in.readInt();
        IsIznajmljena = in.readByte() != 0;
        KnjigaID = in.readInt();
        KlasifikacijaID = in.readInt();
        KorisnikID = in.readInt();
    }

    public static final Creator<Knjige> CREATOR = new Creator<Knjige>() {
        @Override
        public Knjige createFromParcel(Parcel in) {
            return new Knjige(in);
        }

        @Override
        public Knjige[] newArray(int size) {
            return new Knjige[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Naslov);
        dest.writeString(ImeAutora);
        dest.writeString(Naklada);
        dest.writeInt(GodinaIzdanja);
        dest.writeInt(BrojStranica);
        dest.writeByte((byte) (IsIznajmljena ? 1 : 0));
        dest.writeInt(KnjigaID);
        dest.writeInt(KlasifikacijaID);
        dest.writeInt(KorisnikID);
    }
}
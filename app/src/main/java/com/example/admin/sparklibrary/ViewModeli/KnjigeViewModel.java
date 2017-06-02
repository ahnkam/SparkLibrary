package com.example.admin.sparklibrary.ViewModeli;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.admin.sparklibrary.Helper.DateTimeFormater;
import com.example.admin.sparklibrary.Kontroleri.KorisniciKontroler;

import java.util.Date;


public class KnjigeViewModel implements Parcelable {
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

    public KnjigeViewModel(int knjigaID) {
        KnjigaID = knjigaID;
    }
    public KnjigeViewModel(String knjigaID, String brojStranica, String datumDodavanja, String godinaIzdanja, String imeAutora, String isIznajmljena,
                           String naklada, String naslov, String korisnikID, String klasifikacijaID, String nazivKlasifikacije) {
        KnjigaID = Integer.parseInt(knjigaID);
        BrojStranica = Integer.parseInt(brojStranica);
        String s = "03/24/2013 21:54";

        DatumDodavanja = DateTimeFormater.getDateFromString(datumDodavanja);
        GodinaIzdanja = Integer.parseInt(godinaIzdanja);
        ImeAutora = imeAutora;
        IsIznajmljena = Integer.parseInt(isIznajmljena) == 1;
        Naklada = naklada;
        Naslov = naslov;
        KorisnikID = Integer.parseInt(korisnikID);
        KlasifikacijaID = Integer.parseInt(klasifikacijaID);
        NazivKlasifikacije = nazivKlasifikacije;
    }

    protected KnjigeViewModel(Parcel in) {
        KnjigaID = in.readInt();
        BrojStranica = in.readInt();
        Naslov = in.readString();
        ImeAutora = in.readString();
        Naklada = in.readString();
        GodinaIzdanja = in.readInt();
        IsIznajmljena = in.readByte() != 0;
        KlasifikacijaID = in.readInt();
        KorisnikID = in.readInt();
        NazivKlasifikacije = in.readString();
        DatumDodavanja = (Date) in.readSerializable();
    }

    public static final Creator<KnjigeViewModel> CREATOR = new Creator<KnjigeViewModel>() {
        @Override
        public KnjigeViewModel createFromParcel(Parcel in) {
            return new KnjigeViewModel(in);
        }

        @Override
        public KnjigeViewModel[] newArray(int size) {
            return new KnjigeViewModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(KnjigaID);
        dest.writeInt(BrojStranica);
        dest.writeString(Naslov);
        dest.writeString(ImeAutora);
        dest.writeString(Naklada);
        dest.writeInt(GodinaIzdanja);
        dest.writeByte((byte) (IsIznajmljena ? 1 : 0));
        dest.writeInt(KlasifikacijaID);
        dest.writeInt(KorisnikID);
        dest.writeString(NazivKlasifikacije);
        dest.writeSerializable(DatumDodavanja);
    }
}

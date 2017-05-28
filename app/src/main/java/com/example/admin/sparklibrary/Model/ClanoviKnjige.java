package com.example.admin.sparklibrary.Model;

import java.util.Date;

public class ClanoviKnjige {

    private int ClanoviKnjigeID;
    private int ClanID;
    private int KnjigaID;
    private Date DatumPosudjivanja;
    private boolean IsVracena;

    public ClanoviKnjige(int clanID, int knjigaID) {
        ClanID = clanID;
        KnjigaID = knjigaID;
        DatumPosudjivanja = new Date();
        IsVracena = false;
    }


    public int getClanoviKnjigeID() {
        return ClanoviKnjigeID;
    }

    public void setClanoviKnjigeID(int clanoviKnjigeID) {
        ClanoviKnjigeID = clanoviKnjigeID;
    }

    public int getClanID() {
        return ClanID;
    }

    public void setClanID(int clanID) {
        ClanID = clanID;
    }

    public int getKnjigaID() {
        return KnjigaID;
    }

    public void setKnjigaID(int knjigaID) {
        KnjigaID = knjigaID;
    }

    public Date getDatumPosudjivanja() {
        return DatumPosudjivanja;
    }

    public void setDatumPosudjivanja(Date datumPosudjivanja) {
        DatumPosudjivanja = datumPosudjivanja;
    }

    public boolean isVracena() {
        return IsVracena;
    }

    public void setVracena(boolean vracena) {
        IsVracena = vracena;
    }
}
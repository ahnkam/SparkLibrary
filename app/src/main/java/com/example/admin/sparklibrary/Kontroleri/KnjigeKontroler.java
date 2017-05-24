package com.example.admin.sparklibrary.Kontroleri;


import android.content.Context;

import com.example.admin.sparklibrary.DB.MojDbContext;
import com.example.admin.sparklibrary.Model.Knjige;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import java.util.List;

public class KnjigeKontroler {


    public static List<KnjigeViewModel> SelectKnjige(Context ctx) {
        MojDbContext db = new MojDbContext(ctx);

        return db.usp_SelectKnjige();

    }
}

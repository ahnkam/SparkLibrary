package com.example.admin.sparklibrary.Helper;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 24.05.2017..
 */

public class DateTimeFormater {
    public static Date getDateFromString(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (Exception ex) {
            Log.i("ParseException", ex.getMessage());
        }
        return date;
    }

    public static String getStringFromDate(Date datumDodavanja) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return simpleDateFormat.format(datumDodavanja);
    }
}

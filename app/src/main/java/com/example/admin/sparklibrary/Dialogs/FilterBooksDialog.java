package com.example.admin.sparklibrary.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Klasifikacija;
import com.example.admin.sparklibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 25.05.2017..
 */

public class FilterBooksDialog extends DialogFragment {

    private String filter;
    private ArrayList<String> klasifikacije;

    public interface IFilterSelected {
        void filterItems(String filter);
    }

    private static final String KEY_FILTER_KLASIFIKACIJE = "key_filter_klasifikacije";

    public static DialogFragment getInstance(Context ctx) {
        List<Klasifikacija> klasifikacije = KnjigeKontroler.SelectKlasifikacijeKnjiga(ctx);
        ArrayList<String> klasifikacijaNaziv = new ArrayList<>(klasifikacije.size());
        for (Klasifikacija k : klasifikacije) {
            klasifikacijaNaziv.add(k.getNaziv());
        }
        DialogFragment f = new FilterBooksDialog();
        Bundle b = new Bundle();
        b.putStringArrayList(KEY_FILTER_KLASIFIKACIJE, klasifikacijaNaziv);
        f.setArguments(b);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        // Where we track the selected items
        klasifikacije = getArguments().getStringArrayList(KEY_FILTER_KLASIFIKACIJE);
        klasifikacije.add(0, "---");
        filter = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the dialog title
        builder.setTitle(R.string.FilterBooksDialogTitle)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setPositiveButton(R.string.FilteBooksFilter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        ((IFilterSelected) getTargetFragment()).filterItems(filter);
                    }
                })
                .setNegativeButton(R.string.DialogCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        FilterBooksDialog.this.getDialog().dismiss();
                    }
                });
        builder.setSingleChoiceItems(klasifikacije.toArray(new CharSequence[klasifikacije.size()]), 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0)
                    filter = null;
                else
                    filter = klasifikacije.get(which);
            }
        });


        return builder.create();

    }
}

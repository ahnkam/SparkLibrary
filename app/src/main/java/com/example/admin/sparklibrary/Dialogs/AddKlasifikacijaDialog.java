package com.example.admin.sparklibrary.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.sparklibrary.Kontroleri.ClanoviKontroler;
import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.Model.Klasifikacija;
import com.example.admin.sparklibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 25.05.2017..
 */

public class AddKlasifikacijaDialog extends DialogFragment {


    private static final String KEY_BUNDLE_KLASIFIKACIJE = "bundle_key_klasifikacije";

    private ArrayList<String> klasifikacije;


    public interface IAddKlasifikacija {
        void KlasifikacijaInserted();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        klasifikacije = getArguments().getStringArrayList(KEY_BUNDLE_KLASIFIKACIJE);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.add_klasifikacija_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.AddKlasifikacijaDialogPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et = (EditText) AddKlasifikacijaDialog.this.getDialog().findViewById(R.id.etDialogKlasifikacijaNaziv);
                        TextView tvAddKlasifikacijaWarning = (TextView) AddKlasifikacijaDialog.this.getDialog().findViewById(R.id.tvAddKlasifikacijaWarning);
                        String naziv = et.getText().toString();
                        if (naziv.isEmpty())
                            return;
                        if (klasifikacije.contains(naziv)) {
                            tvAddKlasifikacijaWarning.setText(R.string.AddKlasifikacijaWarning);
                            return;
                        }
                        KnjigeKontroler.InsertKlasifikacijaKnjige(getContext(), new Klasifikacija(naziv));
                        ((IAddKlasifikacija) getActivity()).KlasifikacijaInserted();
                    }
                })
                .setNegativeButton(R.string.DialogCancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddKlasifikacijaDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public static AddKlasifikacijaDialog getInstance(List<String> trenutneKlasifikacije) {
        AddKlasifikacijaDialog dialog = new AddKlasifikacijaDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(KEY_BUNDLE_KLASIFIKACIJE, (ArrayList<String>) trenutneKlasifikacije);
        dialog.setArguments(bundle);
        return dialog;
    }
}

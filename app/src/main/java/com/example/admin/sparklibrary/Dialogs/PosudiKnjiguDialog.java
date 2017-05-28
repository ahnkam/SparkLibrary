package com.example.admin.sparklibrary.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.admin.sparklibrary.Model.Clan;
import com.example.admin.sparklibrary.R;

/**
 * Created by Admin on 28.05.2017..
 */

public class PosudiKnjiguDialog extends DialogFragment {

    public interface IPosudiKnjiguDialog {
        void PosudiKnjiguDialogOkClicked(Clan clan);
    }

    private static final String KEY_PARCELABLE_CLAN = "KEY_PARCELABLE_CLAN";

    public static DialogFragment getInstance(Clan clan) {

        Bundle b = new Bundle();
        b.putParcelable(KEY_PARCELABLE_CLAN, clan);
        DialogFragment f = new PosudiKnjiguDialog();
        f.setArguments(b);
        return f;
    }

    private Clan clan;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        clan = getArguments().getParcelable(KEY_PARCELABLE_CLAN);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        String message = getResources().getString(R.string.PosudjivanjeKnjigeClanu) + " " + clan.getIme() + " " + clan.getPrezime();

        builder.setTitle(R.string.PosudiKnjiguDialogTitle).setMessage(message).setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doDialogBtnOkClick();
            }
        })
                .setNegativeButton(R.string.DialogCancel, null);
        return builder.create();

    }

    private void doDialogBtnOkClick() {
        ((IPosudiKnjiguDialog) getActivity()).PosudiKnjiguDialogOkClicked(clan);
    }
}

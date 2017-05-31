package com.example.admin.sparklibrary.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.admin.sparklibrary.Kontroleri.KnjigeKontroler;
import com.example.admin.sparklibrary.R;
import com.example.admin.sparklibrary.ViewModeli.KnjigeViewModel;

import static com.example.admin.sparklibrary.PosudjivanjeKnjiga.KEY_PARCELABLE_KNJIGA;

/**
 * Created by Admin on 28.05.2017..
 */

public class VratiPosudjenuKnjiguDialog extends DialogFragment {


    private KnjigeViewModel knjiga;
    private IVratiPosudjenuKnjiguDialog callback;

    public interface IVratiPosudjenuKnjiguDialog {
        void VratiPosudjenuDialogOkClicked(KnjigeViewModel knjiga);
    }

    public static DialogFragment getInstance(KnjigeViewModel k) {
        Bundle b = new Bundle();
        b.putParcelable(KEY_PARCELABLE_KNJIGA, k);
        DialogFragment f = new VratiPosudjenuKnjiguDialog();
        f.setArguments(b);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        knjiga = getArguments().getParcelable(KEY_PARCELABLE_KNJIGA);

        callback = ((IVratiPosudjenuKnjiguDialog) getTargetFragment());
        if (callback == null)
            callback = (IVratiPosudjenuKnjiguDialog) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.VratiPosudjenuKnjiguDialogTitle).setMessage(R.string.VratiPosudjenuKnjiguDialogMessage)
                .setNegativeButton(R.string.DialogCancel, null)
                .setPositiveButton(R.string.VratiPosudjenuKnjiguDialogOK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doDialogBtnOkClicked();
                    }
                });
        return builder.create();

    }

    private void doDialogBtnOkClicked() {
        KnjigeKontroler.VratiPosudjenuKnjigu(getContext(), knjiga);
        callback.VratiPosudjenuDialogOkClicked(knjiga);
    }
}

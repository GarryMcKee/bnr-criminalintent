package com.bignerdranch.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by Garry on 23/06/2017.
 */

public class CrimePhotoDetailDialog extends DialogFragment {

    private static final String ARG_CRIME_ID = "argCrimeId";

    public static CrimePhotoDetailDialog newInstance(UUID crimeId) {
        Log.d("CHECKID", crimeId.toString());
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimePhotoDetailDialog fragment = new CrimePhotoDetailDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View v = layoutInflater.inflate(R.layout.dialog_photo_detail, null);
        Crime crime = CrimeLab.get(getActivity()).getCrime((UUID) getArguments().getSerializable(ARG_CRIME_ID));
        String crimeTitle = crime.getTitle();
        Bitmap crimeBitmap = PictureUtils.getScaledBitmap(CrimeLab.get(getActivity()).getPhotoFile(crime).getPath(), getActivity());

        ImageView photoDetailView = (ImageView) v.findViewById(R.id.photo_detail_view);
        photoDetailView.setImageBitmap(crimeBitmap);

        return new AlertDialog.Builder(getActivity())
                .setTitle(crimeTitle)
                .setView(v)
                .create();
    }

}

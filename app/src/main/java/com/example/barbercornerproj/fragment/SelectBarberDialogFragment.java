package com.example.barbercornerproj.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbercornerproj.DatabaseHelper;
import com.example.barbercornerproj.R;
import com.example.barbercornerproj.adapter.BarberAdapter;
import com.example.barbercornerproj.model.StaffModel;

public class SelectBarberDialogFragment extends DialogFragment {

    public interface SelectBarberDialogListener {
        void onBarberClick(DialogFragment dialogFragment, StaffModel clickedBarber);
    }

    private RecyclerView relBarberSelect;
    private DatabaseHelper databaseHelper;
    private SelectBarberDialogListener barberDialogListener;

    public SelectBarberDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getContext());

        //  Inflate layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View barberSelectView = inflater.inflate(R.layout.fragment_select_barber_dialog, null);

        //  Init Recycler view
        relBarberSelect = barberSelectView.findViewById(R.id.rel_barber_select);
        BarberAdapter barberAdapter = new BarberAdapter(getContext(), databaseHelper.allStaffs(), this);
        relBarberSelect.setAdapter(barberAdapter);
        relBarberSelect.setLayoutManager(new LinearLayoutManager(getContext()));

        //  Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(barberSelectView);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            barberDialogListener = (SelectBarberDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement SelectBarberDialogListener");
        }
    }

    public void setRelBarberSelect(RecyclerView relBarberSelect) {
        this.relBarberSelect = relBarberSelect;
    }

    public void setDatabaseHelper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public SelectBarberDialogListener getBarberDialogListener() {
        return barberDialogListener;
    }

    public void setBarberDialogListener(SelectBarberDialogListener barberDialogListener) {
        this.barberDialogListener = barberDialogListener;
    }
}
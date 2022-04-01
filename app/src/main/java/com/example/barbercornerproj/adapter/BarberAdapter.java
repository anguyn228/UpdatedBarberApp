package com.example.barbercornerproj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbercornerproj.R;
import com.example.barbercornerproj.fragment.SelectBarberDialogFragment;
import com.example.barbercornerproj.model.StaffModel;

import java.util.ArrayList;

public class BarberAdapter extends RecyclerView.Adapter<BarberAdapter.ViewHolder> {
    private Context context;
    private ArrayList<StaffModel> barberList;
    private SelectBarberDialogFragment dialogFragment;

    public BarberAdapter(Context context, ArrayList<StaffModel> barberList) {
        this.context = context;
        this.barberList = barberList;
    }

    public BarberAdapter(Context context, ArrayList<StaffModel> barberList, SelectBarberDialogFragment dialogFragment) {
        this.context = context;
        this.barberList = barberList;
        this.dialogFragment = dialogFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View barberRow = inflater.inflate(R.layout.barber_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(barberRow);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        System.out.println(barberList.get(position).getName());
        holder.txtBarberName.setText(barberList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return barberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBarberAvatar;
        private TextView txtBarberName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBarberAvatar = itemView.findViewById(R.id.img_barber_avatar);
            txtBarberName = itemView.findViewById(R.id.txt_barber_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectedItemIndex = getAdapterPosition();
                    dialogFragment.getBarberDialogListener().onBarberClick(dialogFragment, barberList.get(selectedItemIndex));
                }
            });
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<StaffModel> getBarberList() {
        return barberList;
    }

    public void setBarberList(ArrayList<StaffModel> barberList) {
        this.barberList = barberList;
    }
}

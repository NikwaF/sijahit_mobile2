package com.example.sijahit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijahit.AccountActivity;
import com.example.sijahit.EditProfilAkunActivity;
import com.example.sijahit.Profile_ukuran_detail;
import com.example.sijahit.R;
import com.example.sijahit.model.Profile_ukuran_model;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class Profile_ukuran_adapter extends RecyclerView.Adapter<Profile_ukuran_adapter.Myviewholder> {

    private Context context;
    private List<Profile_ukuran_model> profile_ukuran_data;


    public Profile_ukuran_adapter(Context context, List<Profile_ukuran_model> profile_ukuran_data) {
        this.context = context;
        this.profile_ukuran_data = profile_ukuran_data;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.list_item, viewGroup, false) ;
        final Myviewholder myviewholder = new Myviewholder(view);

        myviewholder.container_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,profile_ukuran_data.get(myviewholder.getAdapterPosition()).getId_profile_ukuran(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, Profile_ukuran_detail.class);
                intent.putExtra("id_profile_ukuran",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getId_profile_ukuran());
                intent.putExtra("nama_profile",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getNama_profile());
                intent.putExtra("panjang_dada",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getPanjang_dada());
                intent.putExtra("lingkar_dada",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getLingkar_dada());
                intent.putExtra("lebar_dada",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getLebar_dada());
                intent.putExtra("panjang_lengan",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getPanjang_lengan());
                intent.putExtra("lingkar_lengan",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getLingkar_lengan());
                intent.putExtra("lingkar_pinggul",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getLingkar_pinggul());
                intent.putExtra("panjang_bahu",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getPanjang_bahu());
                intent.putExtra("lingkar_pinggang",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getLingkar_pinggang());
                intent.putExtra("panjang_celana",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getPanjang_celana());
                intent.putExtra("lingkar_celana",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getLingkar_celana());
                intent.putExtra("lingkar_paha",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getLingkar_paha());
                intent.putExtra("jenis_kelamin",profile_ukuran_data.get(myviewholder.getAdapterPosition()).getJenis_kelamin());
                context.startActivity(intent);
                CustomIntent.customType(context, "left-to-right");
            }
        });


        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {

        myviewholder.text_nama_profile.setText(profile_ukuran_data.get(i).getNama_profile());

    }

    @Override
    public int getItemCount() {
        return profile_ukuran_data.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder {

        TextView text_nama_profile;
        LinearLayout container_data;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            text_nama_profile = itemView.findViewById(R.id.nama_profile_ukuran);
            container_data = itemView.findViewById(R.id.container_list);
        }
    }
}

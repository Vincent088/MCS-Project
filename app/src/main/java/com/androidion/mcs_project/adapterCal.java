package com.androidion.mcs_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapterCal extends RecyclerView.Adapter<adapterCal.MyViewHolder>{

    Context ctx;

    ArrayList<Integer> Id;
    ArrayList<String> Tanggal;
    ArrayList<String> Kegiatan;
    ArrayList<String> Waktu;
    ArrayList<String> Kuantitas;
    ArrayList<String> Kalori;
    ArrayList<String> Hasil;


    public adapterCal(Context ctx){
        this.ctx = ctx;
    }
    public void setTanggal (ArrayList<String> Tanggal ){
        this.Tanggal = Tanggal;
    }
    public void setKegiatan (ArrayList<String> Kegiatan ){
        this.Kegiatan = Kegiatan;
    }
    public void setWaktu (ArrayList<String> Waktu ){
        this.Waktu = Waktu;
    }
    public void setId (ArrayList<Integer> Id ){
        this.Id = Id;
    }
    public void setKuantitas (ArrayList<String> Kuantitas ){
        this.Kuantitas = Kuantitas;
    }
    public void setKalori (ArrayList<String> Kalori ){
        this.Kalori = Kalori;
    }
    public void setHasil (ArrayList<String> Hasil ){
        this.Hasil = Hasil;
    }




    @NonNull
    @Override
    public adapterCal.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.frame2,parent,false);
        return new adapterCal.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tanggal.setText ("Tanggal : "  + Tanggal.get(position) );
        holder.kegiatan.setText("Kegiatan : " + Kegiatan.get(position));
        holder.kalori.setText("Kalori Burned : " + Kalori.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Detail.class);
                intent.putExtra("Tanggal", Tanggal.get(position));
                intent.putExtra("Kegiatan", Kegiatan.get(position));
                intent.putExtra("Kalori Burned", Kalori.get(position));
                intent.putExtra("Kuantitas", Kuantitas.get(position));
                intent.putExtra("Waktu", Waktu.get(position));
                intent.putExtra("position",Id.get(position));
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tanggal, kegiatan, kalori;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tanggal = itemView.findViewById(R.id.Tanggal);
            kegiatan = itemView.findViewById(R.id.Kegiatan);
            kalori = itemView.findViewById(R.id.Kalori);

        }
    }
}

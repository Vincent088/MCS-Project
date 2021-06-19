package com.androidion.mcs_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class adapter  extends RecyclerView.Adapter<adapter.MyViewHolder> {

    Context ctx;
    int[] image;
    ArrayList<int[]> Judul;
    ArrayList<String> judul2;
    ArrayList <String> judul3;

    public adapter(Context ctx, int[] image ){
        this.ctx = ctx;
        this.image = image;
        this.judul2 = judul2;

    }

    public void setJudul (ArrayList<int[]> Judul ){
        this.Judul = Judul;
    }
    public void setJudul2 (ArrayList<String> judul2 ){ this.judul2 = judul2;}

    @NonNull
    @Override
    public adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.MyViewHolder holder, final int position) {
        holder.imageItem.setImageResource(image[position]);
//        holder.judul2.setText("Judul : " + judul2.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MainActivity4.class);
                intent.putExtra("Judul", judul2.get(position));
                ctx.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return MainActivity.Judul.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView judul2;
        ImageView imageItem;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            judul2= itemView.findViewById(R.id.judultv);
            imageItem =  itemView.findViewById(R.id.imageItem);
        }

    }
}
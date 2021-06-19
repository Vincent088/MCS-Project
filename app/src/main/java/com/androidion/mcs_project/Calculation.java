package com.androidion.mcs_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Calculation extends AppCompatActivity {

    RecyclerView history;
    Button delete;
    String Idd;
    Database database = new Database(Calculation.this);

    Double total = 0.0;

    ArrayList<Integer> Id = new ArrayList<>();
    ArrayList<String> Tanggal = new ArrayList<>();
    ArrayList<String> Kegiatan = new ArrayList<>();
    ArrayList<String> Waktu = new ArrayList<>();
    ArrayList<String> Kuantitas = new ArrayList<>();
    ArrayList<String> Kalori = new ArrayList<>();
    ArrayList<String> Hasil = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);


        BottomNavigationView navBar = findViewById(R.id.navBar);

        navBar.setSelectedItemId(R.id.Calculation1);
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Home1:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Calculation1:
                        return true;
                }
                return false;
            }
        });


        Cursor cursor = database.readAllData();
        if (cursor.moveToFirst() == true) {
            do {
                Id.add(cursor.getInt(0));
                Tanggal.add(cursor.getString(1));
                Kegiatan.add(cursor.getString(2));
                Waktu.add(cursor.getString(3));
                Kuantitas.add(cursor.getString(4));
                Kalori.add(cursor.getString(5));
                Hasil.add(cursor.getString(6));

            } while (cursor.moveToNext());
        }
            adapterCal adaptercal = new adapterCal(this);

            adaptercal.setId(Id);
            adaptercal.setTanggal(Tanggal);
            adaptercal.setKegiatan(Kegiatan);
            adaptercal.setKalori(Kalori);
            adaptercal.setWaktu(Waktu);
            adaptercal.setKuantitas(Kuantitas);


            //recycle view//
            RecyclerView TH;
            TH = findViewById(R.id.history);
            TH.setAdapter(adaptercal);
            TH.setLayoutManager(new GridLayoutManager(this, 1));

        TextView totalcalories = findViewById(R.id.todaycalories);
        total = database.sumall();
        totalcalories.setText(""+total);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deleteall,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteall){
            confirmdelete();

        }
        return super.onOptionsItemSelected(item);
    }

    void confirmdelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure you want to delete all data ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(getApplicationContext(), "Delete All", Toast.LENGTH_SHORT);
                toast.show();
                Database database = new Database(Calculation.this);
                database.deleteall();
                Intent intent = new Intent(Calculation.this,Calculation.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public ArrayList<String> getKalori() {

        return Kalori;
    }
}
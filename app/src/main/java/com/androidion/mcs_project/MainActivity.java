package com.androidion.mcs_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity {


    static ArrayList<int[]> Judul =new ArrayList<int[]>();
     static ArrayList<String>Judul2 =new ArrayList<>();
    int image [] = {
            R.drawable.push_up,
            R.drawable.sit_up,
            R.drawable.hip_raise,
            R.drawable.leg_raise,
            R.drawable.burpees,
            R.drawable.jumping_jacks,
            R.drawable.squats,
            R.drawable.lunges,
            R.drawable.push_up,

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navBar = findViewById(R.id.navBar);

        navBar.setSelectedItemId(R.id.Home1);
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Calculation1:
                        startActivity(new Intent(getApplicationContext(),Calculation.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Home1:
                        return true;
                }
                return false;
            }
        });

        Judul.add(image);
        Judul.add(image);
        Judul.add(image);
        Judul.add(image);
        Judul.add(image);
        Judul.add(image);
        Judul.add(image);
        Judul.add(image);
        Judul.add(image);

        Judul2.add("Push Up");
        Judul2.add("Sit Up");
        Judul2.add("Hip Raise");
        Judul2.add("Leg Raise");
        Judul2.add("Burpees");
        Judul2.add("Jumping Jacks");
        Judul2.add("Squats");
        Judul2.add("Lunges");
        Judul2.add("Push Up");

        RecyclerView Adapter;
        Adapter =findViewById(R.id.recycle_searchList);

        adapter ad =new adapter(this, image);
        ad.setJudul(Judul);
        ad.setJudul2(Judul2);

        Adapter.setAdapter(ad);
        Adapter.setLayoutManager(new GridLayoutManager(this,1));

    }


}

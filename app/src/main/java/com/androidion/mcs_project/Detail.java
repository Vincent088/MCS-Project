package com.androidion.mcs_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    TextView txt11, txt12, txt13, txt14, txt15;
    String position = "";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        txt11 = findViewById(R.id.txt11);
        txt12 = findViewById(R.id.txt12);
        txt13 = findViewById(R.id.txt13);
        txt14 = findViewById(R.id.txt14);
        txt15 = findViewById(R.id.txt15);

        txt11.setText("Kalori Burned    : " + intent.getStringExtra("Kegiatan"));
        txt12.setText("Kegiatan         : " + intent.getStringExtra("Tanggal") );
        txt13.setText("Kuantitas        : " + intent.getStringExtra("Waktu"));
        txt14.setText("Tanggal          : " + intent.getStringExtra("Kalori Burned"));
        txt15.setText("Waktu            : " + intent.getStringExtra("Kuantitas"));
        int position2 = intent.getIntExtra("position", 0);
        position = String.valueOf(position2);

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmdelete();
            }
        });


    }

    void confirmdelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete This Data");
        builder.setMessage("Are you sure you want to delete this data ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT);
                toast.show();
                Database database = new Database(Detail.this);
                database.delete(position);
                Intent intent = new Intent(Detail.this,Calculation.class);
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



}
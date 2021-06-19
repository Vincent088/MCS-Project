package com.androidion.mcs_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "WorkoutDB.db";
    public final static Integer DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Workout";
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_NAME = "name";
    public static final String TABLE_COLUMN_KALORI = "kalori";
    public static final String TABLE_COLUMN_KUANTITAS = "kuantitas";
    public static final String TABLE_COLUMN_WAKTU = "waktu";
    public static final String TABLE_COLUMN_TANGGAL = "tanggal";
    public static final String TABLE_COLUMN_HASIL = "hasil";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION );
    }

    public void insertSpendingItem(Data new_item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

//        id tidak perlu karena sudah set autoincrement pada onCreate()
        cv.put(TABLE_COLUMN_NAME, new_item.getKegiatan());
        cv.put(TABLE_COLUMN_KALORI,new_item.getKalori());
        cv.put(TABLE_COLUMN_KUANTITAS,new_item.getKuantitas());
        cv.put(TABLE_COLUMN_WAKTU,new_item.getWaktu());
        cv.put(TABLE_COLUMN_TANGGAL,new_item.getTanggal());
        cv.put(TABLE_COLUMN_HASIL,new_item.getHasil());

        db.insert(TABLE_NAME, null, cv);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table_query = "CREATE TABLE " + TABLE_NAME
                + " (" + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_COLUMN_NAME + " TEXT NOT NULL, "
                + TABLE_COLUMN_KALORI + " TEXT NOT NULL, "
                + TABLE_COLUMN_KUANTITAS + " TEXT NOT NULL,"
                + TABLE_COLUMN_WAKTU + " TEXT NOT NULL,"
                + TABLE_COLUMN_TANGGAL + " TEXT NOT NULL,"
                + TABLE_COLUMN_HASIL + " TEXT NOT NULL)";
        db.execSQL(create_table_query);
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void delete(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{row_id});
    }

    void deleteall(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    double sumall(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        double total = 0.0;
        cursor = db.rawQuery("SELECT SUM(" + TABLE_COLUMN_KALORI + ") FROM " + TABLE_NAME, null);
        if(cursor.moveToFirst()){
            total = cursor.getInt(0);
        }
        else{
            total = -1;
        }
        cursor.close();
        return total;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

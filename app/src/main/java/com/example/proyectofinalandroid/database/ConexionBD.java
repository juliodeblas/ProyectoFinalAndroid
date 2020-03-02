package com.example.proyectofinalandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionBD extends SQLiteOpenHelper {

    public ConexionBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA + " ("
                + EsquemaBD.EsquemaPersona.TAG_NOMBRE + " TEXT NOT NULL, "
                + EsquemaBD.EsquemaPersona.TAG_APELLIDOS + " TEXT NOT NULL, "
                + EsquemaBD.EsquemaPersona.TAG_EMAIL + " TEXT NOT NULL, "
                + EsquemaBD.EsquemaPersona.TAG_PASSWORD + " TEXT NOT NULL, "
                + EsquemaBD.EsquemaPersona.TAG_EDAD + " TEXT NOT NULL, "
                + EsquemaBD.EsquemaPersona.TAG_SEXO + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA);
        onCreate(db);
    }


}

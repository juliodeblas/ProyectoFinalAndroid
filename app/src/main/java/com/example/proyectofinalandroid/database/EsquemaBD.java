package com.example.proyectofinalandroid.database;

import android.provider.BaseColumns;

public class EsquemaBD {

    public static String TAG_NOMBRE_BD = "Personas.db";

    public static interface EsquemaPersona extends BaseColumns {
        public static String TAG_NOMBRE_TABLA = "personas";
        public static String TAG_NOMBRE = "nombre";
        public static String TAG_APELLIDOS = "apellidos";
        public static String TAG_EMAIL = "email";
        public static String TAG_PASSWORD = "password";
        public static String TAG_EDAD = "edad";
        public static String TAG_SEXO = "sexo";
    }
}

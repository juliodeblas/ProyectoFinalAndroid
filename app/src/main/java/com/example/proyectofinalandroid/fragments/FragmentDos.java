package com.example.proyectofinalandroid.fragments;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.SecondActivity;
import com.example.proyectofinalandroid.database.ConexionBD;
import com.example.proyectofinalandroid.database.EsquemaBD;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDos extends Fragment implements View.OnClickListener {

    EditText edit_email, edit_password;
    Button boton_eliminar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_dos, container, false);
        edit_email = v.findViewById(R.id.edit_email_eliminar);
        edit_password = v.findViewById(R.id.edit_password_eliminar);
        boton_eliminar = v.findViewById(R.id.boton_eliminar);
        boton_eliminar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_eliminar:
                ConexionBD conexionBD = new ConexionBD(getActivity(), EsquemaBD.TAG_NOMBRE_BD, null, 1);
                SQLiteDatabase database = conexionBD.getReadableDatabase();
                SQLiteDatabase database1 = conexionBD.getWritableDatabase();
                String email = edit_email.getText().toString();
                String password = edit_password.getText().toString();
                Cursor cursor = database.rawQuery("SELECT * FROM " + EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA, null);

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        int i = cursor.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_EMAIL);
                        int j = cursor.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_PASSWORD);
                        if (cursor.getString(i).equals(email) && cursor.getString(j).equals(password)) {
                            database1.delete(EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA, EsquemaBD.EsquemaPersona.TAG_EMAIL + "=? AND " + EsquemaBD.EsquemaPersona.TAG_PASSWORD + "=?", new String[]{email, password});
                            database1.close();
                            Toast.makeText(getActivity(), "Usuario borrado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("error");
                        }
                    } while ((cursor.moveToNext()));
                }

                break;
        }
    }
}

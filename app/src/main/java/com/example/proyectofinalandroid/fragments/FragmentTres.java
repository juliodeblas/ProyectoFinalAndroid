package com.example.proyectofinalandroid.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.adaptadores.AdaptadorRecycler;
import com.example.proyectofinalandroid.database.ConexionBD;
import com.example.proyectofinalandroid.database.EsquemaBD;
import com.example.proyectofinalandroid.utils.Persona;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentTres extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Persona> personas;
    AdaptadorRecycler adaptadorRecycler;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ConexionBD conexionBD = new ConexionBD(getActivity(), EsquemaBD.TAG_NOMBRE_BD, null, 1);
        SQLiteDatabase database = conexionBD.getReadableDatabase();
        //SQLiteDatabase database1 = conexionBD.getWritableDatabase();
        Cursor consultaRegistros = database.rawQuery("SELECT * FROM " + EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA, null);

        View v = inflater.inflate(R.layout.fragment_fragment_tres, container, false);
        recyclerView = v.findViewById(R.id.recycler);
        personas = new ArrayList<>();

        if (consultaRegistros != null && consultaRegistros.moveToFirst()) {
            do {
                String nombre = consultaRegistros.getString(consultaRegistros.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_NOMBRE));
                String apellidos = consultaRegistros.getString(consultaRegistros.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_APELLIDOS));
                String email = consultaRegistros.getString(consultaRegistros.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_EMAIL));
                String password = consultaRegistros.getString(consultaRegistros.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_PASSWORD));
                String edad = consultaRegistros.getString(consultaRegistros.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_EDAD));
                String sexo = consultaRegistros.getString(consultaRegistros.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_SEXO));

                Persona p = new Persona(nombre, apellidos, email, password, edad, sexo);
                personas.add(p);
            } while (consultaRegistros.moveToNext());
        }

        AdaptadorRecycler adaptadorRecycler = new AdaptadorRecycler(getActivity(), personas);
        recyclerView.setAdapter(adaptadorRecycler);
        adaptadorRecycler.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        consultaRegistros.close();
        conexionBD.close();

        return v;
    }

}

package com.example.proyectofinalandroid.fragments;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.database.ConexionBD;
import com.example.proyectofinalandroid.database.EsquemaBD;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUno extends Fragment implements View.OnClickListener {

    EditText edit_nombre, edit_apellidos, edit_email, edit_password, edit_password1, edit_edad;
    RadioGroup radio_group;
    RadioButton radio_hombre, radio_mujer;
    Button boton_registrarme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_uno, container, false);
        edit_nombre = v.findViewById(R.id.edit_nombre_insertar);
        edit_apellidos = v.findViewById(R.id.edit_apellidos_insertar);
        edit_email = v.findViewById(R.id.edit_email_insertar);
        edit_password = v.findViewById(R.id.edit_password_insertar);
        edit_password1 = v.findViewById(R.id.edit_password1_insertar);
        edit_edad = v.findViewById(R.id.edit_edad_insertar);
        radio_group = v.findViewById(R.id.radio_group_insertar);
        radio_hombre = v.findViewById(R.id.radio_hombre_insertar);
        radio_mujer = v.findViewById(R.id.radio_mujer_insertar);
        boton_registrarme = v.findViewById(R.id.boton_registrarme_insertar);
        boton_registrarme.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_registrarme_insertar:
                ConexionBD conexionBD = new ConexionBD(getActivity(), EsquemaBD.TAG_NOMBRE_BD, null, 1);
                SQLiteDatabase database = conexionBD.getReadableDatabase();
                SQLiteDatabase database1 = conexionBD.getWritableDatabase();

                if (!edit_nombre.getText().toString().equals("") &&
                        !edit_apellidos.getText().toString().equals("") &&
                        !edit_email.getText().toString().equals("") &&
                        !edit_password.getText().toString().equals("") &&
                        !edit_edad.getText().toString().equals("") &&
                        (radio_hombre.isChecked() || radio_mujer.isChecked()) && (edit_password.getText().toString().equals(edit_password1.getText().toString()))) {

                    String sexo = "";
                    if (radio_hombre.isChecked()) {
                        sexo = "hombre";
                    } else if (radio_mujer.isChecked()) {
                        sexo = "mujer";
                    }

                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(EsquemaBD.EsquemaPersona.TAG_NOMBRE, edit_nombre.getText().toString());
                    nuevoRegistro.put(EsquemaBD.EsquemaPersona.TAG_APELLIDOS, edit_apellidos.getText().toString());
                    nuevoRegistro.put(EsquemaBD.EsquemaPersona.TAG_EMAIL, edit_email.getText().toString());
                    nuevoRegistro.put(EsquemaBD.EsquemaPersona.TAG_PASSWORD, edit_password.getText().toString());
                    nuevoRegistro.put(EsquemaBD.EsquemaPersona.TAG_EDAD, edit_edad.getText().toString());
                    nuevoRegistro.put(EsquemaBD.EsquemaPersona.TAG_SEXO, sexo);
                    database1.insert(EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA, null, nuevoRegistro);
                    database1.close();
                    Toast.makeText(getActivity(), "Datos introducidos correctamente", Toast.LENGTH_SHORT).show();
                    edit_nombre.setText("");
                    edit_apellidos.setText("");
                    edit_email.setText("");
                    edit_password.setText("");
                    edit_password1.setText("");
                    edit_edad.setText("");
                    radio_hombre.setChecked(false);
                    radio_mujer.setChecked(false);
                } else {
                    Toast.makeText(getActivity(), "Debe introducir todos los datos correctamente", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}

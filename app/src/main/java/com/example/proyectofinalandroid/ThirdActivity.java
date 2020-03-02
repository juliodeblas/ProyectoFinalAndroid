package com.example.proyectofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proyectofinalandroid.database.ConexionBD;
import com.example.proyectofinalandroid.database.EsquemaBD;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_nombre, edit_apellidos, edit_email, edit_password, edit_password1, edit_edad;
    Button boton_volver, boton_registrame;
    RadioGroup radio_group;
    RadioButton radio_hombre, radio_mujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        instancias();
        acciones();
    }

    private void acciones() {
        boton_volver.setOnClickListener(this);
        boton_registrame.setOnClickListener(this);
    }

    private void instancias() {
        edit_nombre = findViewById(R.id.edit_nombre);
        edit_apellidos = findViewById(R.id.edit_apellidos);
        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        edit_password1 = findViewById(R.id.edit_password1);
        edit_edad = findViewById(R.id.edit_edad);
        boton_volver = findViewById(R.id.boton_volver);
        boton_registrame = findViewById(R.id.boton_registrarme);
        radio_group = findViewById(R.id.radio_group);
        radio_hombre = findViewById(R.id.radio_hombre);
        radio_mujer = findViewById(R.id.radio_mujer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_volver:
                finish();
                break;
            case R.id.boton_registrarme:
                ConexionBD conexionBD = new ConexionBD(getApplicationContext(), EsquemaBD.TAG_NOMBRE_BD, null, 1);
                SQLiteDatabase database = conexionBD.getReadableDatabase();
                SQLiteDatabase database1 = conexionBD.getWritableDatabase();
                Cursor consultaRegistros = database.rawQuery("SELECT * FROM " + EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA, null);

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
                    Toast.makeText(getApplicationContext(), "Datos introducidos correctamente", Toast.LENGTH_SHORT).show();
                    edit_nombre.setText("");
                    edit_apellidos.setText("");
                    edit_email.setText("");
                    edit_password.setText("");
                    edit_password1.setText("");
                    edit_edad.setText("");
                    radio_hombre.setChecked(false);
                    radio_mujer.setChecked(false);
                } else {
                    Toast.makeText(getApplicationContext(), "Debe introducir todos los datos correctamente", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}

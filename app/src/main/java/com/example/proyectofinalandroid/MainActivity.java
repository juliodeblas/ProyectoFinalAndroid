package com.example.proyectofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinalandroid.database.ConexionBD;
import com.example.proyectofinalandroid.database.EsquemaBD;
import com.example.proyectofinalandroid.utils.Persona;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_email, edit_password;
    Button button_registrar, button_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
    }

    private void acciones() {
        button_iniciar.setOnClickListener(this);
        button_registrar.setOnClickListener(this);
    }

    private void instancias() {
        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        button_iniciar = findViewById(R.id.button_iniciar);
        button_registrar = findViewById(R.id.button_registrar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_iniciar:
                String email = edit_email.getText().toString();
                String password = edit_password.getText().toString();
                ConexionBD conexionBD = new ConexionBD(getApplicationContext(), EsquemaBD.TAG_NOMBRE_BD, null, 1);
                SQLiteDatabase database = conexionBD.getReadableDatabase();
                Cursor cursor = database.rawQuery("SELECT * FROM " + EsquemaBD.EsquemaPersona.TAG_NOMBRE_TABLA, null);

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        int i = cursor.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_EMAIL);
                        int j = cursor.getColumnIndex(EsquemaBD.EsquemaPersona.TAG_PASSWORD);
                        if(cursor.getString(i).equals(email) && cursor.getString(j).equals(password)){
                            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                            startActivity(intent);
                        }else{
                            System.out.println("error");
                        }
                    }while ((cursor.moveToNext()));
                }

                break;
            case R.id.button_registrar:
                Intent intent1 = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent1);
                break;
        }
    }
}

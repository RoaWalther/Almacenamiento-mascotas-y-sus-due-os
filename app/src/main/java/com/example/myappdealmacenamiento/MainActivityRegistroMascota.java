package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappdealmacenamiento.entidades.Usuario;
import com.example.myappdealmacenamiento.utilidades.Utilidades;

import java.util.ArrayList;

public class MainActivityRegistroMascota extends AppCompatActivity {

    private TextView nombreMascota,razaMascota;
    private Spinner comboduenio;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario>personasList;
    ConexionSQLiteHelper con;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_mascota);

        nombreMascota= findViewById(R.id.txtNombreMascota);
        razaMascota= findViewById(R.id.txtRaza);
        comboduenio= findViewById(R.id.comboDuenioMascota);

        ConexionSQLiteHelper con= new ConexionSQLiteHelper
                (getApplicationContext(),"bd_usuarios",null,1);

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,listaPersonas);
        comboduenio.setAdapter(adapter);

        comboduenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListaPersonas() {
        con= new ConexionSQLiteHelper
                (getApplicationContext(),"bd_usuarios",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

        Usuario persona= null;
        personasList = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            personasList.add(persona);
        }

        obtenerList();
    }

    private void obtenerList() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione: ");

        for (int i=0; i< personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }

    }

    public void registroMascota(View view) {
        if( !nombreMascota.getText().toString().isEmpty()||
                !razaMascota.getText().toString().isEmpty()) {
            con= new ConexionSQLiteHelper
                    (getApplicationContext(),"bd_usuarios",null,1);


            SQLiteDatabase db= con.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,nombreMascota.getText().toString());
            values.put(Utilidades.CAMPO_RAZA_MASCOTA,razaMascota.getText().toString());

            int idCombo = (int) comboduenio.getSelectedItemId();

            if (idCombo!=0){
                int idDuenio= personasList.get(idCombo-1).getId();
                values.put(Utilidades.CAMPO_ID_DUENIO,idDuenio);

                Long idResultante= db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);
                Toast.makeText(getApplicationContext(),"id: "+ idResultante,Toast.LENGTH_LONG).show();
                db.close();
            }else{
                Toast.makeText(getApplicationContext(),"Debes seleccionar un dueño ",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Campos Vacios ",Toast.LENGTH_LONG).show();
        }

    }
}
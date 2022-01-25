package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myappdealmacenamiento.entidades.Usuario;
import com.example.myappdealmacenamiento.utilidades.Utilidades;

import java.util.ArrayList;

public class MainConsultarConSpinner extends AppCompatActivity {

    private TextView campoNombre,campoDocumento,campoTel;
    private Spinner combo;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario>personasList;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consultar_con_spinner);

        con= new ConexionSQLiteHelper(getApplicationContext(),
                "bd_usuarios",null,1);

        campoDocumento= findViewById(R.id.txtdocumento);
        campoNombre= findViewById(R.id.txtNombreConsulta);
        campoTel= findViewById(R.id.txtTelconsulta);
        combo = findViewById(R.id.comboPeronas);

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,listaPersonas);
        combo.setAdapter(adapter);

        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    campoDocumento.setText("Documento: "+personasList.get(position-1).getId().toString());
                    campoNombre.setText("Nombre: "+personasList.get(position-1).getNombre());
                    campoTel.setText("telefono: "+personasList.get(position-1).getTelefono());
                }else {
                    campoDocumento.setText("");
                    campoNombre.setText("");
                    campoTel.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private  void consultarListaPersonas(){
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
}
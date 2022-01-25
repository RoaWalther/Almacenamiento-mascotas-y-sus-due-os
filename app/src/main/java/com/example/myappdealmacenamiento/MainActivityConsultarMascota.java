package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myappdealmacenamiento.entidades.Mascotas;
import com.example.myappdealmacenamiento.entidades.Usuario;
import com.example.myappdealmacenamiento.utilidades.Utilidades;

import java.util.ArrayList;

public class MainActivityConsultarMascota extends AppCompatActivity {
    ListView listaViewMascotas;
    ArrayList<String> listInformacion;
    ArrayList<Mascotas> listaMascotas;
    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consultar_mascota);

        listaViewMascotas= findViewById(R.id.listaViewMascotas);

        con = new ConexionSQLiteHelper(getApplicationContext(),
                "bd_usuarios",null,1);

        consulatrListaMascotas();
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item,listInformacion);
        listaViewMascotas.setAdapter(adapter);

        listaViewMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                /*String inf = "Id: "+ listaMascotas.get(pos).getId()+"\n";
                String nombre = "Nombre: "+ listaMascotas.get(pos).getNombre()+"\n";
                String telefono = "Telefono: "+ listaMascotas.get(pos).getTelefono()+"\n";*/

                Mascotas mascota = listaMascotas.get(pos);
                Intent intent = new Intent(getApplicationContext(),DetalleMascotaActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("Mascota",mascota);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
    private void consulatrListaMascotas() {

        con = new ConexionSQLiteHelper(getApplicationContext(),
                "bd_usuarios",null,1);

        SQLiteDatabase db= con.getReadableDatabase();

        Mascotas mascota = null;
        listaMascotas = new ArrayList<Mascotas>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA,
                null);

        while (cursor.moveToNext()){
            mascota= new Mascotas();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDueño(cursor.getInt(3));

            listaMascotas.add(mascota);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listInformacion =new ArrayList<String>();
        for (int i=0;i<listaMascotas.size();i++){
            listInformacion.add(listaMascotas.get(i).getIdMascota()+" - "
                    +listaMascotas.get(i).getNombreMascota());
        }
    }
}
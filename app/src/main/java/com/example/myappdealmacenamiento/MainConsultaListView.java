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
import android.widget.Toast;

import com.example.myappdealmacenamiento.entidades.Usuario;
import com.example.myappdealmacenamiento.utilidades.Utilidades;

import java.util.ArrayList;

public class MainConsultaListView extends AppCompatActivity {
    ListView listaPersonas;
    ArrayList<String> listInformacion;
    ArrayList<Usuario>listaUsusarios;
    ConexionSQLiteHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consulta_list_view);

        listaPersonas= findViewById(R.id.listaViewPersonas);

         con = new ConexionSQLiteHelper(getApplicationContext(),
                 "bd_usuarios",null,1);

         consulatrListaPersona();
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item,listInformacion);
        listaPersonas.setAdapter(adapter);

        listaPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                String inf = "Id: "+ listaUsusarios.get(pos).getId()+"\n";
                String nombre = "Nombre: "+ listaUsusarios.get(pos).getNombre()+"\n";
                String telefono = "Telefono: "+ listaUsusarios.get(pos).getTelefono()+"\n";

                Usuario user = listaUsusarios.get(pos);
                Intent intent = new Intent(MainConsultaListView.this,DetalleUsuarioList.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("Usuario",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
    private void consulatrListaPersona() {

        SQLiteDatabase db= con.getReadableDatabase();

        Usuario usuario = null;
        listaUsusarios = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,
                null);

        while (cursor.moveToNext()){
            usuario= new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            listaUsusarios.add(usuario);

            obtenerLista();
        }

    }

    private void obtenerLista() {
        listInformacion =new ArrayList<String>();
        for (int i=0;i<listaUsusarios.size();i++){
            listInformacion.add(listaUsusarios.get(i).getId()+" - "
                    +listaUsusarios.get(i).getNombre());
        }
    }
}
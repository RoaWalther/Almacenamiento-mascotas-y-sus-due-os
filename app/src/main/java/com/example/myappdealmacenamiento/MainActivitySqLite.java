package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivitySqLite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sq_lite);

        ConexionSQLiteHelper con= new ConexionSQLiteHelper
                (getApplicationContext(),"bd_usuarios",null,1);
    }

    public void enviarUsuario(View view) {
        Intent intent = new Intent(getApplicationContext(),MainRegistroUsuario.class);
        startActivity(intent);
    }

    public void enviarMascota(View view) {
        Intent intent= new Intent(getApplicationContext(),MainActivityRegistroMascota.class);
        startActivity(intent);
    }

    public void consultarUsuario(View view) {
        Intent intent = new Intent(getApplicationContext(),MainConsultaUsuarios.class);
        startActivity(intent);
    }

    public void consultarUsuarioConSpinner(View view) {
        Intent intent = new Intent(getApplicationContext(),MainConsultarConSpinner.class);
        startActivity(intent);
    }

    public void consultarLista(View view) {
        Intent intent = new Intent(getApplicationContext(),MainConsultaListView.class);
        startActivity(intent);
    }

    public void consultarListaMascota(View view) {
        Intent intent= new Intent(getApplicationContext(),MainActivityConsultarMascota.class);
        startActivity(intent);
    }
}
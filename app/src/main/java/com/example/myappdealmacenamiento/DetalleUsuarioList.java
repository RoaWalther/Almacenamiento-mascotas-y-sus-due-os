package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myappdealmacenamiento.entidades.Usuario;

public class DetalleUsuarioList extends AppCompatActivity {

    TextView id,nombre,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario_list);
        id = findViewById(R.id.txtDetalleId);
        nombre= findViewById(R.id.txtDetalleNombre);
        tel= findViewById(R.id.txtDetalleTel);

        Bundle objetoEnviado = getIntent().getExtras();
        Usuario user= null;
        if (objetoEnviado!=null){
            user= (Usuario) objetoEnviado.getSerializable("Usuario");
            id.setText(user.getId().toString());
            nombre.setText(user.getNombre());
            tel.setText(user.getTelefono());
        }
        else{
            id.setText("vacio");
            nombre.setText("vacio");
            tel.setText("vacio");
        }
    }

    public void atras(View view) {
        finish();
    }
}
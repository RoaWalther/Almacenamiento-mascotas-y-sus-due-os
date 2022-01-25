package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappdealmacenamiento.entidades.Mascotas;
import com.example.myappdealmacenamiento.entidades.Usuario;
import com.example.myappdealmacenamiento.utilidades.Utilidades;

public class DetalleMascotaActivity extends AppCompatActivity {
    TextView idUser,nombreUser,telUser;
    TextView idMascota,raza,nombreMascota;

    ConexionSQLiteHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        con = new ConexionSQLiteHelper(getApplicationContext(),
                "bd_usuarios",null,1);

        idUser = findViewById(R.id.txtDetalleId);
        nombreUser= findViewById(R.id.txtDetalleNombre);
        telUser= findViewById(R.id.txtDetalleTel);

        idMascota = findViewById(R.id.txtIdMascota);
        nombreMascota= findViewById(R.id.txtDetalleNombreMascota);
        raza= findViewById(R.id.txtDetalleRaza);


        Bundle objetoEnviado = getIntent().getExtras();
        Mascotas mascota= null;
        if (objetoEnviado!=null){
            mascota= (Mascotas) objetoEnviado.getSerializable("Mascota");
            idMascota.setText(mascota.getIdMascota().toString());
            nombreMascota.setText(mascota.getNombreMascota().toString());
            raza.setText(mascota.getRaza().toString());
            consultarPersona(mascota.getIdDueño());
        }

    }

    private void consultarPersona(Integer idPersona) {
        con = new ConexionSQLiteHelper(getApplicationContext(),
                "bd_usuarios",null,1);
        SQLiteDatabase db= con.getReadableDatabase();
        String[]parametros= {idPersona.toString()};
        String[]campos= {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,
                    Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();

            idUser.setText(idPersona.toString());
            nombreUser.setText(cursor.getString(0));
            telUser.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Usuario no existe",Toast.LENGTH_LONG).show();
            nombreUser.setText("");
            telUser.setText("");
        }


    }

    public void atras(View view) {

        finish();
    }
}
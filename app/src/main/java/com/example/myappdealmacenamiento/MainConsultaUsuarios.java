package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappdealmacenamiento.utilidades.Utilidades;

public class MainConsultaUsuarios extends AppCompatActivity {

    private TextView campoNombre,campoDocumento,campoTel;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consulta_usuarios);

        con= new ConexionSQLiteHelper(getApplicationContext(),
                "bd_usuarios",null,1);

        campoDocumento= findViewById(R.id.txtdocumento);
        campoNombre= findViewById(R.id.txtNombreConsulta);
        campoTel= findViewById(R.id.txtTelconsulta);
    }

    //consulta usuario
    public void consultarUsuario(View view) {

        //consultar();
        consultarSQL();
    }

    private void limpiar() {
        campoTel.setText("");
        campoNombre.setText("");
    }

    private void consultar(){
        SQLiteDatabase db = con.getReadableDatabase();
        String[] parametros = {campoDocumento.getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,
                    Utilidades.CAMPO_ID+"=?",parametros,
                    null,null,null);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTel.setText(cursor.getString(1));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No existe Registro ",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void consultarSQL(){
        SQLiteDatabase db = con.getReadableDatabase();
        String[] parametros = {campoDocumento.getText().toString()};

        try {//select nombre,telefono from tabla_usuarios where codigo=?
            Cursor cursor= db.rawQuery("SELECT " +Utilidades.CAMPO_NOMBRE +","+Utilidades.CAMPO_TELEFONO +
                   " FROM " +Utilidades.TABLA_USUARIO +" WHERE " +Utilidades.CAMPO_ID +"=? ",parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTel.setText(cursor.getString(1));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No existe Registro ",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    //eliminar usuario
    public void eliminarUsuario(View view) {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {campoDocumento.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=? ",parametros);
        Toast.makeText(getApplicationContext(),"se elimino Registro ",Toast.LENGTH_LONG).show();
        db.close();
        campoTel.setText("");
        campoNombre.setText("");
        campoDocumento.setText("");
    }

    //actualizar usuarios
    public void actualizarUsuario(View view) {

        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {campoDocumento.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTel.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=? ",parametros);

        Toast.makeText(getApplicationContext(),"se actualizo Registro ",Toast.LENGTH_LONG).show();
        db.close();
    }
}
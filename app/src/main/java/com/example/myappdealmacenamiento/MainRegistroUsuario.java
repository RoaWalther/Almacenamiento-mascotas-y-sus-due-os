package com.example.myappdealmacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappdealmacenamiento.utilidades.Utilidades;

public class MainRegistroUsuario extends AppCompatActivity {

    private TextView campoNombre,campoId,campoTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_usuario);

        campoId= findViewById(R.id.txtCampoId);
        campoNombre= findViewById(R.id.txtCampoNombre);
        campoTel= findViewById(R.id.txtCampoTel);
    }

    public void registroUsuario(View view) {
        try {

            registroUserMetodo_Insert();
            //registroUserMetodo_ContentValues();
        }catch (Exception e){

        }

    }

    private void registroUserMetodo_ContentValues(){
        if(!campoId.getText().toString().isEmpty() || !campoNombre.getText().toString().isEmpty()||
                !campoTel.getText().toString().isEmpty()) {
            ConexionSQLiteHelper con= new ConexionSQLiteHelper
                    (getApplicationContext(),"bd_usuarios",null,1);

            SQLiteDatabase db= con.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
            values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_TELEFONO,campoTel.getText().toString());

            Long idResultante= db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);
            Toast.makeText(getApplicationContext(),"id: "+ idResultante,Toast.LENGTH_LONG).show();
            db.close();

        }else{
            Toast.makeText(getApplicationContext(),"Campos Vacios ",Toast.LENGTH_LONG).show();
        }
    }



    private void registroUserMetodo_Insert() {

        if(!campoId.getText().toString().isEmpty() ||
                !campoNombre.getText().toString().isEmpty()||
                !campoTel.getText().toString().isEmpty()) {

            ConexionSQLiteHelper con= new ConexionSQLiteHelper
                    (getApplicationContext(),"bd_usuarios",null,1);

            SQLiteDatabase db= con.getWritableDatabase();

            String insert = "INSERT INTO "+ Utilidades.TABLA_USUARIO +"(" +Utilidades.CAMPO_ID
                    +","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+ ") VALUES (" +
                    campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                            + campoTel.getText().toString()+"')";
            db.execSQL(insert);
            Toast.makeText(getApplicationContext(),"id: "+ insert,Toast.LENGTH_LONG).show();
            db.close();

        }else{
            Toast.makeText(getApplicationContext(),"Campos Vacios ",Toast.LENGTH_LONG).show();
        }
    }

}
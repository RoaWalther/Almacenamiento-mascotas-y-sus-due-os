package com.example.myappdealmacenamiento.sharePreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappdealmacenamiento.R;

public class MainActivity extends AppCompatActivity {
    private EditText producto1, producto2,producto3;
    public static final String agenda="Datos";
    public static final float valorProducto=15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        producto1= findViewById(R.id.txtProducto1);
        producto2= findViewById(R.id.txtProducto2);
        producto3= findViewById(R.id.txtProducto3);

        cargarPreferencias();
    }

    public void guardarPreferences(View view) {

        SharedPreferences preferences= getSharedPreferences(
                agenda, Context.MODE_PRIVATE);
        String prod1= producto1.getText().toString();
        String prod2= producto2.getText().toString();
        String prod3= producto3.getText().toString();

        if (prod1.isEmpty() || prod2.isEmpty() || prod3.isEmpty()){
            Toast.makeText(getApplicationContext(),"campos vacios",Toast.LENGTH_LONG).show();
        }
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Producto1",prod1);
        editor.putString("Producto2",prod2);
        editor.putString("Producto3",prod3);

        editor.commit();
    }
    public void enviarPreferences(View view) {
        Intent miIntent = new Intent(getApplicationContext(), MainConsultaPreferencias.class);

        float totalProd1 = valorProducto*Float.parseFloat(producto1.getText().toString());
        float totalProd2 = valorProducto*Float.parseFloat(producto2.getText().toString());
        float totalProd3 = valorProducto*Float.parseFloat(producto3.getText().toString());
        miIntent.putExtra("TotalProd1",totalProd1);
        miIntent.putExtra("TotalProd2",totalProd2);
        miIntent.putExtra("TotalProd3",totalProd3);
        startActivity(miIntent);
    }

    private void cargarPreferencias() {
        SharedPreferences preferences= getSharedPreferences(
                "credenciales", Context.MODE_PRIVATE);

        String  prod1= preferences.getString("Producto1","No existe informacio");
        String  prod2= preferences.getString("Producto2","No existe informacio");
        String  prod3= preferences.getString("Producto3","No existe informacio");


    }
}
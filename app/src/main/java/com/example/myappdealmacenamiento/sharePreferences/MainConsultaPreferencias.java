package com.example.myappdealmacenamiento.sharePreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappdealmacenamiento.R;

public class MainConsultaPreferencias extends AppCompatActivity {
    private TextView mostrarN;
    public static final String agenda="Datos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consulta_preferencias);
        mostrarN= findViewById(R.id.mostarN);
        cargarPreferencias();
    }
    private void cargarPreferencias() {
        SharedPreferences preferences= getSharedPreferences(
                agenda, Context.MODE_PRIVATE);

        String  prod1= preferences.getString("Producto1","No existe informacio");
        String  prod2= preferences.getString("Producto2","No existe informacio");
        String  prod3= preferences.getString("Producto3","No existe informacio");

        Bundle miBundle = this.getIntent().getExtras();
        if (miBundle!= null) {

            float valorProd1 = miBundle.getFloat("TotalProd1");
            float valorProd2 = miBundle.getFloat("TotalProd2");
            float valorProd3 = miBundle.getFloat("TotalProd3");
            mostrarN.setText("Producto1: \n Cantidad: " +  prod1 + "\nValor Producto1: "+ valorProd1+
                    "\nProducto2: \n Cantidad: " +  prod2 +"\nValor Producto2: "+ valorProd2+
                    "\nProducto3: \n Cantidad: " +  prod3+"\nValor Producto3: "+ valorProd3);
        }
    }

    public void regresar(View view) {
        finish();
    }
}
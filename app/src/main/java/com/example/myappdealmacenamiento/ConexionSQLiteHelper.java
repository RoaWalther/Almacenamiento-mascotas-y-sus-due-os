package com.example.myappdealmacenamiento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myappdealmacenamiento.utilidades.Utilidades;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("CREATE TABLE usuarios(id INTEGER,nombre TEXT,telefono TEXT)");
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //si des-instala y vuelve a instalar la app y ya existe la tabla
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MASCOTA);
        //se llama al metodo onCreate para volver a crearla despues de borrarla
        onCreate(db);
    }
}

package com.example.dm2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Sqlite extends AppCompatActivity {

    private Contacto[] datos = new Contacto[]{
            new Contacto("Andoni","Alda","123459876")
    };
    private ListView lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        Sqlitehelper usdbh = new Sqlitehelper(this,"DBContactos",null,1);

        AdaptadorContactos adaptador = new AdaptadorContactos(this,datos);
        lista = (ListView)findViewById(R.id.list);
        lista.setAdapter(adaptador);
    }

    public void insertar (){}

    public void actualizar (){}

    public void eliminar (){}

    public void consultar (){}

    public class Sqlitehelper extends SQLiteOpenHelper
    {
        private String sqlCreate = "CREATE TABLE Contactos (idContacto INTEGER PRIMARY KEY, nombre TEXT, apellidos TEXT, numero INTEGER )";
        public Sqlitehelper (Context context, String nombre, SQLiteDatabase.CursorFactory factory,int version)
        {
            super(context,nombre,factory,version);
        }
        public void onCreate (SQLiteDatabase db)
        {
            db.execSQL(sqlCreate);
        }
        public void onUpgrade(SQLiteDatabase db, int i, int i1)
        {
            db.execSQL("DROP TABLE IF EXISTS Contactos");
            db.execSQL(sqlCreate);
        }
    }

    public class Contacto    {
        private String nombre;
        private String apellido1;
        private String apellido2;
        private String numero;

        public Contacto(String nombre, String apellido1,String apellido2, String numero) {
            this.nombre = nombre;
            this.apellido1 = apellido1;
            this.apellido2 = apellido2;
            this.numero = numero;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido1() {
            return apellido1;
        }
        public String getApellido2() {
            return apellido2;
        }

        public String getNumero() {
            return numero;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setApellido1(String apellido1) {
            this.apellido1 = apellido1;
        }

        public void setApellido2(String apellido2) {
            this.apellido2 = apellido2;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }
    }
    public class AdaptadorContactos extends ArrayAdapter<Contacto>
    {
        public AdaptadorContactos(Context context, Contacto[] datos) {
            super(context,R.layout.lista,datos);
        }
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.lista,null);

            TextView lbl1= (TextView) item.findViewById(R.id.lbl1);
            TextView lbl2= (TextView) item.findViewById(R.id.lbl2);

            lbl1.setText(datos[position].getNombre()+" "+datos[position].getApellido1()+" "+datos[position].getApellido2());
            lbl2.setText(datos[position].getNumero());

            return item;
        }
    }
}

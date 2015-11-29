package com.example.usuario.proyectoimagenesweb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Principal extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        inicio();
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }

    private EditText ed;
    public void inicio(){
        ed=(EditText) findViewById(R.id.editText);
        String s="http://www.istockphoto.com/photos/nature";
        String c="www.cuantarazon.com/ultimos/p/2";
        String m="www.marca.es";
        ed.setText(c);
    }
    public void aceptar(View v){
        String url=ed.getText().toString();
            Intent i=new Intent(this,DescargarImg.class);
            Bundle b = new Bundle();
            b.putString("url",url);
        i.putExtras(b);
        startActivity(i);
    }
    public void limpiar(View v){
        ed.setText("");
    }

}

package com.universidadcauca.movil.menus;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements DialogInterface.OnClickListener{

    ListView list;
    ArrayAdapter<String> adapter;
    String[] data;

    public static List<String> dataPostre;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);

        data = getResources().getStringArray(R.array.postres);
        dataPostre = new ArrayList<>();

        for(int i = 0; i<data.length;i++){
            dataPostre.add(data[i]);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1
                ,dataPostre);

        list.setAdapter(adapter);

        registerForContextMenu(list);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()){
            case R.id.action_add:

                Intent intent = new Intent(this, AddPostreActivity.class);
                startActivity(intent);

                break;
            case R.id.action_about:
                Toast.makeText(this, "Seleccionaste Acerca de", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_info:
                Toast.makeText(this, "Seleccionaste informacion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_more:
                Toast.makeText(this, "Seleccionaste mas", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_ctx_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        pos = info.position;

        switch (item.getItemId()){
            case R.id.action_edit:
                Intent intent = new Intent(this, EditPostreActivity.class);
                intent.putExtra("position", pos);
                startActivity(intent);

                break;
            case R.id.action_delete:
                showAlertDelete();
                break;

        }

        return super.onContextItemSelected(item);
    }

    private void showAlertDelete() {

        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("Eliminar Postre")
                .setMessage("Desea eleminar el postre ?")
                .setNegativeButton("Cancelar", this)
                .setPositiveButton("Aceptar",this)
                .create();

        alert.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE){
           dataPostre.remove(pos);
           adapter.notifyDataSetChanged();
        }
    }
}

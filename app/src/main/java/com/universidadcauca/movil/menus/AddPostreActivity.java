package com.universidadcauca.movil.menus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddPostreActivity extends ActionBarActivity implements View.OnClickListener {

    EditText edit;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_postre);

        edit= (EditText) findViewById(R.id.edit_postre);
        btn = (Button) findViewById(R.id.btn_aceptar);
        
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        MainActivity.dataPostre.add(edit.getText().toString());
        finish();
    }
}

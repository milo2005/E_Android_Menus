package com.universidadcauca.movil.menus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditPostreActivity extends ActionBarActivity implements View.OnClickListener {

    EditText edit;
    Button btn;
    int pos;
    String postre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_postre);

        Bundle extras = getIntent().getExtras();

        if(extras != null)
            pos = extras.getInt("position");

        edit = (EditText) findViewById(R.id.edit_postre);
        btn = (Button) findViewById(R.id.btn_aceptar);

        btn.setOnClickListener(this);

        postre = MainActivity.dataPostre.get(pos);
        edit.setText(postre);
    }
    @Override
    public void onClick(View v) {

        postre = edit.getText().toString();
        MainActivity.dataPostre.remove(pos);
        MainActivity.dataPostre.add(pos, postre);

        finish();
    }
}

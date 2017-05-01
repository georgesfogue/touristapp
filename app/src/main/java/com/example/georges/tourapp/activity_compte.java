package com.example.georges.tourapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class activity_compte extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerSimpleViewAdapter adapter;
    List<String> items = new ArrayList<String>();
    private Button btnadd;
    private Button btnQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        //Création d'une instance de la classe UserDB
        final ActivityDB actvdb = new ActivityDB(this);
        //final user users = new user(); //Création d'un utilisateur


        final user loguser = getIntent().getExtras().getParcelable("user");
        btnadd = (Button) findViewById(R.id.myButtonSimpleAdd);
        btnQ = (Button) findViewById(R.id.btquit);

        recyclerView = (RecyclerView) findViewById(R.id.myListSimple);

        actvdb.open();

        List<activite> listact = actvdb.getAllActivite();
        actvdb.close();
        for (activite ac : listact) {
            items.add(ac.getNomPays() + " - " + ac.getNomVille() + "\n" + ac.getActivite()
                    + "\n" + "Adresse : " + ac.getAddresse() + "\n" + "Horaire d'ouverture : " + ac.getHorairedebut()
                    + " - " + ac.getHorairefin() + "\n" + "\n" + "Description : " + "\n" + ac.getDescription());
        }

        //activite Activ = actvdb.getActivite(loguser.getEmail());

        //items.add(Activ.getNomPays() + " - " + Activ.getNomVille() + "\n" + Activ.getActivite() + "\n" + Activ.getDescription());

        /*ResultSet r = actvdb.getResultOf("SELECT * from table_act where USRMAIL = '"+loguser.getEmail()+"'");
        try {
            while (r.next()){
                items.add(r.getString("PAYS") + " - " + r.getString("VILLES") + "\n" + r.getString("TITLE"));
            }
        } catch (SQLException ex) {
        }*/

        /*for (int i = 0; i < 27; i++) {
            // new item
            items.add(" France - Paris \nVisite Musé du louvre " + i);
        }*/
        adapter = new RecyclerSimpleViewAdapter(items, android.R.layout.simple_list_item_1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnadd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                user usr = new user(loguser.getEmail(), loguser.getMotDePasse());
                Intent i = new Intent(getApplicationContext(), activity_add.class);
                i.putExtra("user", usr);
                startActivity(i);
                finish();
            }
        });

        btnQ.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //user usr = new user(loguser.getEmail(), loguser.getMotDePasse());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //i.putExtra("user", usr);
                startActivity(i);
                finish();
            }
        });
    }
}

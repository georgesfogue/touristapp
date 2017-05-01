package com.example.georges.tourapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Created by Georges on 30/04/2017.
 */

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
        final user loguser = getIntent().getExtras().getParcelable("user");
        btnadd = (Button) findViewById(R.id.myButtonSimpleAdd);
        btnQ = (Button) findViewById(R.id.btquit);

        recyclerView = (RecyclerView) findViewById(R.id.myListSimple);

        for (int i = 0; i < 27; i++) {
            // new item
            items.add(" France - Paris \nVisite Musé du louvre " + i);
        }
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
    /*public void add(ViewModel item, int position) {
        items.add(position, item); // on insère le nouvel objet dans notre       liste d'article lié à l'adapter
        adapter.notifyItemInserted(position); // on notifie à l'adapter ce changement
    }*/
}

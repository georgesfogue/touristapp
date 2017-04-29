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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        btnadd = (Button) findViewById(R.id.myButtonSimpleAdd);

        recyclerView = (RecyclerView) findViewById(R.id.myListSimple);

        for (int i = 0; i < 6; i++) {
            // new item
            items.add("test " + i);
        }
        adapter = new RecyclerSimpleViewAdapter(items, android.R.layout.simple_list_item_1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnadd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_add.class);
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

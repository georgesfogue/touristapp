package com.example.georges.tourapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Georges on 30/04/2017.
 */

public class activity_add extends AppCompatActivity {
    private static final String TAG = activity_register.class.getSimpleName();
    private Button bt_valid;
    private Button bt_cancel;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);*/

        bt_valid = (Button) findViewById(R.id.btvalid);
        bt_cancel = (Button) findViewById(R.id.btquit);

        // Link to Register Screen
        bt_valid.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_compte.class);
                startActivity(i);
                finish();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_compte.class);
                startActivity(i);
                finish();
            }
        });
    }
}

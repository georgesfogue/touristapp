package com.example.georges.tourapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Georges on 29/04/2017.
 */

public class activity_register extends AppCompatActivity {

    private static final String TAG = activity_register.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Création d'une instance de la classe UserDB
        final UserDB userdb = new UserDB(this);
        final Users users = new Users(); //Création d'un utilisateur
        userdb.open(); // on ouvre la base de donnee pour ecrire

        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(activity_register.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    users.setNom(name);
                    users.setEmail(email);
                    users.setMotDePasse(password);


                    Users userfromdb = userdb.getUserWithName(users.getEmail());
                    //Si un utilisateur est retourné (donc si l'utilisateur  à bien été ajouté à la BDD)
                    if(userfromdb != null){
                        //On affiche les infos de l'utilisateur dans un Toast
                        Toast.makeText(getApplicationContext(),"Utilisateur deja inscrit au nom de " + userfromdb.getNom() + "Connecter Vous", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }else {
                        userdb.insertUser(users);
                        Toast.makeText(getApplicationContext(),
                                "Utilisateur ajouter Connecter vous!", Toast.LENGTH_LONG)
                                .show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }


                } else {
                    Toast.makeText(getApplicationContext(),
                            "SVP remplir tous les champs!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }
}

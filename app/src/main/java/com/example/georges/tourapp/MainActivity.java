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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private Button btnLogout;

    private Button btnLinkToRegister;
    private Button btnconect;

    TextView txtStatus;
    LoginButton login_button;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        //Création d'une instance de la classe UserDB
        final UserDB userdb = new UserDB(this);
        final user users = new user(); //Création d'un utilisateur
        userdb.open(); // on ouvre la base de donnee pour ecrire

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        btnconect = (Button)findViewById(R.id.btnLogin);

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_register.class);
                startActivity(i);
                finish();
            }
        });

        // Gestion connexion
        btnconect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                user logusr = new user();
                logusr.setEmail(email);
                logusr.setMotDePasse(password);

                if (!email.isEmpty() && !password.isEmpty()) {
                    user utilisateur = userdb.getUserWithName(email);

                    if(utilisateur != null){
                        if(logusr.getMotDePasse().equals(utilisateur.getMotDePasse())){
                            Toast.makeText(getApplicationContext(),
                                    "Mot de passe correct  ", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), activity_compte.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Mot de passe incorret  " + utilisateur.getMotDePasse() + " = " + logusr.getMotDePasse(), Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "Cet email non connu inscriver vous!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SVP remplir tous les champs!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        initializeControls();
        loginwithFB();

    }

    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        login_button = (LoginButton)findViewById(R.id.login_button);
    }
    private void loginwithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //txtStatus.setText("Connexion reussi\n" + loginResult.getAccessToken());
                Intent i = new Intent(getApplicationContext(), activity_compte.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Connexion Annulee.");
            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Erreur de Connexion : " + error.getMessage());
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

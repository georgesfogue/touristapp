package com.example.georges.tourapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Georges on 30/04/2017.
 */

public class activity_add extends AppCompatActivity {
    private static final String TAG = activity_register.class.getSimpleName();
    private Button bt_valid;
    private Button bt_cancel;
    private EditText pays;
    private EditText ville;
    private EditText activite;
    private EditText adresse;
    private EditText heuredebut;
    private EditText heurefin;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final user usrs = getIntent().getExtras().getParcelable("user");
        //final user usrss = getIntent().getExtras().getParcelable("users");
        //Création d'une instance de la classe UserDB
        final ActivityDB actvdb = new ActivityDB(this);
        final activite activ = new activite(); //Création d'un utilisateur
        actvdb.open(); // on ouvre la base de donnee pour ecrire

        pays = (EditText) findViewById(R.id.namepay);
        ville = (EditText) findViewById(R.id.namevil);
        activite = (EditText) findViewById(R.id.activite);
        adresse = (EditText) findViewById(R.id.editTextaddr);
        heuredebut = (EditText) findViewById(R.id.heurdebut);
        heurefin = (EditText) findViewById(R.id.heurfin);
        description = (EditText) findViewById(R.id.edit_texto);


        bt_valid = (Button) findViewById(R.id.btvalid);
        bt_cancel = (Button) findViewById(R.id.btquit);

        // Link to Register Screen
        bt_valid.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String Pays = pays.getText().toString().trim();
                String Ville = ville.getText().toString().trim();
                String Activite = activite.getText().toString().trim();
                String Adresse = adresse.getText().toString().trim();
                String Heuredebut = heuredebut.getText().toString().trim();
                String Heurefin = heurefin.getText().toString().trim();
                String Description = description.getText().toString().trim();

                if (!Pays.isEmpty() && !Ville.isEmpty() && !Activite.isEmpty() && !Adresse.isEmpty() && !Heuredebut.isEmpty() && !Heurefin.isEmpty() && !Description.isEmpty()) {

                    activ.setNomPays(Pays);
                    activ.setNomVille(Ville);
                    activ.setActivite(Activite);
                    activ.setAddresse(Adresse);
                    activ.setHorairedebut(Heuredebut);
                    activ.setHorairefin(Heurefin);
                    activ.setDescription(Description);
                    activ.setUseremail(usrs.getEmail());

                    actvdb.insertActivite(activ);
                    actvdb.close();
                    Toast.makeText(getApplicationContext(),
                            "L'activité à bien été ajouter!", Toast.LENGTH_LONG)
                            .show();
                    user usro = new user(usrs.getEmail(), usrs.getMotDePasse());
                    Intent i = new Intent(getApplicationContext(), activity_compte.class);
                    i.putExtra("user", usro);
                    startActivity(i);
                    finish();

                    /*activite activfromdb = actvdb.getActivite(activ.getActivite());
                    //Si un utilisateur est retourné (donc si l'utilisateur  à bien été ajouté à la BDD)
                    if(activfromdb != null){
                        //On affiche les infos de l'utilisateur dans un Toast
                        Toast.makeText(getApplicationContext(),"Cette activité existe déjà ", Toast.LENGTH_LONG).show();
                        user usro = new user(usrs.getEmail(), usrs.getMotDePasse());
                        Intent i = new Intent(getApplicationContext(), activity_add.class);
                        i.putExtra("user", usro);
                        startActivity(i);
                        finish();

                    }else {
                        actvdb.insertActivite(activ);
                        actvdb.close();
                        Toast.makeText(getApplicationContext(),
                                "L'activité à bien été ajouter!", Toast.LENGTH_LONG)
                                .show();
                        user usro = new user(usrs.getEmail(), usrs.getMotDePasse());
                        Intent i = new Intent(getApplicationContext(), activity_compte.class);
                        i.putExtra("user", usro);
                        startActivity(i);
                        finish();
                    }*/


                } else {
                    Toast.makeText(getApplicationContext(),
                            "SVP remplir tous les champs!" + usrs.getEmail(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                user usr = new user(usrs.getEmail(), usrs.getMotDePasse());
                Intent i = new Intent(getApplicationContext(), activity_compte.class);
                i.putExtra("user", usr);
                startActivity(i);
                finish();
            }
        });
    }
}

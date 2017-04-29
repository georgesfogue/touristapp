package com.example.georges.tourapp;

/**
 * Created by Georges on 29/04/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDB {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "DBtour.db";

    private static final String TABLE_USER = "table_user";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "NOM";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_EMAIL = "EMAIL";
    private static final int NUM_COL_EMAIL = 2;
    private static final String COL_PASSWORD = "PASSWORD";
    private static final int NUM_COL_PASSWORD = 3;

    private SQLiteDatabase bdd;

    private Database maBaseSQLite;

    public UserDB(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new Database(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertUser(user users){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NOM, users.getNom());
        values.put(COL_EMAIL, users.getEmail());
        values.put(COL_PASSWORD, users.getMotDePasse());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_USER, null, values);
    }

    public int updateUser(int id, user users){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel utilisateur on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_NOM, users.getNom());
        values.put(COL_EMAIL, users.getEmail());
        values.put(COL_PASSWORD, users.getMotDePasse());
        return bdd.update(TABLE_USER, values, COL_ID + " = " +id, null);
    }

    public int removeUserID(int id){
        //Suppression d'un utilisateur de la BDD grâce à l'ID
        return bdd.delete(TABLE_USER, COL_ID + " = " +id, null);
    }

    public user getUserWithName(String email){
        //Récupère dans un Cursor les valeurs correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son nom)
        Cursor c = bdd.query(TABLE_USER, new String[] {COL_ID, COL_NOM, COL_EMAIL, COL_PASSWORD}, COL_EMAIL + " LIKE \"" + email +"\"", null, null, null, null, null);
        return cursorToUser(c);
    }

    //Cette méthode permet de convertir un cursor en un utilisateur
    private user cursorToUser(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        user usr = new user();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        usr.setId(c.getInt(NUM_COL_ID));
        usr.setNom(c.getString(NUM_COL_NOM));
        usr.setEmail(c.getString(NUM_COL_EMAIL));
        usr.setMotDePasse(c.getString(NUM_COL_PASSWORD));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return usr;
    }

}

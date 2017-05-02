package com.example.georges.tourapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ActivityDB {

    private static final int VERSION_BDDB = 2;
    private static final String NOM_BDD = "DBtouract.db";

    private static final String TABLE_ACT = "Activite";
    private static final String COL_IDACT = "id";
    private static final int NUM_COL_id = 0;
    private static final String COL_USRMAIL = "USRMAIL";
    private static final int NUM_COL_USRMAIL = 1;
    private static final String COL_PAYS = "PAYS";
    private static final int NUM_COL_PAYS = 2;
    private static final String COL_VILLES = "VILLES";
    private static final int NUM_COL_VILLES = 3;
    private static final String COL_ADD = "ADRESSE";
    private static final int NUM_COL_ADD = 4;
    private static final String COL_TITLE = "TITLE";
    private static final int NUM_COL_TITLE = 5;
    private static final String COL_DESCR = "DESCRIPTION";
    private static final int NUM_COL_DESCR = 6;
    private static final String COL_HOPEN = "HOPEN";
    private static final int NUM_COL_HOPEN = 7;
    private static final String COL_HCLOSE = "HCLOSE";
    private static final int NUM_COL_HCLOSE = 8;

    private SQLiteDatabase bdd;
    private DatabaseAct maBaseSQLite;

    public ActivityDB(Context context) {
        //On crée la BDD et sa table
        maBaseSQLite = new DatabaseAct(context, NOM_BDD, null, VERSION_BDDB);
    }

    public void open() {
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }


    public long insertActivite(activite activ) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_USRMAIL, activ.getUseremail());
        values.put(COL_PAYS, activ.getNomPays());
        values.put(COL_VILLES, activ.getNomVille());
        values.put(COL_ADD, activ.getAddresse());
        values.put(COL_HOPEN, activ.getHorairedebut());
        values.put(COL_HCLOSE, activ.getHorairefin());
        values.put(COL_DESCR, activ.getDescription());
        values.put(COL_TITLE, activ.getActivite());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_ACT, null, values);
    }

    public int updateActivite(int id, activite act) {
        ContentValues values = new ContentValues();
        values.put(COL_USRMAIL, act.getUseremail());
        values.put(COL_PAYS, act.getNomPays());
        values.put(COL_VILLES, act.getNomVille());
        values.put(COL_ADD, act.getAddresse());
        values.put(COL_HOPEN, act.getHorairedebut());
        values.put(COL_HCLOSE, act.getHorairefin());
        values.put(COL_DESCR, act.getDescription());
        values.put(COL_TITLE, act.getActivite());
        return bdd.update(TABLE_ACT, values, COL_IDACT + " = " + id, null);
    }

    public int removeActivite(int id) {
        //Suppression d'un utilisateur de la BDD grâce à l'ID
        return bdd.delete(TABLE_ACT, COL_IDACT + " = " + id, null);
    }


    // Getting All Contacts
    public List<activite> getAllActivite(String email) {
        List<activite> ActiviteList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ACT + " WHERE TRIM(" + COL_USRMAIL + ")  = '" + email.trim() + "'";
        //Cursor c = db.rawQuery("SELECT * FROM tbl1 WHERE TRIM(name) = '"+name.trim()+"'", null);

        //SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = bdd.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                activite Activ = new activite();
                Activ.setUseremail(cursor.getString(1));
                Activ.setNomPays(cursor.getString(2));
                Activ.setNomVille(cursor.getString(3));
                Activ.setAddresse(cursor.getString(4));
                Activ.setActivite(cursor.getString(5));
                Activ.setDescription(cursor.getString(6));
                Activ.setHorairedebut(cursor.getString(7));
                Activ.setHorairefin(cursor.getString(8));
                // Adding contact to list
                ActiviteList.add(Activ);
            } while (cursor.moveToNext());
        }
        // return contact list
        return ActiviteList;

    }

    public activite getActivite(String titre) {
        //Récupère dans un Cursor les valeurs correspondant à une activite contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son nom)
        Cursor c = bdd.query(TABLE_ACT, new String[]{COL_IDACT, COL_USRMAIL, COL_PAYS, COL_VILLES}, COL_USRMAIL + " LIKE \"" + titre + "\"", null, null, null, null, null);
        return cursorToactivite(c);
    }

    //Cette méthode permet de convertir un cursor en un utilisateur
    private activite cursorToactivite(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un utilisateur
        activite usract = new activite();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        usract.setId(c.getInt(NUM_COL_id));
        usract.setUseremail(c.getString(NUM_COL_USRMAIL));
        usract.setNomPays(c.getString(NUM_COL_PAYS));
        usract.setNomVille(c.getString(NUM_COL_VILLES));
        usract.setAddresse(c.getString(NUM_COL_ADD));
        usract.setActivite(c.getString(NUM_COL_TITLE));
        usract.setDescription(c.getString(NUM_COL_DESCR));
        usract.setHorairedebut(c.getString(NUM_COL_HOPEN));
        usract.setHorairefin(c.getString(NUM_COL_HCLOSE));

        //On ferme le cursor
        c.close();

        //On retourne le livre
        return usract;
    }

}

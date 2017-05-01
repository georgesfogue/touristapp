package com.example.georges.tourapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by Georges on 01/05/2017.
 */
public class DatabaseAct extends SQLiteOpenHelper {

    private static final String TAG = SQLiteOpenHelper.class.getSimpleName();
    private static final boolean DEBUG_STRICT_READONLY = false;
    private boolean mIsInitializing;
    private boolean mEnableWriteAheadLogging;

    private static final String TABLE_ACT = "Activite";
    private static final String COL_IDACT = "id";
    private static final String COL_USRMAIL = "USRMAIL";
    private static final String COL_PAYS = "PAYS";
    private static final String COL_VILLES = "VILLES";
    private static final String COL_ADD = "ADRESSE";
    private static final String COL_HOPEN = "HOPEN";
    private static final String COL_HCLOSE = "HCLOSE";
    private static final String COL_DESCR = "DESCRIPTION";
    private static final String COL_TITLE = "TITLE";

    private static final String CREATE_ACTBD = "CREATE TABLE " + TABLE_ACT + " ("
            + COL_IDACT + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_USRMAIL + " TEXT NOT NULL, "
            + COL_PAYS + " TEXT NOT NULL, " + COL_VILLES + " TEXT NOT NULL, "
            + COL_ADD + " TEXT NOT NULL, " + COL_TITLE + " TEXT NOT NULL, "
            + COL_DESCR + " TEXT NOT NULL, " + COL_HOPEN + " TEXT," + COL_HCLOSE + " TEXT);";

    public DatabaseAct(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_ACTBD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_ACT + ";");
        onCreate(db);
    }

}
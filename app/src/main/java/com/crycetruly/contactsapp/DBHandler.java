package com.crycetruly.contactsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Elia on 06/06/2018.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final String DBNAME="myContacts";
    private static final int DBVERSION=1;
    private static final String TABLENAME="contacts";
    private static final String IDCOL="id";
    private static final String NAMECOL="name";
    private static final String EMAILCOL="email";
    private static final String RELATIONSHIPCOL="relationship";
    private static final String NUMBERCOL="number";
    private static final String ORGANISATIONCOL="number";

    public DBHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE=
        "CREATE TABLE "+TABLENAME+ "("+IDCOL+" int PRIMARY KEY,"+NAMECOL+" TEXT,"+EMAILCOL+" TEXT,"+NUMBERCOL+" TEXT"+ORGANISATIONCOL+" TEXT,"+RELATIONSHIPCOL+" TEXT)";
         db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETETABLE="DROP TABLE "+TABLENAME;
        db.execSQL(DELETETABLE);
        onCreate(db);

    }
}

package com.crycetruly.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.crycetruly.contactsapp.model.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Elia on 06/06/2018.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final String DBNAME = "myContacts";
    private static final int DBVERSION = 1;
    private static final String TABLENAME = "contacts";
    private static final String IDCOL = "id";
    private static final String NAMECOL = "name";
    private static final String EMAILCOL = "email";
    private static final String RELATIONSHIPCOL = "relationship";
    private static final String NUMBERCOL = "number";
    private static final String ORGANISATIONCOL = "organisation";
    private static final String TAG = "DBHandler";

    public DBHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =
 "CREATE TABLE " + TABLENAME + "(" + IDCOL + " integer PRIMARY KEY autoincrement," + NAMECOL + " TEXT," + EMAILCOL + " TEXT,"
         + NUMBERCOL + " TEXT," + ORGANISATIONCOL + " TEXT," + RELATIONSHIPCOL + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETETABLE = "DROP TABLE " + TABLENAME;
        db.execSQL(DELETETABLE);
        onCreate(db);

    }

    public boolean addContact(Contact contact) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMECOL, contact.getName());
        contentValues.put(NUMBERCOL, contact.getNumber());
        contentValues.put(EMAILCOL, contact.getEmail());
        contentValues.put(RELATIONSHIPCOL, contact.getRelationship());
        contentValues.put(ORGANISATIONCOL, contact.getOrganisation());





        long result = sqLiteDatabase.insert(TABLENAME, null, contentValues);
        sqLiteDatabase.close();
        if (result != -1) {
            Log.d(TAG, "addContact: Data addded successfully");

            return true;
        }else{
            Log.d(TAG, "addContact:Data not  addded successfully "+result);

            return false;
        }


    }

    public Contact getContact(int id) {
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(
                TABLENAME,
                new String[]{IDCOL, NAMECOL, NUMBERCOL, EMAILCOL, ORGANISATIONCOL, RELATIONSHIPCOL},
                IDCOL + " = ? "
                ,
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        if (cursor != null) {
            Contact contact;

            cursor.moveToFirst();
            contact = new Contact(Integer.parseInt(cursor.getString(0))
                    , cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5)

            );

            return contact;

        } else {
            return null;
        }

    }


    public List<Contact> getContacts() {
        List<Contact> contacts = new LinkedList<Contact>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLENAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Contact contact = null;
        if (cursor.moveToFirst()) {
            do {
                contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setNumber(cursor.getString(3));
                contact.setOrganisation(cursor.getString(4));
                contact.setRelationship(cursor.getString(5));
                contacts.add(contact);

            } while (cursor.moveToNext());
        }


        // return books
        return contacts;


    }












    public int updateContact(Contact contact) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMECOL, contact.getName());
        contentValues.put(NUMBERCOL, contact.getNumber());
        contentValues.put(EMAILCOL, contact.getEmail());
        contentValues.put(RELATIONSHIPCOL, contact.getRelationship());
        contentValues.put(ORGANISATIONCOL, contact.getOrganisation());
        return database.update(TABLENAME, contentValues, IDCOL + " = ? ", new String[]{String.valueOf(contact.getId())});

    }

    public  void deleteContact(int id) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.delete(TABLENAME, IDCOL + " = ? ", new String[]{String.valueOf(id)});


    }

    public int getContactsCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLENAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);


        return cursor.getCount();
    }
}

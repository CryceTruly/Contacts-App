package com.crycetruly.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crycetruly.contactsapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<Contact> contactList=new ArrayList<>();
RecyclerView listView;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        listView.setHasFixedSize(true);



        DBHandler handler=new DBHandler(getBaseContext());
        getSupportActionBar().setSubtitle(String.valueOf(handler.getContactsCount()+" Contacts"));
        contactList=handler.getContacts();
        Log.d(TAG, "onCreate: contact size "+contactList.size());

        Adapter adapter=new Adapter(contactList,getBaseContext());

        listView.setAdapter(adapter);





        String [] names=new String[contactList.size()];




        //listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names));


    }

    public void gonext(View view) {
        startActivity(new Intent(getBaseContext(),AddContactActivity.class));
    }
}

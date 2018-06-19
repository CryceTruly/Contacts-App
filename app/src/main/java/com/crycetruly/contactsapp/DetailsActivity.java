package com.crycetruly.contactsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.crycetruly.contactsapp.model.Contact;

public class DetailsActivity extends AppCompatActivity {
    int id;
    private static final String TAG = "DetailsActivity";
    private TextView name,phone,email,family,organisation;
Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        id = getIntent().getIntExtra("id", 1);
init();


        DBHandler handler = new DBHandler(getBaseContext());
         contact = handler.getContact(id);

        name.setText(contact.getName());
        email.setText(contact.getEmail());
        family.setText(contact.getRelationship());
        phone.setText(contact.getNumber());
        organisation.setText(contact.getOrganisation());

        getSupportActionBar().setTitle("Contact Details");
getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate: " + contact.toString());


    }

    private void init() {
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        family=findViewById(R.id.category);
        organisation=findViewById(R.id.company);
        phone=findViewById(R.id.phone);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contactdetail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(getBaseContext(), EditActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name",contact.getName());
                intent.putExtra("email",contact.getEmail());
                intent.putExtra("org",contact.getOrganisation());
                intent.putExtra("phone",contact.getNumber());

                startActivity(intent);

                break;
            case R.id.delete:
                deleteContact(id);
                break;

            case android.R.id.home:
               finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteContact(int id) {
        Log.d(TAG, "deleteContact: ");
        DBHandler handler = new DBHandler(getBaseContext());
        handler.deleteContact(id);

        Toast.makeText(this, "Contact Removed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }
}

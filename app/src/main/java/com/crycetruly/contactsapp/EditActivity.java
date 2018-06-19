package com.crycetruly.contactsapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.crycetruly.contactsapp.model.Contact;

public class EditActivity extends AppCompatActivity {
    int id;
    private AppCompatEditText name, email, number;
    private AppCompatEditText organistion;
    private AppCompatSpinner relationship;
    private Button add;
    private static final String TAG = "EditActivity";
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
        String[] p = getResources().getStringArray(R.array.relationships);
        relationship.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, p));

        relationship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cname = name.getText().toString();
                String cemail = email.getText().toString();
                String phone = number.getText().toString();
                String organisationn = organistion.getText().toString();
                String relationshipp = selected;

                Log.d(TAG, "onClick: " + cname + " " + cemail + " " + phone + " " + organisationn + " " + relationshipp);
                if (TextUtils.isEmpty(cname) || TextUtils.isEmpty(cemail) || TextUtils.isEmpty(phone)
                        || TextUtils.isEmpty(organisationn) || TextUtils.isEmpty(relationshipp)) {
                    Snackbar.make(relationship, "Fill all fields", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Contact c = new Contact(cname, phone, cemail, organisationn, relationshipp);
                DBHandler dbHandler = new DBHandler(getBaseContext());

                if (dbHandler.updateContact(c) != -1) {
                    Toast.makeText(getBaseContext(), "Updated a contact", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                }
                ;


            }
        });
    }

    private void init() {
        name = findViewById(R.id.n);
        name.setText(getIntent().getStringExtra("name"));
        email = findViewById(R.id.e);
        email.setText(getIntent().getStringExtra("email"));
        number = findViewById(R.id.no);
        number.setText(getIntent().getStringExtra("phone"));

        organistion = findViewById(R.id.o);
        organistion.setText(getIntent().getStringExtra("org"));
        relationship = findViewById(R.id.spinner);
        add = findViewById(R.id.add);

        getSupportActionBar().setTitle("Edit a contact");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}

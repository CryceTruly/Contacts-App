package com.crycetruly.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.crycetruly.contactsapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elia on 16/06/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ContactsAdapter> {
List<Contact> contactList=new ArrayList<>();
private Context context;
    private static final String TAG = "Adapter";
    public Adapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view= inflater.inflate(R.layout.singlecontact,parent,false);

        return new ContactsAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsAdapter holder, final int position) {
holder.type.setText(contactList.get(position).getRelationship());
holder.name.setText(contactList.get(position).getName());
holder.phone.setText(contactList.get(position).getNumber());

holder.mView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(context,DetailsActivity.class);
     int id=contactList.get(position).getId();
        Log.d(TAG, "onClick:item at "+id);

        Intent intent=new Intent(context,DetailsActivity.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);



    }
});

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public static class ContactsAdapter extends RecyclerView.ViewHolder{
private View mView;
private TextView name,phone,type;
        public ContactsAdapter(View itemView) {
            super(itemView);
            mView=itemView;

            name=mView.findViewById(R.id.nameview);
            phone=mView.findViewById(R.id.phone);
            type=mView.findViewById(R.id.type);

        }
    }
}

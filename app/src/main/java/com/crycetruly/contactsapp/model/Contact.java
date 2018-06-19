package com.crycetruly.contactsapp.model;

/**
 * Created by Elia on 06/06/2018.
 */

public class Contact {
    String name,email,organisation,number,relationship;
    int id;

    public Contact( int id,String name,String number, String email, String organisation, String relationship) {
        this.name = name;
        this.email = email;
        this.organisation = organisation;
        this.number = number;
        this.relationship=relationship;
        this.id = id;
    }
    public Contact( String name,String number, String email, String organisation, String relationship) {
        this.name = name;
        this.email = email;
        this.organisation = organisation;
        this.number = number;
        this.relationship=relationship;

    }
    public Contact() {
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Contact(String name , String number,String email, String organisation) {
        this.name = name;
        this.email = email;
        this.organisation = organisation;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", organisation='" + organisation + '\'' +
                ", number='" + number + '\'' +
                ", relationship='" + relationship + '\'' +
                ", id=" + id +
                '}';
    }
}

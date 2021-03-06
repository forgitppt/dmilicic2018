package com.alex.readabc.letters.myapplication.storage;

import android.util.Log;

import com.alex.readabc.letters.myapplication.HttpHandler;
import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.domain.model.SampleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created on 6/24/18.
 */

public class ContactsContactsRepository implements com.alex.readabc.letters.myapplication.domain.repository.ContactsRepository {

    @Override
    public boolean insert(Contact model) {
        return false;
    }

    @Override
    public boolean update(Contact model) {
        return false;
    }

    @Override
    public Contact get(Object id) {
        return null;
    }

    @Override
    public boolean delete(Contact model) {
        return false;
    }

    @Override
    public ArrayList<Contact> getContacts() {
        Log.v("vvv", "getContacts");

        ArrayList<Contact> contacts = new ArrayList<>();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpHandler h = new HttpHandler();

        //https://api.androidhive.info/contacts/
        //http://rayrays-json-deliverer.herokuapp.com/user
        String json = h.makeServiceCall("https://api.androidhive.info/contacts/");


        try {
            JSONObject jsonRoot = new JSONObject(json);
            JSONArray members = jsonRoot.getJSONArray("contacts");
            for (int i = 0; i < members.length(); i++) {
                JSONObject user = members.getJSONObject(i);
                String s = user.getString("name");
                Log.v("vvv", s);
                Contact c = new Contact();
                c.setName(s);
                contacts.add(c);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return contacts;
    }
}

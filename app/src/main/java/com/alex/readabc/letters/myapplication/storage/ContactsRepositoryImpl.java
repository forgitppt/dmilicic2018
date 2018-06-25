package com.alex.readabc.letters.myapplication.storage;

import android.util.Log;

import com.alex.readabc.letters.myapplication.HttpHandler;
import com.alex.readabc.letters.myapplication.domain.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created on 6/24/18.
 */

public class ContactsRepositoryImpl implements com.alex.readabc.letters.myapplication.domain.repository.ContactsRepository {




    // static variable single_instance of type Singleton
    private static ContactsRepositoryImpl singleInstance = null;

    // private constructor restricted to this class itself
    private ContactsRepositoryImpl()
    {
    }

    // static method to create instance of Singleton class
    public static ContactsRepositoryImpl getInstance()
    {
        if (singleInstance == null)
            singleInstance = new ContactsRepositoryImpl();

        return singleInstance;
    }




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
        Log.v("vvv", "getContacts tick");

        ArrayList<Contact> contacts = new ArrayList<>();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v("vvv", "getContacts serviceCall");
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

package com.alex.readabc.letters.myapplication.domain.repository;


import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.domain.model.SampleModel;

import java.util.ArrayList;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface ContactsRepository {

    boolean insert(Contact model);

    boolean update(Contact model);

    Contact get(Object id);

    boolean delete(Contact model);

    ArrayList<Contact> getContacts();

    ArrayList<Contact> getContacts(String filter);
}

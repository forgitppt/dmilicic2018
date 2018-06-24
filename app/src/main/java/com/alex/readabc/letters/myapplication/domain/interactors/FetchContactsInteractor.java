package com.alex.readabc.letters.myapplication.domain.interactors;


import com.alex.readabc.letters.myapplication.domain.interactors.base.Interactor;
import com.alex.readabc.letters.myapplication.domain.model.Contact;

import java.util.ArrayList;

public interface FetchContactsInteractor extends Interactor {

    interface Callback {
        void onContactsFetch(ArrayList<Contact> contacts);

        void onContactsFetchFailed();
    }

}

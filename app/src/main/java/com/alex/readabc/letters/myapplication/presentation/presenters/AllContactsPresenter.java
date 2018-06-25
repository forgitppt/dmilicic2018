package com.alex.readabc.letters.myapplication.presentation.presenters;


import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.presentation.presenters.base.BasePresenter;
import com.alex.readabc.letters.myapplication.presentation.ui.BaseView;

import java.util.ArrayList;

public interface AllContactsPresenter extends BasePresenter {

    interface View extends BaseView {
        void showContactsList(ArrayList<Contact> contacts);
    }

    void displayContacts();

    void setView(View mView);
}

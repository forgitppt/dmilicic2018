package com.alex.readabc.letters.myapplication.domain.interactors.impl;


import android.util.Log;

import com.alex.readabc.letters.myapplication.domain.executor.Executor;
import com.alex.readabc.letters.myapplication.domain.executor.MainThread;
import com.alex.readabc.letters.myapplication.domain.interactors.FetchContactsInteractor;
import com.alex.readabc.letters.myapplication.domain.interactors.base.AbstractInteractor;
import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.domain.repository.ContactsRepository;
import com.alex.readabc.letters.myapplication.storage.ContactsRepositoryImpl;

import java.util.ArrayList;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class FetchContactsInteractorImpl extends AbstractInteractor implements FetchContactsInteractor {


    // static variable single_instance of type Singleton
    private static FetchContactsInteractorImpl singleInstance = null;

    // private constructor restricted to this class itself
    private FetchContactsInteractorImpl() {
        super(null, null);
    }

    private FetchContactsInteractorImpl(Executor threadExecutor,
                                        MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    // static method to create instance of Singleton class
    public static FetchContactsInteractorImpl getInstance(Executor threadExecutor,
                                                          MainThread mainThread,
                                                          Callback callback,
                                                          ContactsRepository contactsRepository) {
        if (singleInstance == null) {
            singleInstance = new FetchContactsInteractorImpl(threadExecutor, mainThread);
        }

        singleInstance.mContactsRepository = contactsRepository;
        singleInstance.mCallback = callback;

        return singleInstance;
    }


    private Callback mCallback;
    private ContactsRepository mContactsRepository;

//    public FetchContactsInteractorImpl(Executor threadExecutor,
//                                       MainThread mainThread,
//                                       Callback callback, ContactsRepository contactsRepository) {
//        super(threadExecutor, mainThread);
//        mCallback = callback;
//        mContactsRepository = contactsRepository;
//    }

    @Override
    public void run() {
        //Log.v("vvv","RUN");
        // retrieve the message
        final ArrayList<Contact> message;
        if (filter != null) {
            message = mContactsRepository.getContacts(filter);
        } else {
            message = mContactsRepository.getContacts();
        }


        // check if we have failed to retrieve our message
        if (message == null) {

            // notify the failure on the main thread
            notifyError();

            return;
        }

        // we have retrieved our message, notify the UI on the main thread
        postContacts(message);
    }


    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onContactsFetchFailed();
            }
        });
    }

    private void postContacts(final ArrayList<Contact> contacts) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onContactsFetch(contacts);
            }
        });
    }


    String filter;

    @Override
    public void setSearch(String filter) {
        this.filter = filter;

    }
}

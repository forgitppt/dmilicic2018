package com.alex.readabc.letters.myapplication.presentation.presenters.impl;


import com.alex.readabc.letters.myapplication.domain.executor.Executor;
import com.alex.readabc.letters.myapplication.domain.executor.MainThread;
import com.alex.readabc.letters.myapplication.domain.interactors.FetchContactsInteractor;
import com.alex.readabc.letters.myapplication.domain.interactors.impl.FetchContactsInteractorImpl;
import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.presentation.presenters.AllContactsPresenter;
import com.alex.readabc.letters.myapplication.presentation.presenters.base.AbstractPresenter;
import com.alex.readabc.letters.myapplication.storage.ContactsRepositoryImpl;

import java.util.ArrayList;

/**
 * Created by dmilicic on 12/13/15.
 */
public class AllContactsPresenterImpl extends AbstractPresenter implements AllContactsPresenter,
        FetchContactsInteractor.Callback {

    private AllContactsPresenter.View mView;
    private ArrayList<Contact> contacts;

    public void setView(View mView) {
        this.mView = mView;
    }

    ContactsRepositoryImpl repository;
    FetchContactsInteractor interactor;

    public AllContactsPresenterImpl(Executor executor,
                                    MainThread mainThread,
                                    View view, ContactsRepositoryImpl repository) {
        super(executor, mainThread);
        mView = view;
        this.repository = repository;

        interactor = FetchContactsInteractorImpl.getInstance(
                mExecutor,
                mMainThread,
                this,
                repository
        );
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void displayContacts() {
        mView.showProgress();

        // initialize the interactor
//        FetchContactsInteractor interactor = new FetchContactsInteractorImpl(
//                mExecutor,
//                mMainThread,
//                this,
//                repository
//        );


        interactor.setSearch(null);
        // run the interactor
        interactor.execute();
    }

    @Override
    public void displayContacts(String search) {
        mView.showProgress();
        interactor.setSearch(search);

        interactor.execute();
    }

    @Override
    public void onContactsFetch(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        mView.showContactsList(contacts);
        mView.hideProgress();
    }

    @Override
    public void onContactsFetchFailed() {
        mView.showError("shit");
    }
}

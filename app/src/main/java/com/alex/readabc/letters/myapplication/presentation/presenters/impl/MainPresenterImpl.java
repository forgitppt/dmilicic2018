package com.alex.readabc.letters.myapplication.presentation.presenters.impl;


import com.alex.readabc.letters.myapplication.domain.executor.Executor;
import com.alex.readabc.letters.myapplication.domain.executor.MainThread;
import com.alex.readabc.letters.myapplication.domain.interactors.FetchContactsInteractor;
import com.alex.readabc.letters.myapplication.domain.interactors.SampleInteractor;
import com.alex.readabc.letters.myapplication.domain.interactors.impl.FetchContactsInteractorImpl;
import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.presentation.presenters.MainPresenter;
import com.alex.readabc.letters.myapplication.presentation.presenters.base.AbstractPresenter;
import com.alex.readabc.letters.myapplication.storage.ContactsContactsRepository;

import java.util.ArrayList;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        FetchContactsInteractor.Callback {

    private MainPresenter.View mView;

    public void setView(View mView) {
        this.mView = mView;
    }

    ContactsContactsRepository repository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view, ContactsContactsRepository repository) {
        super(executor, mainThread);
        mView = view;
        this.repository = repository;
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
        FetchContactsInteractor interactor = new FetchContactsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                repository
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void onContactsFetch(ArrayList<Contact> contacts) {

    }

    @Override
    public void onContactsFetchFailed() {
        mView.showError("shit");
    }
}

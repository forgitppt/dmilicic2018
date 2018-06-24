package com.alex.readabc.letters.myapplication.domain.interactors.impl;


import com.alex.readabc.letters.myapplication.domain.executor.Executor;
import com.alex.readabc.letters.myapplication.domain.executor.MainThread;
import com.alex.readabc.letters.myapplication.domain.interactors.SampleInteractor;
import com.alex.readabc.letters.myapplication.domain.interactors.base.AbstractInteractor;
import com.alex.readabc.letters.myapplication.domain.repository.ContactsRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class SampleInteractorImpl extends AbstractInteractor implements SampleInteractor {

    private SampleInteractor.Callback mCallback;
    private ContactsRepository mContactsRepository;

    public SampleInteractorImpl(Executor threadExecutor,
                                MainThread mainThread,
                                Callback callback, ContactsRepository contactsRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mContactsRepository = contactsRepository;
    }

    @Override
    public void run() {
        // TODO: Implement this with your business logic
    }

}

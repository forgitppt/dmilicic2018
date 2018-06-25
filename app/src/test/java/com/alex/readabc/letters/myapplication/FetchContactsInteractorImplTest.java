package com.alex.readabc.letters.myapplication;

import com.alex.readabc.letters.myapplication.domain.executor.Executor;
import com.alex.readabc.letters.myapplication.domain.executor.MainThread;
import com.alex.readabc.letters.myapplication.domain.interactors.FetchContactsInteractor;
import com.alex.readabc.letters.myapplication.domain.interactors.impl.FetchContactsInteractorImpl;
import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.storage.ContactsRepositoryImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

/**
 * Created on 6/24/18.
 */

public class FetchContactsInteractorImplTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private ContactsRepositoryImpl mMessageRepository;
    @Mock
    private FetchContactsInteractor.Callback mMockedCallback;

    public FetchContactsInteractorImplTest() {
    }


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }

    @Test
    public void fakeTest() {
        when(mMessageRepository.getContacts()).thenReturn(null);

        mMessageRepository.getContacts();

        Mockito.verify(mMessageRepository).getContacts();
    }

    @Test
    public void testFetchContact() {
        Contact contact1 = new Contact();
        contact1.setName("c1");
        Contact contact2 = new Contact();
        contact2.setName("c2");
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);


        when(mMessageRepository.getContacts())
                .thenReturn(contacts);

        FetchContactsInteractorImpl interactor = new FetchContactsInteractorImpl(
                mExecutor,
                mMainThread,
                mMockedCallback,
                mMessageRepository
        );
        interactor.run();

        Mockito.verify(mMessageRepository).getContacts();
        Mockito.verifyNoMoreInteractions(mMessageRepository);
        Mockito.verify(mMockedCallback).onContactsFetch(contacts);
    }
}
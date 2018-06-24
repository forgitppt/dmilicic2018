package com.alex.readabc.letters.myapplication.presentation.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alex.readabc.letters.myapplication.R;
import com.alex.readabc.letters.myapplication.domain.executor.Executor;
import com.alex.readabc.letters.myapplication.domain.executor.impl.ThreadExecutor;
import com.alex.readabc.letters.myapplication.domain.model.Contact;
import com.alex.readabc.letters.myapplication.presentation.presenters.MainPresenter;
import com.alex.readabc.letters.myapplication.presentation.presenters.impl.MainPresenterImpl;
import com.alex.readabc.letters.myapplication.presentation.ui.BaseView;
import com.alex.readabc.letters.myapplication.storage.ContactsContactsRepository;
import com.alex.readabc.letters.myapplication.threading.MainThreadImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private static final int MY_PERMISSIONS_REQUEST = 155;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create a presenter for this view
        mainPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ContactsContactsRepository()
        );


        Log.v("vvv", "onCreate");
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.v("vvv", "onCreate 1");
            askForPermission();

        } else {
            Toast.makeText(MainActivity.this, "Hah you already got this", Toast.LENGTH_LONG);
            Log.v("vvv", "onCreate 2");
        }

        ((Button) findViewById(R.id.submitBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do stuff

                mainPresenter.displayContacts();
            }
        });
    }

    private void askForPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            Log.v("vvv", "NOt, request");
            Toast.makeText(MainActivity.this, "Not Granted", Toast.LENGTH_LONG);
            // Permission is not granted
            // Should we show an explanation?

            //todo make fragment dialog
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.INTERNET)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST);
            Toast.makeText(MainActivity.this, "Request", Toast.LENGTH_LONG);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
//            }
        } else {
            // Permission has already been granted
            Toast.makeText(MainActivity.this, "Already", Toast.LENGTH_LONG);
            Log.v("vvv", "you got this");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.v("vvv", "result");
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                Log.v("vvv", "result 1");
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(MainActivity.this, "System approved", Toast.LENGTH_LONG);
                    Log.v("vvv", "result ok");
                } else {
                    Toast.makeText(MainActivity.this, "System denied", Toast.LENGTH_LONG);
                    Log.v("vvv", "result not");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showContactsList(ArrayList<Contact> contacts) {

    }

    MainPresenter mainPresenter;

    @Override
    protected void onResume() {
        super.onResume();

        //mainPresenter.resume();
        mainPresenter.setView(this);
    }
}

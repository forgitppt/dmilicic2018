package com.alex.readabc.letters.myapplication.presentation.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alex.readabc.letters.myapplication.R;
import com.alex.readabc.letters.myapplication.presentation.ui.fragments.AllContactsFragment;
import com.alex.readabc.letters.myapplication.presentation.ui.fragments.MyAlertDialogFragment;
import com.alex.readabc.letters.myapplication.presentation.ui.fragments.MyCustomDialogFragment;

public class MainActivity extends AppCompatActivity implements AllContactsFragment.OnFragmentInteractionListener {

    private static final int MY_PERMISSIONS_REQUEST = 155;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("vvv", "onCreate activity");
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
//            Log.v("vvv", "onCreate 1");
            askForPermission();

        } else {
            Toast.makeText(MainActivity.this, "Hah you already got this", Toast.LENGTH_LONG);
//            Log.v("vvv", "onCreate 2");
        }





        //newFragment.show(getFragmentManager(), "dialog");



        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            AllContactsFragment firstFragment = new AllContactsFragment();


            //firstFragment.setRetainInstance(true);

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
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
    public void onFragmentInteraction(String uri) {
//        DialogFragment newFragment = MyAlertDialogFragment.newInstance(
//                R.string.app_name);
//        newFragment.show(getSupportFragmentManager(),"a");

        MyCustomDialogFragment newFragment = new MyCustomDialogFragment();
        newFragment.show(getSupportFragmentManager(),"a");


    }
}

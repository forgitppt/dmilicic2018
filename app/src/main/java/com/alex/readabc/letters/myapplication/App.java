package com.alex.readabc.letters.myapplication;

import android.app.Application;
import android.content.Context;

import com.alex.readabc.letters.myapplication.dagger.ApplicationModule;
import com.alex.readabc.letters.myapplication.dagger.DaggerMyComponent;
import com.alex.readabc.letters.myapplication.dagger.MyComponent;

/**
 * Created on 6/26/18.
 */

public class App extends Application{

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public void initDagger() {
        applicationComponent = DaggerMyComponent.builder()
                .applicationModule(new ApplicationModule(this, getApplicationContext()))
//                .networkModule(new NetworkModule("localhost"))
//                .chatChatScreenModule(new ChatChatScreenModule())
//                .calcDaggerModule(new CalcDaggerModule())
                //.vehicleModule(new VehicleModule())
//                    .applicationModule(new ApplicationModule(this))
//                    .settingsModule(new SettingsModule())
                .build();
    }
    public static MyComponent getMyAppComponent() {
        return ((App) appContext.getApplicationContext()).getDaggerComponent();
    }

    private MyComponent applicationComponent;

    public MyComponent getDaggerComponent() {
        return applicationComponent;
    }
}

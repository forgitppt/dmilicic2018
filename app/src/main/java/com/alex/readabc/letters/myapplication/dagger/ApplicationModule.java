package com.alex.readabc.letters.myapplication.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 6/26/18.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;
    private Context context;

    public ApplicationModule(Application application, Context context) {
        mApplication = application;
        this.context = context;
    }

    @Provides
    Application providePpplication() {
        return mApplication;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}

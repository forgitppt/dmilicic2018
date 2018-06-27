package com.alex.readabc.letters.myapplication.dagger;

import android.content.Context;

import dagger.Component;

/**
 * Created on 6/26/18.
 */

@Component(modules = {ApplicationModule.class})
public interface MyComponent {

    void inject(Context context);
}

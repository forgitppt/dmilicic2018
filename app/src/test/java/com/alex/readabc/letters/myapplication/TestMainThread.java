package com.alex.readabc.letters.myapplication;

import com.alex.readabc.letters.myapplication.domain.executor.MainThread;

/**
 * Created on 6/24/18.
 */

public class TestMainThread implements MainThread {

    @Override
    public void post(Runnable runnable) {
        // tests can run on this thread, no need to invoke other threads
        runnable.run();
    }
}


package com.pireslabs.android.utils.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.pireslabs.android.utils.log.Log;

public abstract class BasicActivity extends AppCompatActivity {

    private String tag;

    protected String getTag() {
        if (tag == null) {
            this.tag = this.getClass().getSimpleName();
        }
        return this.tag;
    }

    protected void logDebug(String message) {
        Log.debug(getTag(), message);
    }

    protected void logInformation(String message) {
        Log.info(getTag(), message);
    }

    protected void logError(String message) {
        Log.error(getTag(), message);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.debug(this.getTag(), "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.debug(this.getTag(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.debug(this.getTag(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.debug(this.getTag(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.debug(this.getTag(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.debug(this.getTag(), "onDestroy");
    }

    public void loadFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

package com.pireslabs.android.utils.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pireslabs.android.utils.log.Log;

public abstract class BasicFragment extends Fragment {

    private String tag;

    private String getTagForLog() {
        if (this.tag == null) {
            this.tag = this.getClass().getSimpleName();
        }
        return tag;
    }

    protected void logDebug(String message) {
        Log.debug(getTagForLog(), message);
    }

    protected void logInformation(String message) {
        Log.info(getTagForLog(), message);
    }

    protected void logError(String message) {
        Log.error(getTagForLog(), message);
    }

    private void logActivity() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            logDebug("Activity: " + activity.getClass().getSimpleName() + ", ID: " + activity.hashCode());
        } else {
            logDebug("Activity: null");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logDebug("onAttach");
        String contextTypeName = context.getClass().getSimpleName();
        if (!(context instanceof BasicActivity)) {
            throw new IllegalStateException("A activity onde o Fragment foi inserido deve ser do tipo BasicFragment. Tipo da activity: " +
                    contextTypeName);
        }
        logDebug("Context Type: " + contextTypeName);
        logActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logDebug("onCreate");
        logDebug("Bundle: " + String.valueOf(savedInstanceState));
        Bundle arguments = getArguments();
        if (arguments != null) {
            logDebug("Arguments Bundle: " + String.valueOf(arguments));
        }
        logActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logDebug("onCreateView");
        logDebug("Inflater: " + String.valueOf(inflater));
        logDebug("ViewGroup: " + String.valueOf(container));
        logDebug("Bundle: " + String.valueOf(savedInstanceState));
        logActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logDebug("onActivityCreated");
        logDebug("Bundle: " + String.valueOf(savedInstanceState));
        logActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        logDebug("onStart");
        logActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        logDebug("onResume");
        logActivity();
    }

    @Override
    public void onPause() {
        super.onPause();
        logDebug("onPause");
        logActivity();
    }

    @Override
    public void onStop() {
        super.onStop();
        logDebug("onStop");
        logActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logDebug("onDestroyView");
        logActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logDebug("onDestroy");
        logActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logDebug("onDetach");
        logActivity();
    }

    protected BasicActivity getMyParentActivity() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new IllegalStateException("Activity não encontrada. " +
                    "Verifique se este método está sendo chamado após a execução do onAttach deste Fragment.");
        }
        return (BasicActivity) activity;
    }
}

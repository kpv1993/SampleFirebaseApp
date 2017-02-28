package com.example.android.samplefirebaseapp;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by prash on 2/28/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        tokenBroadcast(refreshedToken);

        //Displaying token on logcat
        Log.e(TAG, "Refreshed token: " + refreshedToken);

    }

    public void tokenBroadcast(String refreshedToken) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.putExtra("token",refreshedToken);
        broadcastIntent.setAction("tokenBroadcast");
        sendBroadcast(broadcastIntent);
    }

}

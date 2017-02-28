package com.example.android.samplefirebaseapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver tokenReceiver;
    TextView token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        token = (TextView) findViewById(R.id.token);
        Button createToken = (Button) findViewById(R.id.createToken);
        Button deleteToken = (Button) findViewById(R.id.deleteToken);

        createToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFCMRegistration();
            }
        });

        deleteToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                            Log.e("Deleted","Token");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        });

        if(tokenReceiver==null) {

            tokenReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String k = intent.getStringExtra("token");
                    token.setText(k);
                }
            };
            IntentFilter filter = new IntentFilter("tokenBroadcast");
            registerReceiver(tokenReceiver, filter);
        }

    }

    private void startFCMRegistration() {
        String k = FirebaseInstanceId.getInstance().getToken();
        token.setText(k);
    }
}

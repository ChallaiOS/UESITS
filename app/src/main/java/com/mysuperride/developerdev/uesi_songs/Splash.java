package com.mysuperride.developerdev.uesi_songs;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Thread xyz = new Thread(){

            public void run(){
                try{
                    sleep(1500);
                }

                catch (Exception e){
                    e.printStackTrace();
                }

                finally {
                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                }
            }

        };
        xyz.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}

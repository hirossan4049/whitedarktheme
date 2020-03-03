package com.example.whitedarktheme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("themes", Context.MODE_PRIVATE);

//        editor.putString("theme", "white");
//        editor.apply();

        String theme = prefs.getString("theme", "white");
        if (theme.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else if(theme.equals("white")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else {
            Log.d("ELSE","oncreate");
        }
    }


    public void changedark(View v){
        String str = prefs.getString("theme","null");
        Log.d("TAG",str);

        SharedPreferences.Editor editor = prefs.edit();

        if (str.equals("dark")){
            editor.putString("theme", "white");
        }else{
            editor.putString("theme", "dark");
        }
        editor.apply();
        restartApplication();
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }

    public void restartApplication(){
        Intent intent = getIntent();
        PendingIntent appStarter = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), appStarter);
        finish();
    }
}

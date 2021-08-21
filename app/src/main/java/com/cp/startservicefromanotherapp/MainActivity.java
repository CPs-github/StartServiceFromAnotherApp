package com.cp.startservicefromanotherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        intent =  new Intent(this, AppService.class);
//        startService(intent);
    }

    @Override
    protected void onDestroy() {
//        stopService(intent);
        super.onDestroy();
    }
}
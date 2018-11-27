package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExselectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exactivity_select);
    }

    public void onprefclicked(View v){
        Intent myIntent = new Intent(getApplicationContext(), prefselectex.class);
        startActivity(myIntent);
    }

    public void myselectclicked(View v){
        Intent myIntent = new Intent(getApplicationContext(), myselectex.class);
        startActivity(myIntent);
    }
}

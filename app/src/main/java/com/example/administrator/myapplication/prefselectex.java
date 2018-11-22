package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class prefselectex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefselectex);
    }

    public void onnextbtnclicked(View v){
        Intent myIntents = new Intent(getApplicationContext(), prefselect.class);
        startActivity(myIntents);
    }
}

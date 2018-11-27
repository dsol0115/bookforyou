package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class myselectex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myselectex);
    }

    public void myselectstart(View v){
        Intent intent = new Intent(getApplicationContext(), myselect.class);
        startActivity(intent);
    }
}

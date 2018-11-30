package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class my_s_read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_s_read);
    }

    public void my_s_unp(View v){
        Intent intent = new Intent(getApplicationContext(), my_s_unread.class);
        startActivity(intent);
    }
}

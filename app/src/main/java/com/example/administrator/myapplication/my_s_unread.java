package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class my_s_unread extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_s_unread);
    }

    public void read_p(View v){
        Intent intent = new Intent(getApplicationContext(), my_s_read.class);
        startActivity(intent);
    }
}

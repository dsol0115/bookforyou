package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout=(ConstraintLayout)findViewById(R.id.myLayout);

        animationDrawable=(AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

    }
    public void onselectButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), ExselectActivity.class);
        startActivity(intent);
    }

    public void OnchallengeButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), Challenge.class);
        startActivity(intent);
    }

    public void userpageclicked(View v){
        Intent intent = new Intent(getApplicationContext(), userpage.class);
        startActivity(intent);
    }

}

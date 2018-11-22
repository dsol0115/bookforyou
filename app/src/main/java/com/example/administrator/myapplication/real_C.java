package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class real_C extends AppCompatActivity {
    Button buttont3;
    TextView tv;
    PopupMenu pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real__c);
        buttont3 = (Button)findViewById(R.id.button3);
        tv= (TextView)findViewById(R.id.textView);

        pm = new PopupMenu(this, buttont3);
        pm.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case 10:
                                tv.setText("15");
                                break;

                            case 20:
                                tv.setText("30");
                                break;

                            case 30:
                                tv.setText("40");
                                break;
                        }
                        return false;
                    }
                }
        );

        Menu menu = pm.getMenu();
        menu.add(0,10,0, "15");
        menu.add(0,20,0, "30");
        menu.add(0,30,0, "40");
    }

    public void mOnClick(View v) {
        Log.v("출력", "팝업");
        pm.show();

    }
}

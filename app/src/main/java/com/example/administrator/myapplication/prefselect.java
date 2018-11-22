package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class prefselect extends AppCompatActivity {

    int windowwidth;
    int screenCenter;
    int x_cord, y_cord, x, y;
    int Likes = 0;
    public RelativeLayout parentView;
    float alphaValue = 0;
    private Context context;

    ArrayList<bookdatamodel> bookdatamodelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefselect);

        context = prefselect.this;

        parentView = findViewById(R.id.prefselect_layout);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics( metrics );
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        windowwidth = width;

        screenCenter = windowwidth / 2;

        bookdatamodelArrayList = new ArrayList<>();

        getArrayData();


        for (int i = 0; i < bookdatamodelArrayList.size(); i++) {

            LayoutInflater inflate =
                    (LayoutInflater) prefselect.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View containerView = inflate.inflate(R.layout.customtinderlayout, null);

            ImageView userIMG = containerView.findViewById(R.id.userIMG);
            RelativeLayout relativeLayoutContainer = containerView.findViewById(R.id.relative_container);


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            containerView.setLayoutParams(layoutParams);

            containerView.setTag(i);
            userIMG.setBackgroundResource(bookdatamodelArrayList.get(i).getPhoto());

//            m_view.setRotation(i);
//            containerView.setPadding(0, i, 0, 0);

            LinearLayout.LayoutParams layoutTvParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            final TextView tvLike = new TextView(context);
            tvLike.setLayoutParams(layoutTvParams);
            tvLike.setPadding(10, 10, 10, 10);
            tvLike.setBackground(getResources().getDrawable(R.drawable.btnlikeback));
            tvLike.setText("LIKE");
            tvLike.setGravity(Gravity.CENTER);
            tvLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvLike.setTextSize(25);
            tvLike.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
            tvLike.setX(20);
            tvLike.setY(100);
            tvLike.setRotation(-50);
            tvLike.setAlpha(alphaValue);
            relativeLayoutContainer.addView(tvLike);


//            ADD dynamically dislike TextView on image.
            final TextView tvUnLike = new TextView(context);
            tvUnLike.setLayoutParams(layoutTvParams);
            tvUnLike.setPadding(10, 10, 10, 10);
            tvUnLike.setBackground(getResources().getDrawable(R.drawable.btnlikeback));
            tvUnLike.setText("UNLIKE");
            tvUnLike.setGravity(Gravity.CENTER);
            tvUnLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvUnLike.setTextSize(25);
            tvUnLike.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
            tvUnLike.setX(500);
            tvUnLike.setY(150);
            tvUnLike.setRotation(50);
            tvUnLike.setAlpha(alphaValue);
            relativeLayoutContainer.addView(tvUnLike);


            TextView tvName = containerView.findViewById(R.id.booktitletxt);


            tvName.setText(bookdatamodelArrayList.get(i).getName());


            relativeLayoutContainer.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    x_cord = (int) event.getRawX();
                    y_cord = (int) event.getRawY();

                    containerView.setX(0);
                    containerView.setY(0);

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            x = (int) event.getX();
                            y = (int) event.getY();


                            Log.v("On touch", x + " " + y);
                            break;
                        case MotionEvent.ACTION_MOVE:

                            x_cord = (int) event.getRawX();
                            // smoother animation.
                            y_cord = (int) event.getRawY();

                            containerView.setX(x_cord - x);
                            containerView.setY(y_cord - y);


                            if (x_cord >= screenCenter) {
                                containerView.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                if (x_cord > (screenCenter + (screenCenter / 2))) {
                                    tvLike.setAlpha(1);
                                    if (x_cord > (windowwidth - (screenCenter / 4))) {
                                        Likes = 2;
                                    } else {
                                        Likes = 0;
                                    }
                                } else {
                                    Likes = 0;
                                    tvLike.setAlpha(0);
                                }
                                tvUnLike.setAlpha(0);
                            } else {
                                // rotate image while moving
                                containerView.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                if (x_cord < (screenCenter / 2)) {
                                    tvUnLike.setAlpha(1);
                                    if (x_cord < screenCenter / 4) {
                                        Likes = 1;
                                    } else {
                                        Likes = 0;
                                    }
                                } else {
                                    Likes = 0;
                                    tvUnLike.setAlpha(0);
                                }
                                tvLike.setAlpha(0);
                            }

                            break;
                        case MotionEvent.ACTION_UP:

                            x_cord = (int) event.getRawX();
                            y_cord = (int) event.getRawY();

                            Log.e("X Point", "" + x_cord + " , Y " + y_cord);
                            tvUnLike.setAlpha(0);
                            tvLike.setAlpha(0);

                            if (Likes == 0) {
                                Toast.makeText(context, "NOTHING", Toast.LENGTH_SHORT).show();
                                Log.e("Event_Status :-> ", "Nothing");
                                containerView.setX(0);
                                containerView.setY(0);
                                containerView.setRotation(0);
                            } else if (Likes == 1) {
                                Toast.makeText(context, "UNLIKE", Toast.LENGTH_SHORT).show();
                                Log.e("Event_Status :-> ", "UNLIKE");
                                parentView.removeView(containerView);
                            } else if (Likes == 2) {
                                Toast.makeText(context, "LIKED", Toast.LENGTH_SHORT).show();
                                Log.e("Event_Status :-> ", "Liked");
                                parentView.removeView(containerView);
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

            parentView.addView(containerView);
        }
    }


    private void getArrayData() {

        bookdatamodel model = new bookdatamodel();
        model.setName("군주론");
        model.setPhoto(R.drawable.principe);
        bookdatamodelArrayList.add(model);


        bookdatamodel model2 = new bookdatamodel();
        model2.setName("데미안");
        model2.setPhoto(R.drawable.damien);
        bookdatamodelArrayList.add(model2);

        bookdatamodel model3 = new bookdatamodel();
        model3.setName("햄릿");
        model3.setPhoto(R.drawable.hamlet);
        bookdatamodelArrayList.add(model3);


        bookdatamodel model4 = new bookdatamodel();
        model4.setName("오만과 편견");
        model4.setPhoto(R.drawable.prideandprejudice);
        bookdatamodelArrayList.add(model4);


        bookdatamodel model5 = new bookdatamodel();
        model5.setName("주홍글씨");
        model5.setPhoto(R.drawable.scarlettletter);
        bookdatamodelArrayList.add(model5);

        bookdatamodel model6 = new bookdatamodel();
        model6.setName("애프터 유");
        model6.setPhoto(R.drawable.afteryou);
        bookdatamodelArrayList.add(model6);


        bookdatamodel model7 = new bookdatamodel();
        model7.setName("사서함 110호의 우편물");
        model7.setPhoto(R.drawable.lettersat);
        bookdatamodelArrayList.add(model7);


        bookdatamodel model8 = new bookdatamodel();
        model8.setName("작가들의 연애편지");
        model8.setPhoto(R.drawable.romanticletter);
        bookdatamodelArrayList.add(model8);

        bookdatamodel model9 = new bookdatamodel();
        model9.setName("백의 그림자");
        model9.setPhoto(R.drawable.shadow);
        bookdatamodelArrayList.add(model9);

        bookdatamodel model10 = new bookdatamodel();
        model10.setName("그 여름 나는");
        model10.setPhoto(R.drawable.thatsummer);
        bookdatamodelArrayList.add(model10);

        bookdatamodel model11 = new bookdatamodel();
        model11.setName("미움받을 용기");
        model11.setPhoto(R.drawable.courage);
        bookdatamodelArrayList.add(model11);

        bookdatamodel model12 = new bookdatamodel();
        model12.setName("그릿");
        model12.setPhoto(R.drawable.grit);
        bookdatamodelArrayList.add(model12);

        bookdatamodel model13 = new bookdatamodel();
        model13.setName("마시멜로 이야기");
        model13.setPhoto(R.drawable.marshmello);
        bookdatamodelArrayList.add(model13);

        bookdatamodel model14 = new bookdatamodel();
        model14.setName("신경 끄기의 기술");
        model14.setPhoto(R.drawable.technique);
        bookdatamodelArrayList.add(model14);

        bookdatamodel model15 = new bookdatamodel();
        model15.setName("타이탄의 도구들");
        model15.setPhoto(R.drawable.titan);
        bookdatamodelArrayList.add(model15);

        bookdatamodel model16 = new bookdatamodel();
        model16.setName("그리고 아무도 없었다");
        model16.setPhoto(R.drawable.agatha);
        bookdatamodelArrayList.add(model16);

        bookdatamodel model17 = new bookdatamodel();
        model17.setName("바스커빌가의 개");
        model17.setPhoto(R.drawable.dog);
        bookdatamodelArrayList.add(model17);

        bookdatamodel model18 = new bookdatamodel();
        model18.setName("환상의 여인");
        model18.setPhoto(R.drawable.lady);
        bookdatamodelArrayList.add(model18);

        bookdatamodel model19 = new bookdatamodel();
        model19.setName("가면산장 살인사건");
        model19.setPhoto(R.drawable.mask);
        bookdatamodelArrayList.add(model19);

        bookdatamodel model20 = new bookdatamodel();
        model20.setName("Y의 비극");
        model20.setPhoto(R.drawable.tragedy);
        bookdatamodelArrayList.add(model20);

        bookdatamodel model21 = new bookdatamodel();
        model21.setName("해리포터와 마법사의 돌");
        model21.setPhoto(R.drawable.harrypotter);
        bookdatamodelArrayList.add(model21);

        bookdatamodel model22 = new bookdatamodel();
        model22.setName("반지의 제왕");
        model22.setPhoto(R.drawable.lordoftherings);
        bookdatamodelArrayList.add(model22);

        bookdatamodel model23 = new bookdatamodel();
        model23.setName("나니아 연대기: 사자, 마녀 그리고 옷장");
        model23.setPhoto(R.drawable.narnia);
        bookdatamodelArrayList.add(model23);

        bookdatamodel model24 = new bookdatamodel();
        model24.setName("리버보이");
        model24.setPhoto(R.drawable.riverboy);
        bookdatamodelArrayList.add(model24);

        bookdatamodel model25 = new bookdatamodel();
        model25.setName("트와일라잇");
        model25.setPhoto(R.drawable.twighlight);
        bookdatamodelArrayList.add(model25);

        Collections.reverse(bookdatamodelArrayList);

    }
}

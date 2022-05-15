package com.mysuperride.developerdev.uesi_songs;


import android.content.Context;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v4.view.GestureDetectorCompat;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class YearTwoContentActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {


    private static int currentID = 0;
    public static final int TEXT_MAX_SIZE = 60;
    public static final int TEXT_MIN_SIZE = 40;
    private static final int STEP = 10;
    private int mBaseDistZoomIn;
    private int mBaseDistZoomOut;
    private float x1,x2;
    private static int MIN_DISTANCE = 250;
    private GestureDetector gestureDetector;
    String[] itemmodel;
    TextView textView,textView2;
    Button sharecumTitle,dayButton,monthButton,yearButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        setContentView(R.layout.activity_year_two_content);

//        Boolean isFirstRun = getSharedPreferences("PREFERENCE1", MODE_PRIVATE)
//                .getBoolean("isFirstRun", true);
//
//        if (isFirstRun) {
//
////            Toast toast =  Toast.makeText(EnglishContentActivity.this, "Tap on title for song sharing", Toast.LENGTH_LONG);
////            View view = toast.getView();
////
////            //To change the Background of Toast
////            view.setBackgroundColor(Color.TRANSPARENT);
////            TextView text = (TextView) view.findViewById(android.R.id.message);
////
////            //Shadow of the Of the Text Color
////            text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
////            text.setTextColor(Color.BLUE);
//////            text.setTextSize(Integer.valueOf(getResources().getString(R.string.text_size)));
////            toast.show();
//
//
//        }
//
//
//        getSharedPreferences("PREFERENCE1", MODE_PRIVATE).edit()
//                .putBoolean("isFirstRun", false).commit();
//


        textView = (TextView) findViewById(R.id.textContent2);
        textView.setOnTouchListener(this);
        textView.setMovementMethod(new ScrollingMovementMethod());
        sharecumTitle = (Button) findViewById(R.id.sharecumTitleBtn);
        dayButton = (Button) findViewById(R.id.button1);
        monthButton = (Button) findViewById(R.id.button2);
        yearButton = (Button) findViewById(R.id.button3);

//        sharecumTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(Intent.EXTRA_SUBJECT,textView.getText().toString());
//                String song_text = textView.getText().toString();
//
//                String app_url = "UESI - Vidyarthi_Geethavali iOS App:\n https://apps.apple.com/us/app/vidyarthi-geethavali/id1546450568\n\n\n" + "UESI - Vidyarthi_Geethavali Android App:\n https://play.google.com/store/apps/details?id=com.mysuperride.developerdev.uesi_songs\n\n\n" + song_text;
//                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
//                startActivity(Intent.createChooser(shareIntent, "Share via"));
//            }
//        });

        this.gestureDetector = new GestureDetector(YearTwoContentActivity.this,this);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        itemModel user = (itemModel) bundle.getSerializable("user2");

        currentID = Integer.valueOf(user.getLocal_id().toString());

        TelephonyManager manager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (Objects.requireNonNull(manager).getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {

            textView.setTextSize(20);
            sharecumTitle.setTextSize(22);
            // Toast.makeText(TeluguContentActivity.this, "Detected... You're using a Tablet", Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(TeluguContentActivity.this, "Detected... You're using a Mobile Phone", Toast.LENGTH_SHORT).show();
            textView.setTextSize(18);
            sharecumTitle.setTextSize(20);
        }
        textView.setText(user.getLocal_text());
        // sharecumTitle.setText(user.getLocal_id() + "." + user.getLocal_title());
        sharecumTitle.setText(user.getLocal_title());
        dayButton.setText(user.getLocal_hint());
        monthButton.setText(user.getLocal_category());
        yearButton.setText(user.getLocal_categorycount());


//        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + user.getLocal_id() + "." +user.getLocal_title() + "</font>")));
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF5777E3));
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()){

            case R.id.song_share:
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,textView.getText().toString());
                String song_text = textView.getText().toString();

                String app_url = "UESI - Vidyarthi_Geethavali iOS App:\n https://apps.apple.com/us/app/vidyarthi-geethavali/id1546450568\n\n\n" + "UESI - Vidyarthi_Geethavali Android App:\n https://play.google.com/store/apps/details?id=com.mysuperride.developerdev.uesi_songs\n\n\n" + song_text;
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float valueX =  x2 - x1;
                if(Math.abs(valueX)>MIN_DISTANCE){
                    if (x2>x1){
                        this.textView.bringPointIntoView(0);
                        if (currentID >1){
                            currentID = currentID -1;
                            itemModel str = MySingleTonSS.getInstance().arrayListSS2.get(currentID - 1);
                            textView.setText(str.getLocal_text());
                            //sharecumTitle.setText(str.getLocal_id() + "." + str.getLocal_title());
                            sharecumTitle.setText(str.getLocal_title());
                            dayButton.setText(str.getLocal_hint());
                            monthButton.setText(str.getLocal_category());
                            yearButton.setText(str.getLocal_categorycount());

//                            getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + str.getLocal_id() + "." +str.getLocal_title() + "</font>")));
//                            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF5777E3));
//                            getSupportActionBar().setDisplayShowTitleEnabled(false);
//                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                        }
                        else{
                            currentID = MySingleTonSS.getInstance().arrayListSS2.size();
                            itemModel str = MySingleTonSS.getInstance().arrayListSS2.get(currentID - 1);
                            textView.setText(str.getLocal_text());
                            //sharecumTitle.setText(str.getLocal_id() + "." + str.getLocal_title());
                            sharecumTitle.setText(str.getLocal_title());
                            dayButton.setText(str.getLocal_hint());
                            monthButton.setText(str.getLocal_category());
                            yearButton.setText(str.getLocal_categorycount());


                        }

                    }

                    else{
                        this.textView.bringPointIntoView(0);
                        if (currentID > 0){
                            if (currentID == MySingleTonSS.getInstance().arrayListSS2.size()) {
                                currentID = 1;
                            }
                            else{
                                currentID = currentID + 1;
                            }
                            itemModel str = MySingleTonSS.getInstance().arrayListSS2.get(currentID - 1);
                            textView.setText(str.getLocal_text());
                            //sharecumTitle.setText(str.getLocal_id() + "." + str.getLocal_title());
                            sharecumTitle.setText(str.getLocal_title());
                            dayButton.setText(str.getLocal_hint());
                            monthButton.setText(str.getLocal_category());
                            yearButton.setText(str.getLocal_categorycount());

                        }
                    }
                }
        }
        if (event.getPointerCount() == 2) {
            TextView viewById = (TextView) findViewById(R.id.textContent2);
            int action = event.getAction();
            int pure = action & MotionEvent.ACTION_MASK;

            if (pure == MotionEvent.ACTION_POINTER_DOWN
                    && viewById.getTextSize() <= TEXT_MAX_SIZE
                    && viewById.getTextSize() >= TEXT_MIN_SIZE) {

                mBaseDistZoomIn = getDistanceFromEvent(event);
                mBaseDistZoomOut = getDistanceFromEvent(event);

            } else {
                int currentDistance = getDistanceFromEvent(event);
                if (currentDistance > mBaseDistZoomIn) {
                    float finalSize = viewById.getTextSize() + STEP;
                    if (finalSize > TEXT_MAX_SIZE) {
                        finalSize = TEXT_MAX_SIZE;
                    }
                    viewById.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalSize);
                } else {
                    if (currentDistance < mBaseDistZoomOut) {
                        float finalSize = viewById.getTextSize() - STEP;
                        if (finalSize < TEXT_MIN_SIZE) {
                            finalSize = TEXT_MIN_SIZE;
                        }
                        viewById.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalSize);
                    }
                }
            }
            return true;
        }
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    // good function to get the distance between the multiple touch
    int getDistanceFromEvent(MotionEvent event) {
        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));
        return (int) (Math.sqrt(dx * dx + dy * dy));
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}

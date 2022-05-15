package com.mysuperride.developerdev.uesi_songs;



import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.RequiresApi;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
//import android.view.Menu;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.NavigationView;

//import androidx.navigation.ui.AppBarConfiguration;

//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.content.Intent;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;
//import java.util.List;


//import android.support.design.widget.NavigationView;
import com.google.android.material.navigation.NavigationView;
//import android.support.v4.view.GravityCompat;
import androidx.core.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
import androidx.drawerlayout.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
import androidx.appcompat.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DividerItemDecoration;
import androidx.recyclerview.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
import 	androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.RequiresApi;


import androidx.appcompat.app.AppCompatActivity;

//import org.json.JSONObject;

public class IntroductionSSActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static AbstractList<itemModel> arrayList4;
//    private AppBarConfiguration mAppBarConfiguration;


    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    RecyclerView recyclerView;
    RecycleSpecialAdapter adapter;
    TextView textView;
    Button pay;


    ArrayList<itemModel> arrayList1;
    ArrayList<itemModel> arrayList2;
    ArrayList<itemModel> arrayList3;

//    androidx.appcompat.widget.SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        String status = sharedPreferences.getString("donation_key","NotyetDone");
        Log.d("mystatus", status);

        setContentView(R.layout.activity_introduction_ssactivity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pay = (Button) findViewById(R.id.donateBtn);


        recyclerView  = findViewById(R.id.recycler5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();

        textView = (TextView) findViewById(R.id.year_three_index);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setTextSize(15);

        if (status.equals("NotyetDone")){
            pay.setVisibility(View.VISIBLE);
        }

        else {
            pay.setVisibility(View.INVISIBLE);
        }


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(IntroductionSSActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });

        final String obj1 = "missions";
        Log.d("obj1  is new",obj1);

        arrayList1 =  MySingleTon.getInstance().arrayList1;


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycleview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RecycleSpecialAdapter(getApplicationContext(), arrayList4);
        recyclerView.setAdapter(adapter);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        String title = "Introduction";

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + title + "</font>")));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF5777E3));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){

            case R.id.nav_telugu:
                Intent intent1 = new Intent(IntroductionSSActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(IntroductionSSActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(IntroductionSSActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(IntroductionSSActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(IntroductionSSActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(IntroductionSSActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(IntroductionSSActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(IntroductionSSActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(IntroductionSSActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(IntroductionSSActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(IntroductionSSActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(IntroductionSSActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(IntroductionSSActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(IntroductionSSActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.mysuperride.developerdev.uesi_songs;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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


import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.razorpay.Checkout;
//https://www.youtube.com/watch?v=MFQ3tz6P5Vw - Payment
//https://razorpay.com/docs/payment-gateway/android-integration/standard/

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    Context context;
    JSONObject object;
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    androidx.appcompat.widget.SearchView searchView;
    Button pay;

    ArrayList<itemModel> arrayList1;
    ArrayList<itemModel> arrayList2;
    ArrayList<itemModel> arrayList3;
    ArrayList<itemModel> arrayList4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        String status = sharedPreferences.getString("donation_key","NotyetDone");
        Log.d("mystatus", status);

        recyclerView  = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Checkout.preload(getApplicationContext());
        pay = (Button) findViewById(R.id.paymentBtn);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();



        try {

            if (status == "NotyetDone"){

                object = new JSONObject(demoJSONfile());
//                object = new JSONObject(finalJSONFile());

                pay.setVisibility(View.VISIBLE);

            }

            else {

                object = new JSONObject(finalJSONFile());
                pay.setVisibility(View.GONE);
//                pay.setVisibility(View.VISIBLE);


            }

            JSONArray array1 = object.getJSONArray("telugu");
            JSONArray array2 = object.getJSONArray("english");
            JSONArray array3 = object.getJSONArray("hindi");
            for (int i = 0; i < array1.length(); i++) {

                JSONObject jsonObject = array1.getJSONObject(i);
                String local_id = jsonObject.getString("local_id");
                String local_title = jsonObject.getString("local_title");
                String local_text = jsonObject.getString("local_text");
                String local_hint = jsonObject.getString("local_hint");
                String local_category = jsonObject.getString("local_category");
                String local_categorycount = jsonObject.getString("local_categorycount");


                itemModel model = new itemModel();
                model.setLocal_id(local_id);
                model.setLocal_title(local_title);
                model.setLocal_category(local_category);
                model.setLocal_text(local_text);
                model.setLocal_hint(local_hint);
                model.setLocal_categorycount(local_categorycount);

                arrayList1.add(model);
                MySingleTon.getInstance().arrayOfSongs = model;
                MySingleTon.getInstance().arrayList1 = arrayList1;

            }

            for (int i = 0; i < array2.length(); i++) {

                JSONObject jsonObject = array2.getJSONObject(i);
                String local_id = jsonObject.getString("local_id");
                String local_title = jsonObject.getString("local_title");
                String local_text = jsonObject.getString("local_text");
                String local_hint = jsonObject.getString("local_hint");
                String local_category = jsonObject.getString("local_category");
                String local_categorycount = jsonObject.getString("local_categorycount");



                itemModel model = new itemModel();
                model.setLocal_id(local_id);
                model.setLocal_title(local_title);
                model.setLocal_category(local_category);
                model.setLocal_text(local_text);
                model.setLocal_hint(local_hint);
                model.setLocal_categorycount(local_categorycount);

                arrayList2.add(model);
                MySingleTon.getInstance().arrayOfEng_Songs = model;
                MySingleTon.getInstance().arrayList2 = arrayList2;


            }

            for (int i = 0; i < array3.length(); i++) {

                JSONObject jsonObject = array3.getJSONObject(i);
                String local_id = jsonObject.getString("local_id");
                String local_title = jsonObject.getString("local_title");
                String local_text = jsonObject.getString("local_text");
                String local_hint = jsonObject.getString("local_hint");
                String local_category = jsonObject.getString("local_category");
                String local_categorycount = jsonObject.getString("local_categorycount");



                itemModel model = new itemModel();
                model.setLocal_id(local_id);
                model.setLocal_title(local_title);
                model.setLocal_category(local_category);
                model.setLocal_text(local_text);
                model.setLocal_hint(local_hint);
                model.setLocal_categorycount(local_categorycount);

                arrayList3.add(model);
                MySingleTon.getInstance().arrayOfHin_Songs = model;
                MySingleTon.getInstance().arrayList3 = arrayList3;



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycleview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RecycleViewAdapter(getApplicationContext(), arrayList1);
        recyclerView.setAdapter(adapter);



         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public String finalJSONFile() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("finalsampleandroidkey.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }



    public String demoJSONfile() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("sample.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (	androidx.appcompat.widget.SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnQueryTextListener(new 	androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase();
                List<itemModel> myList  = new ArrayList<>();
                for (itemModel model :arrayList1){
                    String id = model.getLocal_id().toLowerCase();
                    String hint = model.getLocal_hint().toLowerCase();


                    if (id.contains(newText) || hint.contains(newText))
                        myList.add(model);
                }

                adapter.setSearchOperation(myList);

                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){

            case R.id.nav_telugu:
                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(MainActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(MainActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(MainActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(MainActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(MainActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(MainActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(MainActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(MainActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(MainActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(MainActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(MainActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

package com.mysuperride.developerdev.uesi_songs;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.SearchManager;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
//import android.support.design.widget.NavigationView;

import androidx.navigation.ui.AppBarConfiguration;

//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.content.Intent;
//import android.support.v4.view.GravityCompat;
//import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class YearThreeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    itemModel model;
    Context context;
    RecyclerView recyclerView;
    MyYearThreeSSAdapter adapter;
    TextView textView;
    JSONObject object;


    ArrayList<itemModel> arrayList1;
    ArrayList<itemModel> arrayList2;
    ArrayList<itemModel> arrayList3;
    ArrayList<itemModel> arrayList4;

    androidx.appcompat.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_three);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView  = findViewById(R.id.recycler3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        String status = sharedPreferences.getString("donation_key","NotyetDone");
        Log.d("mystatus", status);

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();


//        arrayList3 =  MySingleTonSS.getInstance().arrayListSS3;

        try {

            if (status == "NotyetDone"){

                object = new JSONObject(demoJSONfile());
//                object = new JSONObject(finalJSONFile());

                // pay.setVisibility(View.VISIBLE);

            }

            else {

                object = new JSONObject(finalJSONFile());
                // pay.setVisibility(View.GONE);
//                pay.setVisibility(View.VISIBLE);


            }

            JSONArray array3 = object.getJSONArray("yearthree");

            for (int i = 0; i < array3.length(); i++) {

                JSONObject jsonObject = array3.getJSONObject(i);
                String local_id = jsonObject.getString("local_id");
                String local_title = jsonObject.getString("local_title");
                String local_text = jsonObject.getString("local_text");
                String local_hint = jsonObject.getString("local_day");
                String local_category = jsonObject.getString("local_month");
                String local_categorycount = jsonObject.getString("local_year");
                String list_day = jsonObject.getString("list_day");
                String list_month = jsonObject.getString("list_month");
                String local_search = jsonObject.getString("local_search");

                itemModel model = new itemModel();
                model.setLocal_id(local_id);
                model.setLocal_title(local_title);
                model.setLocal_category(local_category);
                model.setLocal_text(local_text);
                model.setLocal_hint(local_hint);
                model.setLocal_categorycount(local_categorycount);
                model.setList_day(list_day);
                model.setList_month(list_month);
                model.setLocal_search(local_search);


                arrayList3.add(model);
                MySingleTonSS.getInstance().arrayOfYearThreeList = model;
                MySingleTonSS.getInstance().arrayListSS3 = arrayList3;



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView = (TextView) findViewById(R.id.year_two_index);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setTextSize(20);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycleview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new MyYearThreeSSAdapter(getApplicationContext(), arrayList3);
        recyclerView.setAdapter(adapter);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private String finalJSONFile() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("finalsampleandroidkeyss.json");
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



    private String demoJSONfile() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("sampless.json");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (androidx.appcompat.widget.SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase();
                List<itemModel> myList  = new ArrayList<>();
                for (itemModel model :arrayList3){
                    String search = model.getLocal_search().toLowerCase();
                    String hint = model.getList_day().toLowerCase();
                    String month = model.getList_month().toLowerCase();

                    if (search.contains(newText) || hint.contains(newText) || month.contains(newText))
                        myList.add(model);
                }

                adapter.setSearchOperation(myList);

                return false;
            }
        });

        return true;
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
                Intent intent1 = new Intent(YearThreeActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(YearThreeActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(YearThreeActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(YearThreeActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(YearThreeActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(YearThreeActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(YearThreeActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(YearThreeActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(YearThreeActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(YearThreeActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(YearThreeActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(YearThreeActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(YearThreeActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(YearThreeActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

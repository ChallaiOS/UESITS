package com.mysuperride.developerdev.uesi_songs;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
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


public class HinSongsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    itemModel model;
    Context context;
    RecyclerView recyclerView;
    RecycleHindiAdapter adapter;

    ArrayList<itemModel> arrayList1;
    ArrayList<itemModel> arrayList2;
    ArrayList<itemModel> arrayList3;
    ArrayList<itemModel> arrayList4;

    androidx.appcompat.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hin_songs);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView  = findViewById(R.id.recycler3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();


        arrayList3 =  MySingleTon.getInstance().arrayList3;

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycleview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RecycleHindiAdapter(getApplicationContext(), arrayList3);
        recyclerView.setAdapter(adapter);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
                Intent intent1 = new Intent(HinSongsActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(HinSongsActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(HinSongsActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(HinSongsActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(HinSongsActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(HinSongsActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(HinSongsActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(HinSongsActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(HinSongsActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(HinSongsActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(HinSongsActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(HinSongsActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(HinSongsActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(HinSongsActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

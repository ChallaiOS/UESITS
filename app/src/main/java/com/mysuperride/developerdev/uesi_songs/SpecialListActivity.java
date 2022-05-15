package com.mysuperride.developerdev.uesi_songs;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.RequiresApi;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.NavigationView;

import androidx.navigation.ui.AppBarConfiguration;

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

import java.util.AbstractList;
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
import androidx.annotation.RequiresApi;


import androidx.appcompat.app.AppCompatActivity;

public class SpecialListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static AbstractList<itemModel> arrayList4;
    private AppBarConfiguration mAppBarConfiguration;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    itemModel model;
    Context context;
    RecyclerView recyclerView;
    RecycleSpecialAdapter adapter;

    ArrayList<itemModel> arrayList1;
    ArrayList<itemModel> arrayList2;
    ArrayList<itemModel> arrayList3;

    androidx.appcompat.widget.SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_special_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView  = findViewById(R.id.recycler5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();

        Intent intent = getIntent();
        final String obj1 = intent.getStringExtra("carryObj1");
        Log.d("obj1  is new",obj1);

        arrayList1 =  MySingleTon.getInstance().arrayList1;


        itemModel  temp = getStudentByName(arrayList1, obj1);

        Log.d("item new", String.valueOf(temp));


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

        Intent intent1 = getIntent();
        String title = intent1.getStringExtra("carryObj2");

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + title + "</font>")));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF5777E3));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }


    private static itemModel getStudentByName(ArrayList<itemModel> arrayList1, String name) {

        itemModel result = null;
        for (itemModel temp : arrayList1) {
            if (name.equals(temp.getLocal_category())) {
                result = temp;
                arrayList4.add(result);
                MySingleTon.getInstance().arrayOfSpecial_Songs = result;
                MySingleTon.getInstance().arrayList4 = (ArrayList<itemModel>) arrayList4;
            }
        }
        return result;
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
                for (itemModel model :arrayList4){
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
                Intent intent1 = new Intent(SpecialListActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(SpecialListActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(SpecialListActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(SpecialListActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(SpecialListActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(SpecialListActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(SpecialListActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(SpecialListActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(SpecialListActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(SpecialListActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(SpecialListActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(SpecialListActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(SpecialListActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(SpecialListActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

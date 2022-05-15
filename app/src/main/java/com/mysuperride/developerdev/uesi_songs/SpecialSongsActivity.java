package com.mysuperride.developerdev.uesi_songs;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
//import android.support.design.widget.NavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.ui.AppBarConfiguration;

//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.content.Intent;
//import android.support.v4.view.GravityCompat;
//import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import 	androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

public class SpecialSongsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_songs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final ListView listView = (ListView) findViewById(R.id.listCatagory);

        ArrayList<Object> list = new ArrayList<>();

        list.add(new String("UESI-AIMS"));
        list.add(new CategoryItem("EVANGELISM"));
        list.add(new CategoryItem("FELLOWSHIP"));
        list.add(new CategoryItem("TESTIMONY"));
        list.add(new CategoryItem("MISSIONS"));


        list.add(new String("CATEGORIES"));
        list.add(new CategoryItem("PRAISE AND WORSHIP"));
        list.add(new CategoryItem("EVANGELISTIC CAMPS & RETREATS"));
        list.add(new CategoryItem("DISCIPlESHIP TRAINING CAMP"));
        list.add(new CategoryItem("LEADERSHIP TRAINING CAMP"));
        list.add(new CategoryItem("SPIRITUAL REFLECTION"));
        list.add(new CategoryItem("CHRISTMAS"));
        list.add(new CategoryItem("PRAYER"));


        listView.setAdapter(new CategoryAdapter(this,list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                       @Override
                                       public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                                           if (position == 1 || position == 7){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "gospel");

                                               intent.putExtra("carryObj2","EVANGELISM");

                                               startActivity(intent);

                                           }

                                           else  if (position == 2){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "fellowship");

                                               intent.putExtra("carryObj2","FELLOWSHIP");

                                               startActivity(intent);

                                           }

                                           else  if (position == 3){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "testimony");

                                               intent.putExtra("carryObj2","TESTIMONY");

                                               startActivity(intent);

                                           }

                                           else  if (position == 4){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "missions");

                                               intent.putExtra("carryObj2","MISSIONS");

                                               startActivity(intent);

                                           }

                                           else  if (position == 6){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "praise");

                                               intent.putExtra("carryObj2","PRAISE & WORSHIP");

                                               startActivity(intent);

                                           }

                                           else  if (position == 8){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "discipleship");

                                               intent.putExtra("carryObj2","DISCIPlESHIP");

                                               startActivity(intent);

                                           }

                                           else  if (position == 9){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "leadership");

                                               intent.putExtra("carryObj2","LEADERSHIP");

                                               startActivity(intent);

                                           }

                                           else  if (position == 10){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "spiritual");

                                               intent.putExtra("carryObj2","SPIRITUAL REFLECTION");

                                               startActivity(intent);

                                           }

                                           else  if (position == 11){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "christmas");

                                               intent.putExtra("carryObj2","CHRISTMAS");

                                               startActivity(intent);

                                           }

                                           else  if (position == 12){

                                               Intent intent = new Intent(SpecialSongsActivity.this,SpecialListActivity.class);

                                               intent.putExtra("carryObj1", "prayer");

                                               intent.putExtra("carryObj2","PRAYER");

                                               startActivity(intent);

                                           }

                                        }
                                   });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        item.setVisible(false);
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
                Intent intent1 = new Intent(SpecialSongsActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(SpecialSongsActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(SpecialSongsActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(SpecialSongsActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(SpecialSongsActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(SpecialSongsActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(SpecialSongsActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(SpecialSongsActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(SpecialSongsActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(SpecialSongsActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(SpecialSongsActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(SpecialSongsActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(SpecialSongsActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(SpecialSongsActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

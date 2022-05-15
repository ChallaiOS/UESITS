package com.mysuperride.developerdev.uesi_songs;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutUsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    TextView aboutUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aboutUs = findViewById(R.id.aboutus);
        aboutUs.setMovementMethod(new ScrollingMovementMethod());
        aboutUs.setText("Vidyarthi-Geethavali Application has contains 651 songs in telugu,english and hindi languages\n\nPowered by :\nAPLETJAR\n\n\nPowered by :APLETJAR We provide high end user friendly responsive websites. People can enjoy websites Interface and feel real time experience in any device\n\nWe transform your thoughts with a touch of technology into websites, android and iOS apps. Believing in technology makes us to create our own success in a smart way.\n\nAndroid and iOS Apps are about to give an awestruck experience in app market. Improve your business through an app explore in present running technology.\n");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

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
                Intent intent1 = new Intent(AboutUsActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(AboutUsActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(AboutUsActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(AboutUsActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(AboutUsActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(AboutUsActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(AboutUsActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(AboutUsActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(AboutUsActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(AboutUsActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(AboutUsActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(AboutUsActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(AboutUsActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(AboutUsActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

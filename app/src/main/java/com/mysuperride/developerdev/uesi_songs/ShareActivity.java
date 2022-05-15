package com.mysuperride.developerdev.uesi_songs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ShareActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    Button shareBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        shareBtn = (Button) findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"app");

                String str = "UESI - Vidyarthi_Geethavali iOS App:\n https://apps.apple.com/us/app/vidyarthi-geethavali/id1546450568\n\n\n" + "UESI - Vidyarthi_Geethavali Android App:\n https://play.google.com/store/apps/details?id=com.mysuperride.developerdev.uesi_songs";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,str);
                startActivity(Intent.createChooser(shareIntent, "Share via"));


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
                Intent intent1 = new Intent(ShareActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_english:
                Intent intent2 = new Intent(ShareActivity.this, EngSongsActivity.class);
                startActivity(intent2);
                break;


            case R.id.nav_hindi:
                Intent intent3 = new Intent(ShareActivity.this, HinSongsActivity.class);
                startActivity(intent3);
                break;

            case R.id.nav_special:
                Intent intent4 = new Intent(ShareActivity.this, SpecialSongsActivity.class);
                startActivity(intent4);
                break;

            case R.id.nav_share:
                Intent intent5 = new Intent(ShareActivity.this, ShareActivity.class);
                startActivity(intent5);
                break;

            case R.id.nav_contactus:
                Intent intent6 = new Intent(ShareActivity.this, ContactUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_aboutus:
                Intent intent7 = new Intent(ShareActivity.this, AboutUsActivity.class);
                startActivity(intent7);
                break;

            case R.id.nav_terms:
                Intent intent8 = new Intent(ShareActivity.this, TermsCondtionsActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_privacy:
                Intent intent9 = new Intent(ShareActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent9);
                break;
            case R.id.nav_feedback:
                Intent intent10 = new Intent(ShareActivity.this, FeedbackActivity.class);
                startActivity(intent10);
                break;

            case R.id.introduction:
                Intent intent11 = new Intent(ShareActivity.this, IntroductionSSActivity.class);
                startActivity(intent11);
                break;

            case R.id.first_year:
                Intent intent12 = new Intent(ShareActivity.this, YearOneSSActivity.class);
                startActivity(intent12);
                break;

            case R.id.second_year:
                Intent intent13 = new Intent(ShareActivity.this, YearTwoSSActivity.class);
                startActivity(intent13);
                break;

            case R.id.third_year:
                Intent intent14 = new Intent(ShareActivity.this, YearThreeActivity.class);
                startActivity(intent14);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

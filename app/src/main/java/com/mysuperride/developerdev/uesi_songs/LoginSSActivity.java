package com.mysuperride.developerdev.uesi_songs;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSSActivity extends AppCompatActivity {

    Button submit,backBtn,forgotBtn,labelBtn;
    EditText emailAddress,password;
    public static String globalPreferenceName  = "com.my.pay";
    String email,pass;
    String responseCheck = "";

    SimpleArcDialog mDialog;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        setContentView(R.layout.activity_login_ssactivity);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.login);
        emailAddress = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);

//        textView = (TextView) findViewById(R.id.loginLabel);
//        textView.setTextSize(20);

        labelBtn = (Button) findViewById(R.id.loginLabel);
        labelBtn.setTextSize(20);

        submit = (Button) findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailAddress.getText().toString().trim();
                pass = password.getText().toString().trim();

                if(email.isEmpty()){
                    emailAddress.setError("Email Address is required");
                    emailAddress.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                    emailAddress.setError("Enter a valid Email Address");
                    emailAddress.requestFocus();
                    return;

                }

                if(pass.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }

                if (isNetworkAvailable()){


                    mDialog = new SimpleArcDialog(LoginSSActivity.this);
                    mDialog.setConfiguration(new ArcConfiguration(LoginSSActivity.this));
                    mDialog.setTitle("Please Wait...");
                    mDialog.show();

                    validateUser();

                }

                else{
                    Toast.makeText(LoginSSActivity.this,"Please check your internet connection",Toast.LENGTH_LONG).show();
                }




            }
        });

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(LoginSSActivity.this, IntroductionSSActivity.class);
                startActivity(intent1);
            }
        });


        forgotBtn = (Button) findViewById(R.id.forgotBtn);
        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(LoginSSActivity.this, ForgotSSActivity.class);
                startActivity(intent1);
            }
        });

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" +  "ALREADY DONATED USER"+ "</font>")));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF5777E3));
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.register:
                        startActivity(new Intent(getApplicationContext(),RegisterSSActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.login:
//                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void validateUser(){

        Call<DefaultResponse> call = RetrofitClientSS
                .getInstance()
                .getApi()
                .vadidateUser(email,pass);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if (response.code() == 200){

                    DefaultResponse dr = response.body();

                    mDialog.hide();

//                    responseCheck = dr.isMsg();

//                    Log.d("dr is", String.valueOf(dr.isMsg()));

                    if (dr.isMsg().equalsIgnoreCase("Invalid Email")){

                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginSSActivity.this);
                        builder.setMessage("Invalid Email or Password")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

//                                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intent1);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();



                    }
                    else if (dr.isMsg().equalsIgnoreCase("YES")){

                        SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceName,MODE_PRIVATE).edit();
                        editor.putString("pay_key","DonationDone");
                        editor.commit(); // commit changes

                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginSSActivity.this);
                        builder.setMessage("Downloaded Book")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent intent1 = new Intent(LoginSSActivity.this, YearOneSSActivity.class);
                                        startActivity(intent1);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }



//                    Toast.makeText(LoginActivity.this,dr.isMsg(),Toast.LENGTH_LONG).show();
                }

//                else if (response.code() == 422){
//
//                    Toast.makeText(LoginActivity.this,"User Not Exits",Toast.LENGTH_LONG).show();
//                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(LoginSSActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}

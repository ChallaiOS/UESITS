package com.mysuperride.developerdev.uesi_songs;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotSSActivity extends AppCompatActivity {

    Button submit,backBtn,labelBtn;
    EditText emailAddress;
    String email;
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

        setContentView(R.layout.activity_forgot_ssactivity);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" +  "FORGOT PASSWORD"+ "</font>")));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF5777E3));
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(true);}

//        textView = (TextView) findViewById(R.id.forgotPass);
//        textView.setTextSize(20);

        labelBtn = (Button) findViewById(R.id.forgotLabel);
        labelBtn.setTextSize(20);

        submit = (Button) findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailAddress = findViewById(R.id.editText1);

                email = emailAddress.getText().toString().trim();

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

                if(isNetworkAvailable()){
                    mDialog = new SimpleArcDialog(ForgotSSActivity.this);
                    mDialog.setConfiguration(new ArcConfiguration(ForgotSSActivity.this));
                    mDialog.setTitle("Please Wait...");
                    mDialog.show();
                    getMyPassword();
                }
                else {
                    Toast.makeText(ForgotSSActivity.this,"Please check your internet connection",Toast.LENGTH_LONG).show();
                    return;

                }


            }
        });

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ForgotSSActivity.this, IntroductionSSActivity.class);
                startActivity(intent1);
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getMyPassword(){

        Call<DefaultResponse> call = RetrofitClientSS
                .getInstance()
                .getApi()
                .forgotUserPassword(email);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if (response.code() == 200){

                    DefaultResponse dr = response.body();

                    mDialog.hide();

                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotSSActivity.this);
                    builder.setMessage(dr.isFmsg())
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things

//                                    Intent intent1 = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
//                                    startActivity(intent1);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

//                    Log.d("drGkkk", String.valueOf(dr));
//                    Toast.makeText(ForgotPasswordActivity.this,dr.isMsg(),Toast.LENGTH_LONG).show();
                }

//                else if (response.code() == 422){
//
//                    Toast.makeText(ForgotPasswordActivity.this,"User Already Exist",Toast.LENGTH_LONG).show();
//                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}

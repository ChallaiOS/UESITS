package com.mysuperride.developerdev.uesi_songs;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mysuperride.developerdev.uesi_songs.LoginSSActivity.globalPreferenceName;

//import com.cashfree.pg.CFPaymentService;

import java.util.Map;

//import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;


//https://www.youtube.com/watch?v=67GSq31fxoY
//https://www.youtube.com/watch?v=7sG3QEUWzYk&t=374s
//https://www.youtube.com/watch?v=wzK19AaEgzk - Android Volley Get and Post Tutorial

public class RegisterSSActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,PaymentResultListener {

    Button submit,labelBtn,donatedAlreadyButton;
    Spinner spinner,spinnerState;
    String[] moneyList,stateList;
    String email,mobile,user,confirmPass,donationStatus,pass,paymentid;
    String money = "100";
    String state = "Choose your State";

    EditText amount,username,mobileNumber,emailAddress,password,confirm;
    private static final String VALUE_STRING = "%d";
    private static final String VALUE_STRING2 = "%s";
    private HintSpinner<String> defaultHintSpinner,stateHintSpinner;
    private List<String> defaults,defaults2;
    private static final String TAG = RegisterSSActivity.class.getSimpleName();
    SimpleArcDialog mDialog;
    TextView textView;
    Checkout checkout = new Checkout();

//Instamojo
//    private static final HashMap<Instamojo.Environment, String> env_options = new HashMap<>();
//
//    static {
//        env_options.put(Instamojo.Environment.TEST, "https://test.instamojo.com/");
//        env_options.put(Instamojo.Environment.PRODUCTION, "https://api.instamojo.com/");
//    }

    private AlertDialog dialog;
    private AppCompatEditText nameBox, emailBox, phoneBox, amountBox, descriptionBox;
    //private Instamojo.Environment mCurrentEnv = Instamojo.Environment.PRODUCTION;
    //private boolean mCustomUIFlow = false;
    // private MyBackendService myBackendService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_register_ssactivity);

        Checkout.preload(getApplicationContext());
        //Instamojo.getInstance().initialize(RegisterActivity.this, mCurrentEnv);


        labelBtn = (Button) findViewById(R.id.registerLabel);
        labelBtn.setTextSize(20);

        moneyList = new String[]{"100", "200","300","400","500","600","700","800","900","1000"};
        stateList = new String[]{"Andhra Pradesh", "Telangana"};


        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" +  "NEW USER"+ "</font>")));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF5777E3));
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }


        this.initStateHintSpinner();
        this.initDefaultHintSpinner();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigator);

        bottomNavigationView.setSelectedItemId(R.id.register);

        donatedAlreadyButton = (Button) findViewById(R.id.donatedAlreadyBtn);

        donatedAlreadyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(RegisterSSActivity.this, LoginSSActivity.class);
                startActivity(intent1);

            }
        });

        submit = (Button) findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instamojo.getInstance().initialize(RegisterActivity.this, mCurrentEnv);

                username = findViewById(R.id.editText1);
                mobileNumber = findViewById(R.id.editText2);
                emailAddress = findViewById(R.id.editText3);
                password = findViewById(R.id.editText4);
                confirm = findViewById(R.id.editText5);
                spinner = findViewById(R.id.spinnerDonation);
                spinnerState = findViewById(R.id.spinnerState);
                user = username.getText().toString().trim();
                mobile = mobileNumber.getText().toString().trim();
                email = emailAddress.getText().toString().trim();
                pass = password.getText().toString().trim();
                confirmPass = confirm.getText().toString().trim();


//                if (state.equals("Choose your State")) {
//                    Toast.makeText(RegisterActivity.this, "Please select your state", Toast.LENGTH_LONG).show();
//                    return;
//                }


//                if (money.equals("Choose your Donation Amount")) {
//                    Toast.makeText(RegisterActivity.this, "Please select your donation amount", Toast.LENGTH_LONG).show();
//                    return;
//                }


                if(user.isEmpty()){
                    username.setError("UserName is required");
                    username.requestFocus();
                    return;
                }

                if(mobile.isEmpty()){
                    mobileNumber.setError("Mobile Number is required");
                    mobileNumber.requestFocus();
                    return;
                }

                if(!Patterns.PHONE.matcher(mobile).matches()){
                    mobileNumber.setError("Enter a valid mobile number");
                    mobileNumber.requestFocus();
                    return;
                }

                if(email.isEmpty()){
                    emailAddress.setError("Email Address is required");
                    emailAddress.requestFocus();
                    return;
                }

                if(pass.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }

                if(pass.length() < 6){
                    password.setError("Password required minimum 6 characters");
                    password.requestFocus();
                    return;
                }



//                if(confirmPass.isEmpty()){
//                    confirm.setError("Confirm password is required");
//                    confirm.requestFocus();
//                    return;
//                }

//                if (!pass.equals(confirmPass)) {
//
//                    password.setError("Passwords not matched");
//                    password.requestFocus();
//                    return;
//
//
//                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                    emailAddress.setError("Enter a valid Email Address");
                    emailAddress.requestFocus();
                    return;

                }

                else
                {

                    mDialog = new SimpleArcDialog(RegisterSSActivity.this);
                    mDialog.setConfiguration(new ArcConfiguration(RegisterSSActivity.this));
                    mDialog.setTitle("Please Wait...");
                    mDialog.show();
                    startPayment();
                }
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.register:
//                        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
//                        overridePendingTransition(0,0);
                        return true;

                    case R.id.login:
                        startActivity(new Intent(getApplicationContext(),LoginSSActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });



    }


    private void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void initDefaultHintSpinner() {
        defaults = new ArrayList<>();
        defaults.add(String.format(VALUE_STRING, 100));
        defaults.add(String.format(VALUE_STRING, 200));
        defaults.add(String.format(VALUE_STRING, 300));
        defaults.add(String.format(VALUE_STRING, 400));
        defaults.add(String.format(VALUE_STRING, 500));
        defaults.add(String.format(VALUE_STRING, 1000));
        defaults.add(String.format(VALUE_STRING, 2000));
        defaults.add(String.format(VALUE_STRING, 3000));
        defaults.add(String.format(VALUE_STRING, 4000));
        defaults.add(String.format(VALUE_STRING, 5000));

        Spinner defaultSpinner = (Spinner) findViewById(R.id.spinnerDonation);


        defaultHintSpinner = new HintSpinner<>(
                defaultSpinner,
                // Default layout - You don't need to pass in any layout id, just your hint text and
                // your list data
                new HintAdapter<>(this, R.string.tv_title, defaults),
                new HintSpinner.Callback<String>() {
                    @Override
                    public void onItemSelected(int position, String itemAtPosition) {
                        // Here you handle the on item selected event (this skips the hint selected
                        // event)
                        showSelectedItem(itemAtPosition);
                    }
                });

        defaultHintSpinner.init();
    }


    private void initStateHintSpinner() {
        defaults2 = new ArrayList<>();
        defaults2.add("Andhra Pradesh");
        defaults2.add("Telangana");

        Spinner stateSpinner = (Spinner) findViewById(R.id.spinnerState);

        stateHintSpinner = new HintSpinner<>(
                stateSpinner,
                // Default layout - You don't need to pass in any layout id, just your hint text and
                // your list data
                new HintAdapter<>(this, R.string.tv_state, defaults2),
                new HintSpinner.Callback<String>() {
                    @Override
                    public void onItemSelected(int position, String itemAtPosition) {
                        // Here you handle the on item selected event (this skips the hint selected
                        // event)
                        showSelectedItem2(itemAtPosition);
                    }
                });

        stateHintSpinner.init();
    }


    private void showSelectedItem(String itemAtPosition) {
        Log.d("money",itemAtPosition);
        money = itemAtPosition;
    }

    private void showSelectedItem2(String itemAtPosition) {
        state = itemAtPosition;
        Log.d("state",itemAtPosition);
    }

    public void startPayment() {

//        final Checkout checkout = new Checkout();

//        if (state.equalsIgnoreCase("Andhra Pradesh")){
//
//            initiatepaymentCashFree();
//
//        }

        //  else if (state.equalsIgnoreCase("Telangana")) {

        double finalMoney = Double.parseDouble(money);

        finalMoney = finalMoney * 100;

        final Activity activity = this;


        checkout.setKeyID("rzp_live_8VfOQxZZPfUDVl");
//              checkout.setKeyID("rzp_test_9vWiIFNVw2trbO");

        try {

            JSONObject preFill = new JSONObject();
            preFill.put("email",email);
            preFill.put("contact",mobile);

            JSONObject options = new JSONObject();
            options.put("name", "APLETJAR");
            options.put("description", "Search the Scriptures");
            options.put("image", "data:image/jpeg;base64,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");
            options.put("currency", "INR");
            options.put("amount", finalMoney);
            options.put("prefill",preFill);
            checkout.open(activity,options);

        }
        catch(Exception e) {

            Log.e(TAG,"Error in starting Razorpay Checkout",e);
            mDialog.hide();

        }

        //  }

    }


    @Override
    public void onPaymentSuccess(String paymentID) {

        paymentid = paymentID;

        donationStatus = "YES";

        Log.d("PAYMENTgg",paymentid);

        donatedAmountSuccess();



    }

    @Override
    public void onPaymentError(int i, String s) {

        try{
            Toast.makeText(this,s,Toast.LENGTH_LONG).show();
            mDialog.hide();

        }
        catch(Exception e){
            Log.e("onPaymentError","Exception in payment",e);
            mDialog.hide();

        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Same request code for all payment APIs.
//        Log.d(TAG, "ReqCode : " + CFPaymentService.REQ_CODE);
        Log.d("m", "API Response : ");
        //Prints all extras. Replace with app logic.
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.d("resp", key + " : " + bundle.getString(key));
                    }
                }
        }
    }

//Instamojo
//    public void instamojoPaymentMethod(){
//
//
//        Call<DefaultResponse> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .createInstamojoPaymentID(user,mobile,email,money,"UESISONGS");
//
//
//        call.enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//
//                if (response.code() == 200){
//
//                    DefaultResponse dr = response.body();
//                    mDialog.hide();
//
//                    Log.d("successMa",dr.isId());
//                    String orderIDD = dr.isId();
//                    initiateSDKPayment(orderIDD);
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//
//                mDialog.hide();
//
//                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//
//    }

//    private void initiateSDKPayment(String orderID) {
//        Log.d("orderIDhere",orderID);
//        Instamojo.getInstance().initiatePayment(this,orderID, this);
////        Intent intent1 = new Intent(RegisterActivity.this, RegisterActivity.class);
////        startActivity(intent1);
//    }


//
//    @Override
//    public void onInstamojoPaymentComplete(String orderID, String transactionID, String paymentID, String paymentStatus) {
//        Log.d(TAG, "Payment complete. Order ID: " + orderID + ", Transaction ID: " + transactionID
//                + ", Payment ID:" + paymentID + ", Status: " + paymentStatus);
////        showToast("Payment complete. Order ID: " + orderID + ", Transaction ID: " + transactionID
////                + ", Payment ID:" + paymentID + ", Status: " + paymentStatus);
//
//
//        paymentid = paymentID;
//
//        donationStatus = "YES";
//
//        Log.d("PAYMENTInstamojo",paymentid);
//
//        donatedAmountSuccess();
//
//
//    }
//
//    @Override
//    public void onPaymentCancelled() {
//        Log.d(TAG, "Payment cancelled");
//        showToast("Payment cancelled by user");
//        mDialog.hide();
//
//
//    }
//
//    @Override
//    public void onInitiatePaymentFailure(String errorMessage) {
//        Log.d(TAG, "Initiate payment failed");
//        showToast("Initiating payment failed. Error: " + errorMessage);
//        mDialog.hide();
//
//
//    }



    public void donatedAmountSuccess(){


        Call<DefaultResponse> call = RetrofitClientSS
                .getInstance()
                .getApi()
                .createNewUser(user,mobile,email,pass,pass,money,paymentid,donationStatus);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if (response.code() == 200){

                    DefaultResponse dr = response.body();
                    mDialog.hide();

                    if (dr.isEmail().equalsIgnoreCase("The email has already been taken")){


                        SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceName,MODE_PRIVATE).edit();
                        editor.putString("pay_key","DonationDone");
                        editor.commit(); // commit changes

                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterSSActivity.this);
                        builder.setMessage("Payment Success....Downloaded Book")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent intent1 = new Intent(RegisterSSActivity.this, YearOneSSActivity.class);
                                        startActivity(intent1);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }

                    else{

                        mDialog.hide();

                        SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceName,MODE_PRIVATE).edit();
                        editor.putString("pay_key","DonationDone");
                        editor.commit(); // commit changes

                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterSSActivity.this);
                        builder.setMessage("Payment Success....Downloaded Book")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent intent1 = new Intent(RegisterSSActivity.this, YearOneSSActivity.class);
                                        startActivity(intent1);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                mDialog.hide();

                Toast.makeText(RegisterSSActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(RegisterSSActivity.this,adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
        mDialog.hide();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mDialog.hide();

    }
}

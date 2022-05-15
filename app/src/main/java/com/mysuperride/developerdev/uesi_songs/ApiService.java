package com.mysuperride.developerdev.uesi_songs;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("cashfree_token_api.php")
    Call<DefaultResponse> getToken(@Field("orderAmount") String orderAmount, @Field("orderId") String orderId);

    @FormUrlEncoded
    @POST("user/forgot")
    Call<DefaultResponse> forgotUserPassword(

            @Field("email") String email
    );


    @FormUrlEncoded
    @POST("user/create")
    Call<DefaultResponse> createNewUser(

            @Field("name") String name,
            @Field("mobile_number") String mobile_number,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("donation_amount") String donation_amount,
            @Field("donation_id") String donation_id,
            @Field("donation_status") String donation_status

    );

    @FormUrlEncoded
    @POST("user/login")
    Call<DefaultResponse> vadidateUser(

            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("payment")
    Call<DefaultResponse> createInstamojoPaymentID(

            @Field("buyer_name") String buyer_name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("amount") String amount,
            @Field("purpose") String purpose

    );

}

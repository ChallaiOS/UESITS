package com.mysuperride.developerdev.uesi_songs;

import com.google.gson.annotations.SerializedName;

public class GetOrderIDResponse {

    @SerializedName("id")
    private String orderID;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}

package com.mysuperride.developerdev.uesi_songs;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class DefaultResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("cftoken")
    @Expose
    private String cftoken;

    @SerializedName("error")
    private String err;

    @SerializedName("donation_status")
    private String msg;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("email")
    private String email;

    @SerializedName("msg")
    private String fmsg;

    @SerializedName("order_id")
    private String id;

    @SerializedName("longurl")
    private String longurl;

    public DefaultResponse(String err, String msg,String email,String fmsg,String id,String longurl) {
        this.err = err;
        this.msg = msg;
        this.email = email;
        this.fmsg  = fmsg;
        this.id = id;
        this.longurl = longurl;
    }

    public String isErr() {
        return err;
    }

    public String isMsg() {
        return msg;
    }
    public String isEmail() {
        return email;
    }
    public String isFmsg() {
        return fmsg;
    }
    public String isId() {
        return id;
    }
    public String isLongurl() {
        return longurl;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCftoken() {
        return cftoken;
    }

    public void setCftoken(String cftoken) {
        this.cftoken = cftoken;
    }


}



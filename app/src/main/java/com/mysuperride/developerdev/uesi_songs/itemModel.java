package com.mysuperride.developerdev.uesi_songs;



import java.io.Serializable;

public class itemModel implements Serializable {

    String local_id;
    String local_title;
    String local_hint;
    String local_category;
    String local_categorycount;
    String local_text;
    String list_day;
    String list_month;
    String local_search;

    public String getLocal_search() {
        return local_search;
    }

    public void setLocal_search(String local_search) {
        this.local_search = local_search;
    }

    public String getList_day() {
        return list_day;
    }

    public void setList_day(String list_day) {
        this.list_day = list_day;
    }

    public String getList_month() {
        return list_month;
    }

    public void setList_month(String list_month) {
        this.list_month = list_month;
    }

    public String getLocal_text() {
        return local_text;
    }

    public void setLocal_text(String local_text) {
        this.local_text = local_text;
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public void setLocal_title(String local_title) {
        this.local_title = local_title;
    }

    public void setLocal_hint(String local_hint) {
        this.local_hint = local_hint;
    }

    public void setLocal_category(String local_category) {
        this.local_category = local_category;
    }

    public void setLocal_categorycount(String local_categorycount) {
        this.local_categorycount = local_categorycount;
    }

    public String getLocal_id() {
        return local_id;
    }

    public String getLocal_title() {
        return local_title;
    }

    public String getLocal_hint() {
        return local_hint;
    }

    public String getLocal_category() {
        return local_category;
    }

    public String getLocal_categorycount() {
        return local_categorycount;
    }
}






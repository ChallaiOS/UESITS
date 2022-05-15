package com.mysuperride.developerdev.uesi_songs;

import java.util.ArrayList;

public class MySingleTon {
    private static final MySingleTon ourInstance = new MySingleTon();

    itemModel arrayOfSongs;
    itemModel arrayOfEng_Songs;
    itemModel arrayOfHin_Songs;
    itemModel arrayOfSpecial_Songs;

    ArrayList<itemModel> arrayList1;
    ArrayList<itemModel> arrayList2;
    ArrayList<itemModel> arrayList3;
    ArrayList<itemModel> arrayList4;



    public static MySingleTon getInstance() {
        return ourInstance;
    }

    private MySingleTon() {
    }
}

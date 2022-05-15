package com.mysuperride.developerdev.uesi_songs;

import java.util.ArrayList;

public class MySingleTonSS {
    private static final MySingleTonSS ourInstance = new MySingleTonSS();

    itemModel arrayOfYearOneList;
    itemModel arrayOfYearTwoList;
    itemModel arrayOfYearThreeList;
    itemModel arrayOfSpecial_Songs;

    ArrayList<itemModel> arrayListSS1;
    ArrayList<itemModel> arrayListSS2;
    ArrayList<itemModel> arrayListSS3;
    ArrayList<itemModel> arrayListSS4;



    public static MySingleTonSS getInstance() {
        return ourInstance;
    }

    private MySingleTonSS() {
    }
}

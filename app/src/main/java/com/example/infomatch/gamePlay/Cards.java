package com.example.infomatch.gamePlay;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Cards {

    private int numOfPairs;
    private String[] qArray;
    private String[] aArray;

    private Integer[] iArray;
    private HashMap<String, String> qaPair;
    private Card[] cArray;

    public Cards(int numOfPairs, String[] qArray, String[] aArray) {
        this.numOfPairs = numOfPairs;
        this.qaPair = new HashMap<String, String>();
        this.iArray = new Integer[aArray.length];
        for (int i = 0; i < iArray.length; i++) {
            iArray[i] = i;
        }

        Collections.shuffle(Arrays.asList(iArray));

        for (int i = 0; i<numOfPairs/2;i++) {
            this.qaPair.put(qArray[iArray[i]],aArray[iArray[i]]);
        }

    }

    public void setNumOfPairs(int numOfPairs) {
        this.numOfPairs = numOfPairs;
    }


    public void setqArray(String[] qArray) {
        this.qArray = qArray;
    }

    public void setaArray(String[] aArray) {
        this.aArray = aArray;
    }

    public void setcArray(Card[] cArray) {
        this.cArray = cArray;
    }

    public HashMap<String, String> getQaPair() {
        return this.qaPair;
    }


}

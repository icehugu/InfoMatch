package com.example.infomatch.gamePlay;

import java.util.HashMap;

public class Cards {

    private int numOfPairs;
    private String[] qArray;
    private String[] aArray;
    private HashMap<String, String> qaPair;
    private Card[] cArray;

    public Cards(int numOfPairs, String[] qArray, String[] aArray) {
        this.numOfPairs = numOfPairs;
//        this.qArray = qArray;
//        this.aArray = aArray;
        this.qaPair = new HashMap<String, String>();
        for(int i =0;i<6;i++) {
            this.qaPair.put("q" + i,"a" + i);
            //this.cArray[i * 2].setContent("q" + i * 2);
            //this.cArray[i * 2 + 1].setContent("q" + i * 2 + 1);
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


}

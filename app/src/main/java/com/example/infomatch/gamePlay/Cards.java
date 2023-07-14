package com.example.infomatch.gamePlay;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Cards {

    private int numOfPairs;
    private String[] qArray;
    private String[] aArray;

    private Integer[] iArray;
    private HashMap<String, String> qaPair;

    public Cards(int numOfPairs, String[] qArray, String[] aArray) {
        this.numOfPairs = numOfPairs;
        this.qArray = qArray;
        this.aArray = aArray;
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

    public void reShuffle(){
        this.qaPair.clear();

        for (int i = 0; i < iArray.length; i++) {
            iArray[i] = i;
        }

        Collections.shuffle(Arrays.asList(iArray));

        for (int i = 0; i<numOfPairs/2;i++) {
            this.qaPair.put(this.qArray[iArray[i]],this.aArray[iArray[i]]);
        }
    }

    public HashMap<String, String> getQaPair() {
        return this.qaPair;
    }


}

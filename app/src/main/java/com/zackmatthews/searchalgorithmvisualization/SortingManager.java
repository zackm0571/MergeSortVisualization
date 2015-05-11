package com.zackmatthews.searchalgorithmvisualization;

import android.graphics.Point;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zackmathews on 5/7/15.
 */
public class SortingManager {



    private List<DataObject> dataSet;
    private Random rand = new Random();

    public List<DataObject> getDataSet() {
        return dataSet;
    }
    public void setDataSet(List<DataObject> dataSet) { this.dataSet = dataSet;}
    private MergeSorter mergeSorter;

    public SortingManager(List<DataObject> dataSet, Point screenSize){
        this.dataSet = dataSet;

        if(dataSet == null){ //seed data set if null
            seedData(screenSize, 250);
        }
         mergeSorter = new MergeSorter(this.dataSet, this); //initialize merge sorter class
    }


    public void seedData(Point screenSize, int seedInterval){ //seed data
       List data = new ArrayList();

        for(int i = screenSize.x; i > 0; i-- ){

            if(i % seedInterval == 0){
                rand.setSeed(SystemClock.currentThreadTimeMillis());
            }
                int val = rand.nextInt(screenSize.y);
                data.add(new DataObject(val));
        }


        setDataSet(data);
        mergeSorter = new MergeSorter(this.dataSet, this);
    }


    public void executeMergeSort(){ //execute merge sort
       mergeSorter.execute();
    }


}

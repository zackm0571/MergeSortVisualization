package com.zackmatthews.searchalgorithmvisualization;

import android.graphics.Point;
import android.os.Handler;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by zackmathews on 5/7/15.
 */
public class SortingManager {

    public ArrayList<DataObject> getDataSet() { return dataSet; }
    public void setDataSet(final ArrayList<DataObject> dataSet) { this.dataSet = dataSet;}


    private Handler handler = new Handler();
    private ArrayList<DataObject> dataSet = new ArrayList<>();
    private Random rand = new Random();

    private static MergeSorter mergeSorter;
    private static boolean isGenerating = false;


    public SortingManager(final ArrayList<DataObject> dataSet, Point screenSize){
        this.dataSet = dataSet;

        if(dataSet == null){ //seed data set if null
            seedData(screenSize, 250);
        }

    }


    public MergeSorter getMergeSorter(){
        if(mergeSorter == null){
            mergeSorter = new MergeSorter(this.dataSet, this); //initialize merge sorter class
        }

        return mergeSorter;
    }

    public void seedData(Point screenSize, int seedInterval){ //seed data
       ArrayList<DataObject> data = new ArrayList();

        isGenerating = true;
        for(int i = seedInterval; i > 0; i-- ){

            if(i % seedInterval == 0){
                rand.setSeed(SystemClock.currentThreadTimeMillis() * (i*i) - i);
            }
                int val = rand.nextInt(screenSize.y - rand.nextInt(screenSize.y / 3));
                data.add(new DataObject(val));
        }


        setDataSet(data);

        isGenerating = false;
    }


    public void executeMergeSort(){ //execute merge sort

        if(!isGenerating && !getMergeSorter().isSorting) {
            getMergeSorter().setData(getDataSet());
            getMergeSorter().execute();

        }

        else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    executeMergeSort();
                }
            }, 250);
        }
    }


}

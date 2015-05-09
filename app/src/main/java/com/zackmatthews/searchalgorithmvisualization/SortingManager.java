package com.zackmatthews.searchalgorithmvisualization;

import android.graphics.Point;

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
    private MergeSorter mergeSorter;

    public SortingManager(List<DataObject> dataSet, Point screenSize){
        this.dataSet = dataSet;

        if(dataSet == null){ //seed data set if null
            this.dataSet = seedData(screenSize);
        }

        mergeSorter = new MergeSorter(this.dataSet); //initialize merge sorter class
    }


    public List<DataObject> seedData(Point screenSize){ //seed data
       List data = new ArrayList();

        for(int i = screenSize.x; i > 0; i-- ){

                int val = rand.nextInt(screenSize.y) + 50;
                data.add(new DataObject(val));
            }
        this.dataSet = data;
        return data;
    }


    public void executeMergeSort(){ //execute merge sort
       this.dataSet = mergeSorter.execute();
    }


}

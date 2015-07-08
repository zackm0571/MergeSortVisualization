package com.zackmatthews.searchalgorithmvisualization;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zackmathews on 5/8/15.
 */

public class MergeSorter {

    protected ArrayList<DataObject> data;
    protected DataObject[] helper; //temp data buffer
    protected SortingManager sortingManager;
    protected boolean isSorting = false;

    private Handler handler = new Handler();

    public MergeSorter(ArrayList<DataObject> data, SortingManager sortingManager){
        this.data = data;
        this.sortingManager = sortingManager;
        helper = new DataObject[data.size()];
    }


    public void setData(final ArrayList<DataObject> data){
        if(!isSorting){
            this.data = data;
            helper = new DataObject[data.size()];
        }

        else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setData(data);
                }
            }, 150);
        }
    }

    public void execute(){
        isSorting = true;
        MergeSortAsync mergeSortAsync = new MergeSortAsync();
        mergeSortAsync.execute();

    }

    private class MergeSortAsync extends AsyncTask<Void, Void, Void>{



        public List<DataObject> executeSort(){ //Executes merge sort and log data to console


            mergeSort(0, data.size() - 1);
            isSorting = false;
            return data;

        }

        private void mergeSort(int min, int max){

            if(min < max){ //continue if min less than max else data is sorted

                //Get index of middle element
                int mid = min + (max- min) / 2;

                //sort left side of collection
                mergeSort(min, mid);

                //sort right side of collection
                mergeSort(mid + 1, max);

                //combine sorted elements
                merge(min, mid, max);

            }


        }


        private void merge(int min, int mid, int max){



            //copy elements to helper array
            for(int i = min; i <= max; i++){
                helper[i] = data.get(i);
            }

            int i = min;
            int j = mid+1;
            int k = min;


            //move smallest elements from either left or right into original data set
            while(i <= mid && j <= max){
                if(helper[i].getI_val() <= helper[j].getI_val()){

                    data.set(k, helper[i]);
                    i++;
                }

                else{
                    data.set(k, helper[j]);
                    j++;
                }

                sortingManager.setDataSet(data);


                k++;
            }

            //copy remaining elements back into original data set
            while(i <= mid){
                data.set(k, helper[i]);
                sortingManager.setDataSet(data);
                k++;
                i++;
            }



        }



        @Override
        protected Void doInBackground(Void... params) {

            executeSort();

            return null;
        }
    }



}

package com.zackmatthews.searchalgorithmvisualization;

import android.os.AsyncTask;
import android.util.Log;
import java.util.List;

/**
 * Created by zackmathews on 5/8/15.
 */


public class MergeSorter {

    protected List<DataObject> data;
    protected DataObject[] helper; //temp data buffer
    protected SortingManager sortingManager;

    public MergeSorter(List<DataObject> data, SortingManager sortingManager){
        this.data = data;
        this.sortingManager = sortingManager;
        helper = new DataObject[data.size()];
    }


    public void execute(){
        MergeSortAsync mergeSortAsync = new MergeSortAsync();
        mergeSortAsync.execute();
    }

    private class MergeSortAsync extends AsyncTask<Void, Void, Void>{



        public List<DataObject> executeSort(){ //Executes merge sort and log data to console


            mergeSort(0, data.size() - 1);
            for(int i = 0; i < data.size(); i++) {
                Log.v("data at" + i, String.valueOf(data.get(i).getI_val())); //For debugging, prints out sorted data in order
            }
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

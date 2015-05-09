package com.zackmatthews.searchalgorithmvisualization;

/**
 * Created by zackmathews on 5/8/15.
 */
public class DataObject {
    //Data object wrapper

    private int i_val;
    private float f_val;


    public DataObject(int val){
        setI_val(val);
    }

    public DataObject(float val){
        setF_val(val);
    }


    public int getI_val() {
        return i_val;
    }

    public void setI_val(int i_val) {
        this.i_val = i_val;
    }


    public float getF_val() {
        return f_val;
    }

    public void setF_val(float f_val) {
        this.f_val = f_val;
    }





}

package com.zackmatthews.searchalgorithmvisualization;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import java.sql.SQLSyntaxErrorException;
import java.util.List;


/**
 * TODO: document your custom view class.
 */
public class RenderView extends View implements SurfaceHolder {


    public static Point screenSize = new Point();
    public SortingManager sortingManager;

    private TextPaint mNodePaint;
    private TextPaint mNumPaint;
    private Context context;
    private Button resetDataButton = null;
    private Button sortButton = null;
    private SeekBar seedInterval = null;
    private List<DataObject> dataObjects;


    public RenderView(Context context) {
        super(context);

        this.context = context;
        init(null, 0);
    }

    public RenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public RenderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {


        //Get screen dimensions
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(screenSize);

        // Set up a default TextPaint object
        mNodePaint = new TextPaint();
        mNodePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mNodePaint.setTextAlign(Paint.Align.LEFT);
        mNodePaint.setColor(Color.GRAY);

        //Set up number paint object
        mNumPaint = new TextPaint();
        mNumPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mNumPaint.setTextAlign(Paint.Align.CENTER);
        mNumPaint.setTextSize(32.0f);
        mNumPaint.setColor(Color.CYAN);
        mNumPaint.setAlpha(120);

        sortingManager = new SortingManager(null, screenSize); //passing null data set will seed the data set for you

        dataObjects = sortingManager.getDataSet(); //set local data set, will update in future to render sorting live

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.invalidate();

        canvas.drawColor(Color.BLACK); //render background

        List<DataObject> dataObjects = sortingManager.getDataSet();

        for(int i = 0; i < dataObjects.size(); i++){ //render each data object to screen

            DataObject dataObject = dataObjects.get(i);
            if(dataObject != null) {

                if(i <= screenSize.x) {
                    canvas.drawRect(((float) i), ((float)
                                    dataObject.getI_val()), ((float) i + 5),
                            screenSize.y, mNumPaint);
                }

                else{
                    break;
                }
            }
        }
    }

    public void initUI(){


        if(sortButton == null){ //check that sort button has initialized
            try {
                sortButton = (Button) this.getRootView().findViewById(R.id.mergeSortButton);
                sortButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sortingManager.executeMergeSort();

                    }
                });
            }

            catch(Exception e){

            }
        }


        if(resetDataButton == null){ //check that reset button has initialized
            try {
                resetDataButton= (Button) this.getRootView().findViewById(R.id.resetDataButton);
                resetDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!sortingManager.getMergeSorter().isSorting) {
                            sortingManager.getDataSet().clear();
                            sortingManager.seedData(screenSize, seedInterval.getProgress()*100 + 5);
                        }

                    }
                });
            }

            catch(Exception e){

            }
        }

        if(seedInterval == null){ //check that reset button has initialized
            try {
                seedInterval= (SeekBar) this.getRootView().findViewById(R.id.seedInterval);
                seedInterval.setMax(100);

             }

            catch(Exception e){

            }
        }

    }

    @Override
    public void addCallback(Callback callback) {

    }

    @Override
    public void removeCallback(Callback callback) {

    }

    @Override
    public boolean isCreating() {
        return false;
    }

    @Override
    public void setType(int type) {

    }

    @Override
    public void setFixedSize(int width, int height) {

    }

    @Override
    public void setSizeFromLayout() {

    }

    @Override
    public void setFormat(int format) {

    }

    @Override
    public void setKeepScreenOn(boolean screenOn) {

    }

    @Override
    public Canvas lockCanvas() {
        return null;
    }

    @Override
    public Canvas lockCanvas(Rect dirty) {
        return null;
    }

    @Override
    public void unlockCanvasAndPost(Canvas canvas) {

    }

    @Override
    public Rect getSurfaceFrame() {
        return null;
    }

    @Override
    public Surface getSurface() {
        return null;
    }
}

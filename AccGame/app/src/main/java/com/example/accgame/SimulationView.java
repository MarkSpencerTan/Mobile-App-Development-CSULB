package com.example.accgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Mark on 10/15/2016.
 */

public class SimulationView extends View implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Display mDisplay;

    private Bitmap mField;
    private Bitmap mBasket;
    private Bitmap mBitmap;
    private static final int BALL_SIZE = 150;
    private static final int BASKET_SIZE = 300;

    private float mHorizontalBound;
    private float mVerticalBound;
    private float mXOrigin;
    private float mYOrigin;

    // ball position;
    private float mXBall;
    private float mYBall;
    private float mZBall;
    private long timestamp;

    // ball particle
    Particle mBall = new Particle();

    public SimulationView(Context context){
        super(context);

        // Initialize sensor manager
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                mSensorManager.SENSOR_DELAY_NORMAL);
    }
    public SimulationView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        mBitmap = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, true);
        Bitmap basket = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        mBasket = Bitmap.createScaledBitmap(basket, BASKET_SIZE, BASKET_SIZE, true);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mField = BitmapFactory.decodeResource(getResources(), R.drawable.field, opts);

        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        // Calculate ActionBar height to subtract to Vertical Bound
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }

        mHorizontalBound = mDisplay.getWidth();
        mVerticalBound = mDisplay.getHeight()-actionBarHeight*2;
        mXOrigin = mHorizontalBound/2;
        mYOrigin = mVerticalBound/4;

        // Initialize sensor manager
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                mSensorManager.SENSOR_DELAY_NORMAL);
    }

    public void startSimulation(){
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                mSensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopSimulation(){
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if(mDisplay.getRotation() == Surface.ROTATION_90){
                mXBall = -event.values[1];
                mYBall = event.values[0];
            }
            else{
                mXBall = event.values[0];
                mYBall = event.values[1];
            }
            mZBall = event.values[2];
            timestamp = event.timestamp;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        mBitmap = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE, true);
        Bitmap basket = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        mBasket = Bitmap.createScaledBitmap(basket, BASKET_SIZE, BASKET_SIZE, true);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        mField = BitmapFactory.decodeResource(getResources(), R.drawable.field, opts);

        WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(mField, 0,0,null);
        canvas.drawBitmap(mBasket, mXOrigin - BASKET_SIZE/2, mYOrigin-BASKET_SIZE/2, null);

        mBall.updatePosition(mXBall, mYBall, mZBall, timestamp);
        mBall.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);

        Log.d("Posx", Float.toString(mBall.mPosX));
        Log.d("Poxy", Float.toString(mBall.mPosY));
        Log.d("Bound x", Float.toString(mHorizontalBound));
        Log.d("Bound y", Float.toString(mVerticalBound));

        canvas.drawBitmap(mBitmap,
                 mBall.mPosX- BALL_SIZE/2,
                 mBall.mPosY-BALL_SIZE/2, null);
        invalidate();
    }
}

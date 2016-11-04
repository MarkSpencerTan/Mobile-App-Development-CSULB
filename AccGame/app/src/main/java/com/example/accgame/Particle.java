package com.example.accgame;

import android.util.Log;

/**
 * Created by Mark on 10/15/2016.
 */

public class Particle {
    // coefficient of restitution
    private static final float COR = .7f;

    public float mPosX = 0;
    public float mPosY = 0;
    public float mVelX = 0;
    public float mVelY = 0;

    // use acceleration values to calculate displacement of the particle along the x and y axis
    public void updatePosition(float sx, float sy, float sz, long timestamp){
        float dt = (System.nanoTime() - timestamp) / 100000000000000.0f;

        mVelX += -sx * dt/8;
        mVelY += sy * dt/4;

        mPosX += mVelX * dt;
        mPosY += mVelY * dt;

        Log.d("dt",Float.toString(dt));
    }

    // add logic to create a bounce effect when it collides with the boundary.
    public void resolveCollisionWithBounds(float mHorizontalBound, float mVerticalBound){
        if(mPosX > mHorizontalBound) {
            mPosX = mHorizontalBound;
            mVelX = -mVelX * COR;
        }
        else if( mPosX < 0){
            mPosX = 0;
            mVelX = -mVelX * COR;
        }
        if(mPosY > mVerticalBound) {
            mPosY = mVerticalBound;
            mVelY = -mVelY * COR;
        }
        else if( mPosY < 0){
            mPosY = 0;
            mVelY = -mVelY * COR;
        }

    }

}

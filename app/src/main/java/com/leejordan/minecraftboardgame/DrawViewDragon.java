package com.leejordan.minecraftboardgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawViewDragon extends View {

    private DragonActivity game;
    private Dragon dragon;
    private Bitmap upLeft, upRight, downLeft, downRight;
    private int taps;
    private int speed = 20;
    static boolean playable;

    public DrawViewDragon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        playable = true;
        taps = 0;

        game = (DragonActivity) getContext();
        upLeft = BitmapFactory.decodeResource(getResources(), R.drawable.dragon_up_left);
        upRight = BitmapFactory.decodeResource(getResources(), R.drawable.dragon_up_right);
        downLeft = BitmapFactory.decodeResource(getResources(), R.drawable.dragon_down_left);
        downRight = BitmapFactory.decodeResource(getResources(), R.drawable.dragon_down_right);

        Bitmap[] dragonStates ={upLeft, upRight, downLeft, downRight};


        float dragonWidth = 0.34f * getWidth();
        float dragonHeight = 0.27f * getWidth();

        float randLeft = (float)((Math.random() * 0.6) + 0);
        float randTop = (float)((Math.random() * 0.8) + 0.5);

        int theDX;
        int theDY;
        int random = (int) ((Math.random() * (5 - 1)) + 1);
        if (random == 1){
            theDX = speed;
            theDY = speed;
        }
        else if (random == 2)
        {
            theDX = speed * -1;
            theDY = speed * -1;
        }
        else if(random == 3) {
            theDX = speed* 1;
            theDY = speed* -1;
        }
        else {
            theDX = speed * -1;
            theDY = speed* 1;
        }

        dragon = new Dragon(getWidth() * randLeft, getWidth() * randTop, dragonWidth, dragonHeight, theDX, theDY);
        dragon.setStates(dragonStates);
        if(theDX > 0 && theDY > 0) {
            //down, right
            dragon.setBitmap(downRight);
        }
        else if (theDX > 0 && theDY < 0){
            //up, right
            dragon.setBitmap(upRight);
        }
        else if (theDX < 0 && theDY < 0){
            //up, left
            dragon.setBitmap(upLeft);
        }
        else if (theDX < 0 && theDY > 0){
            //down, left
            dragon.setBitmap(downLeft);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        dragon.setdX(speed);
        dragon.setdY(speed);
        dragon.update(canvas);
        dragon.draw(canvas);

        invalidate();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(dragon.contains(event.getX(), event.getY())){
                if(playable)
                    taps++;
            }}
        return true;
    }

    public void setTaps(int taps) {
        this.taps = taps;
    }

    public int getTaps() {
        return taps;
    }
}

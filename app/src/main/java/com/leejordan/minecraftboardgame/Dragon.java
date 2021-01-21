package com.leejordan.minecraftboardgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Dragon extends RectF {
    private int dX, dY;
    private Bitmap bitmap;
    private Bitmap[] states;
    private int hearts;

    private static final int SPEED = 6;

    public Dragon(float left, float top, float width, float length, int dX, int dY){
        super(left, top, left + width, top + length);
        this.dX = dX;
        this.dY = dY;
        states = new Bitmap[4];
    }

    public void draw(Canvas canvas){
            if (bitmap == null){
                Paint p = new Paint();
                p.setColor(Color.RED);
                canvas.drawCircle(500, 500, 30, p);
            }
            else {
                canvas.drawBitmap(bitmap, null, this, null);
            }
    }

    public void update(Canvas canvas){
            if (((left + dX) < 0) ||((right + dX) > canvas.getWidth())   ) //if hits left
            {
                dX *= -1;
            }
            if (((top + dY) < (canvas.getHeight() * 0.1f)) || ((bottom + dY) > canvas.getHeight()) ) //if hits top
            {
                dY *= -1;
            }
            offset(dX, dY);

            if(dX > 0 && dY > 0) {
                //down, right
                setBitmap(states[3]);
            }
            else if (dX > 0 && dY < 0){
                //up, right
                setBitmap(states[1]);
            }
            else if (dX < 0 && dY < 0){
                //up, left
                setBitmap(states[0]);
            }
            else if (dX < 0 && dY > 0){
                //down, left
                setBitmap(states[2]);
            }

    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setdX(int dX) {
        if (this.dX < 0){
            this.dX = dX * -1;
        }
        else{
            this.dX = dX;
        }
    }

    public int getdX() {
        return dX;
    }

    public void setdY(int dY) {
        if (this.dY < 0){
            this.dY = dY * -1;
        }
        else{
            this.dY = dY;
        }
    }

    public int getdY() {
        return dY;
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public void setStates(Bitmap[] states) {
        this.states = states;
    }

    public Bitmap[] getStates() {
        return states;
    }
}

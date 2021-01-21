package com.leejordan.minecraftboardgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

public class Dice extends RectF {
    private Bitmap bitmap;

    public Dice(float left, float top, float l){
        super(left, top, left + l, top + l);
    }

    public void draw(Canvas canvas){
        if (bitmap == null){
            Paint p = new Paint();
            p.setColor(Color.RED);
            canvas.drawCircle(500, 500, 30, p);
        }
        else{
            canvas.drawBitmap(bitmap, null, this,null );
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}

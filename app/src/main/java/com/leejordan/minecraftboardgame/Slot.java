package com.leejordan.minecraftboardgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

public class Slot extends RectF {
    private Bitmap bitmap;
    private int quantity;
    private String type;
    private int slotID;
    public Slot(float left, float top, float l, int i){
        super(left, top, left + l, top + l);
        quantity = 0;
        slotID = i;
    }

    public void draw(Canvas canvas){
        if (bitmap != null){
            canvas.drawBitmap(bitmap, null, this,null );
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }
}

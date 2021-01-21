package com.leejordan.minecraftboardgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

public class Block extends RectF {
    private Bitmap bitmap;
    private ArrayList<Bitmap> bitmapStages;
    private int blockID;
    private boolean mined;
    private int stage;

    public Block(float left, float top, float l, int i){
        super(left, top, left + l, top + l);
        blockID = i;
        mined = false;
        stage = 0;
    }

    public void draw(Canvas canvas){
        if (bitmap != null){
            canvas.drawBitmap(bitmap, null, this,null );
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public ArrayList<Bitmap> getBitmapStages() {
        return bitmapStages;
    }

    public void setBitmapStages(ArrayList<Bitmap> bitmapStages) {
        this.bitmapStages = bitmapStages;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}

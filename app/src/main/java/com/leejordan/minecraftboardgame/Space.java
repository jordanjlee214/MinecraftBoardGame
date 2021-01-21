package com.leejordan.minecraftboardgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Space extends RectF {
    private Bitmap bitmap;
    private String type;
    private boolean visited;
    private int spaceChange;
    private int id;

    public Space(float left, float top, float length, int s, String t, int i) {
        super(left, top, left + length, top + length);
        type = t;
        spaceChange = s;
        visited = false;
        id = i;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getSpaceChange() {
        return spaceChange;
    }

    public String getType() {
        return type;
    }

    public void setSpaceChange(int spaceChange) {
        this.spaceChange = spaceChange;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }




}

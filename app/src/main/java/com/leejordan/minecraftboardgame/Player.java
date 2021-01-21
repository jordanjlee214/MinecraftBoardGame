package com.leejordan.minecraftboardgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Player extends RectF {
    private int currentSpaceID;
    private Space currentSpace;
    private Bitmap bitmap;
    private int playerID;
    private float gap;
    private float length;
    private int moves;

    public Player(float left, float top, float length, Bitmap type, int p ){
        super(left, top, left + length, top + length);
        bitmap = type;
        playerID = p;
        currentSpaceID = 1;
    }

    public Player(RectF f,  float g, float l,Bitmap type, int p, int s){
        super(f.left + g, f.top + g, f.left + g + l,  f.top + g + l);
        length = l;
        gap = g;
        bitmap = type;
        playerID = p;
        currentSpaceID = s;
    }

    public void draw(Canvas canvas)
    {
        if (bitmap == null){
            Paint p = new Paint();
            p.setColor(Color.RED);
            canvas.drawCircle(500, 500, 30, p);
        }
        else{
            canvas.drawBitmap(bitmap, null, this,null );
        }
    }

    public void moveTo(Space s){
        currentSpace = s;
        currentSpaceID = s.getId();
        left = s.left + gap;
        top = s.top + gap;
        right = left + length;
        bottom = top + length;
    }


    public void moveTo(int id){
        currentSpace = DrawView.idToSpace.get(id);
        currentSpaceID = id;
        Space s = DrawView.idToSpace.get(id);
        left = s.left + gap;
        top = s.top + gap;
        right = left + length;
        bottom = top + length;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getCurrentSpaceID() {
        return currentSpaceID;
    }

    public void setCurrentSpaceID(int currentSpaceID) {
        this.currentSpaceID = currentSpaceID;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}

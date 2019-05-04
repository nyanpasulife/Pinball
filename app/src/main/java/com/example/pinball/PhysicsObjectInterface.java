package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;



public interface PhysicsObjectInterface {
    boolean collisionCheck(PhysicsObjectInterface x);
    void beCollided(PhysicsObjectInterface x);
    void collisionAct(Vector2D collisionPoint, Vector2D force);
    void gravitationAct(Vector2D gravity);
    void paint(Canvas c,double widthRate,double heightRate);
    void convertBitmap(double widthRate, double heightRate);
    Bitmap getBitmap();
    void setBitmap(Bitmap bitmap);


}

package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;



public interface PhysicsObjectInterface {
    void collisionCheck(PhysicsObjectInterface other);
    void addGravitation(Vector2D gravity);
    void act();
    void paint(Canvas c,double widthRate,double heightRate);
    void convertBitmap(double widthRate, double heightRate);
    Bitmap getBitmap();
    void setBitmap(Bitmap bitmap);
    double getRadius();
    Vector2D getMaterialPoint();
}

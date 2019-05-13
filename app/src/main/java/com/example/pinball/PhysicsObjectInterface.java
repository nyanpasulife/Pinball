package com.example.pinball;

import android.graphics.Canvas;



public interface PhysicsObjectInterface {
    boolean collisionCheck(PhysicsObject other);
    void addGravitation(Vector2D gravity);
    void act();
    void paint(Canvas c,double widthRate,double heightRate);
    double getRadius();
    Vector2D getMaterialPoint();
}

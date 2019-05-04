package com.example.pinball;

import android.graphics.Canvas;



public interface PhysicsObjectInterface {
    boolean collisionCheck(PhysicsObjectInterface x);
    void beCollided(PhysicsObjectInterface x);
    void act(Vector2D collisionPoint, Vector2D force);
    void paint(Canvas c);


}

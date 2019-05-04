package com.example.pinball;

import android.graphics.Canvas;



public interface PhysicsObjectInterface {
    boolean collisionCheck(PhysicsObjectInterface x);
    void beCollided(PhysicsObjectInterface x);
    void collisionAct(Vector2D collisionPoint, Vector2D force);
    void gravitationAct(Vector2D gravity);
    void paint(Canvas c);


}

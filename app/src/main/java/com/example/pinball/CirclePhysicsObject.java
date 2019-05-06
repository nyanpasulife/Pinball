package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CyclePhysicsObject implements PhysicsObjectInterface{

    boolean mavingObject = false;
    Vector2D matarealPoint;
    double range;
    Bitmap image;

    @Override
    public boolean collisionCheck(PhysicsObjectInterface x) {
        return false;
    }

    @Override
    public void beCollided(PhysicsObjectInterface x) {

    }

    @Override
    public void collisionAct(Vector2D collisionPoint, Vector2D force) {

    }

    @Override
    public void gravitationAct(Vector2D gravity) {

    }

    @Override
    public void paint(Canvas c) {

    }
}

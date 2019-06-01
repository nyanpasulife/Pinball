package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class AccelerationCircle extends CirclePhysicsObject {
    public AccelerationCircle(Vector2D position, int r, Bitmap bitmap) {
        super(position, r, bitmap);
    }
    public AccelerationCircle(Vector2D position, int r, Bitmap bitmap, boolean move) {
        super(position, r, bitmap, move);
    }
}

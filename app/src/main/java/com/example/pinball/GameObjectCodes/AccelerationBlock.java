package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class AccelerationBlock extends RectanglePhysicsObject {
    public AccelerationBlock(Vector2D position, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap);
    }
}

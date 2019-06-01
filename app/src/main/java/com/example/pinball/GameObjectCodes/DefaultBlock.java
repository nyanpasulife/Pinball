package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class DefaultBlock extends RectanglePhysicsObject {
    public DefaultBlock(Vector2D position, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap);
    }
    public DefaultBlock(Vector2D position, double width, double height, Bitmap bitmap, boolean move) {
        super(position, width, height, bitmap, move);
    }
}

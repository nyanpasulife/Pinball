package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class WarriorBlock extends RectanglePhysicsObject {
    public WarriorBlock(Vector2D position, double width, double height, Bitmap bitmap, boolean move) {
        super(position, width, height, bitmap, move);
    }
}

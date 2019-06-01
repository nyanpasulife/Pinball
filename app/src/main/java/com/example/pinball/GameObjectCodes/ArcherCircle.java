package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class ArcherCircle extends CirclePhysicsObject {
    public ArcherCircle(Vector2D position, int r, Bitmap bitmap) {
        super(position, r, bitmap);
    }
}

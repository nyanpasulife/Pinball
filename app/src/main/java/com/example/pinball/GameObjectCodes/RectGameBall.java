package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class RectGameBall extends RectanglePhysicsObject implements GameBall {
    RectGameBall(Vector2D position, Bitmap bitmap){
        this(position,25,bitmap);
    }
    RectGameBall(Vector2D position, double sqSide, Bitmap bitmap){
        super(position,sqSide,sqSide,bitmap);
    }
}

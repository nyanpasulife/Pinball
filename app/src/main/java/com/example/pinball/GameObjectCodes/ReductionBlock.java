package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class ReductionBlock extends RectanglePhysicsObject {
    /*public ReductionBlock(Vector2D position, double width, double height, Bitmap bitmap, boolean move) {
        super(position, width, height, bitmap, move);
    }*/
    public ReductionBlock(Vector2D position, double width, double height, int id, Resources resources, boolean move) {
        super(position, width, height, id, resources, move);
    }

    public void setRotation(double r){
        super.setRotation(r);
    }
}

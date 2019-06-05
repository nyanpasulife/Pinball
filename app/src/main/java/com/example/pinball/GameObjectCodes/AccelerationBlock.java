package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class AccelerationBlock extends RectanglePhysicsObject {
    /*public AccelerationBlock(Vector2D position, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap);
    }
    public AccelerationBlock(Vector2D position, double width, double height, Bitmap bitmap, boolean move) {
        super(position, width, height, bitmap, move);
    }*/

    public AccelerationBlock(Vector2D position, double width, double height, int id, Resources rsc) {
        super(position, width, height, id, rsc);
    }
    public AccelerationBlock(Vector2D position, double width, double height, int id, Resources rsc, boolean move) {
        super(position, width, height, id, rsc, move);
    }
    public void setRotation(double r){
        super.setRotation(r);
    }
}

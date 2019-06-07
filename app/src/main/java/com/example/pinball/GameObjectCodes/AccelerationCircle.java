package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.PhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class AccelerationCircle extends CirclePhysicsObject {
    /*public AccelerationCircle(Vector2D position, int r, Bitmap bitmap) {
        super(position, r, bitmap);
    }
    public AccelerationCircle(Vector2D position, int r, Bitmap bitmap, boolean move) {
        super(position, r, bitmap, move);
    }*/

    public AccelerationCircle(Vector2D position, int r, int id, Resources resources) {
        super(position, r, id, resources);
    }
    public AccelerationCircle(Vector2D position, int r, int id, Resources resources, boolean move) {
        super(position, r, id, resources, move);
    }

//    public void gameCollided(PhysicsObject other){
//        super.gameCollided(other);
//        other.getVelocity().reSize(getVelocity().getSize() * 2);
//    }

}

package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.PhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class ReductionCircle extends CirclePhysicsObject {
    /*public ReductionCircle(Vector2D position, int r, Bitmap bitmap) {
        super(position, r, bitmap);
    }
    public ReductionCircle(Vector2D position, int r, Bitmap bitmap, boolean move) {
        super(position, r, bitmap, move);
    }*/
    public ReductionCircle(Vector2D position, int r, int id, Resources resources) {
        super(position, r, id, resources);
    }
    public ReductionCircle(Vector2D position, int r, int id, Resources resources, boolean move) {
        super(position, r, id, resources, move);
    }

//    public void gameCollided(PhysicsObject other){
//        super.gameCollided(other);
//        other.getVelocity().reSize(-10);
//        Log.d("d", other.getClass().getName());
//    }
}

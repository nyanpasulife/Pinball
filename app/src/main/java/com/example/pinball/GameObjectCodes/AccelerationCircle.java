package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.Physics.CirclePhysicsObject;
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

    public void addImpulseAndFriction(double impulse, Vector2D n, Vector2D normalAngularA, double frictionForceScalar) {
        Vector2D impulseVector = n.constantProduct(impulse * getInverseOfMass());
        setVelocity(getVelocity().minus(impulseVector));
    }
}

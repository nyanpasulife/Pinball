package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.PhysicsObject;
import com.example.pinball.RectanglePhysicsObject;
import com.example.pinball.Vector2D;

public class Flipper extends RectanglePhysicsObject {
    final static int COUNTER_CLOCK = 0;
    final static int CLOCK = 1;
    final static double POWER = 20000;

    final static int ROTATE_SPEED = 2;
    final static int ROTATE_ANGLE = 60;

    boolean PoweredBool = false;
    boolean Falling = false;

    int PivotStatus;

    public Flipper(Vector2D position, int pivotDirection, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap, false);
        PivotStatus = pivotDirection;

    }

    public void powered() {
        if (Falling == false) {
            PoweredBool = true;
        }
    }

    @Override
    public void everyTick() {
        rotateWhenPowered();
    }

    private void rotateWhenPowered() {
        if (PoweredBool) {
            double nowR = getRotation();
            if (nowR > ROTATE_ANGLE || nowR < -ROTATE_ANGLE) {
                PoweredBool = false;
                Falling = true;
                return;
            }
            if (PivotStatus == 0) {
                nowR -= ROTATE_SPEED * 3;
            } else if (PivotStatus == 1) {
                nowR += ROTATE_SPEED * 3;
            }
            setRotation(nowR);
        }
        if(Falling) {
            double nowR = getRotation();
            if (PivotStatus == 0 && nowR > 0) {
                Falling = false;
                nowR = 0;
                return;
            }
            if (PivotStatus == 1 && (nowR < 0)) {
                Falling = false;
                nowR = 0;
                return;
            }
            if (PivotStatus == 0) {
                nowR += ROTATE_SPEED;
            } else { //PivotStatus ==1;
                nowR -= ROTATE_SPEED;
            }
            setRotation(nowR);
        }
    }

    @Override
    public void gameCollided(PhysicsObject other) {
        if(PoweredBool){
            double angle = getRotation();
            Vector2D n = new Vector2D(0,-1).rotate(angle);
            Vector2D powerVector = n.constantProduct(POWER).constantProduct(other.getInverseOfMass());
            Vector2D newVelocity = other.getVelocity().plus(powerVector);
            other.setVelocity(newVelocity);
            int haha=1;
        }

    }
}

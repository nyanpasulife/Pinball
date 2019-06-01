package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Physics.PhysicsObject;
import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class Flipper extends RectanglePhysicsObject {
    final static int COUNTER_CLOCK = 0;
    final static int CLOCK = 1;
    final static double POWER = 20000;
    final static int ROTATE_SPEED = 2;
    final static int ROTATE_ANGLE = 60;
    final static double PivotDistanceRate = 0.8;


    boolean PoweredBool = false;
    boolean Falling = false;

    int PivotStatus; //0 반시계 회전 (회전 음수변화) , 1 시계회전 (회전 양수변화)
    Vector2D PivotPoint; //회전 점
    Vector2D OriginMPoint; //생성될때 맨 처음 위치했던 중심점.

    public Flipper(Vector2D position, int pivotStatus, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap, false);
        PivotStatus = pivotStatus;
        double pMove; //중앙점에서 피벗까지의 이동 x량
        if(PivotStatus ==0){
            pMove = - width/2*PivotDistanceRate;
        }
        else { //PivotStatus ==1
            pMove =  width/2 *PivotDistanceRate;
        }
        PivotPoint = getMaterialPoint().plus(new Vector2D(pMove,0));
        OriginMPoint = getMaterialPoint();
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
            rotateByPivot(nowR, PivotPoint);
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
            rotateByPivot(nowR, PivotPoint);
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
        }

    }
    void rotateByPivot(double angle, Vector2D pivot){
        setRotation(angle);
        Vector2D newM = getOriginMPoint().rotateDotByPivot(angle, pivot);
        setMaterialPoint(newM);
    }

    public Vector2D getOriginMPoint() {
        return OriginMPoint;
    }
}

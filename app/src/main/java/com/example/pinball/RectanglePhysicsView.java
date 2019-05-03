package com.example.pinball;

import android.view.SurfaceView;

public class RectanglePhysicsView extends SurfaceView implements PhysicsEngine.PhysicsInterface {
    double PointX = 0;
    double PointY = 0;

    double Vectorhalfwidth = 0;
    double Vectorhalf]

    RectanglePhysicsView

    public boolean collisionCheck(PhysicsEngine.PhysicsInterface other){
        if(other instanceof CirclePhysicsView){
        }
        else if (other instanceof RectanglePhysicsView){

        }

        return false;

    }
    public void beCollided(PhysicsEngine.PhysicsInterface other){

    }
    public void act(double px, double py, PhysicsEngine.Vector vector){

    }
}

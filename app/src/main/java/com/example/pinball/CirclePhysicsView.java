package com.example.pinball;

import android.content.Context;
import android.view.SurfaceView;

public class CirclePhysicsView extends SurfaceView implements PhysicsEngine.PhysicsInterface {

    double posX = 0, posY = 0;
    double r = 0;
    double speed = 0;
    double rotate = 0;

    public CirclePhysicsView(Context context) {
        super(context);
    }

    @Override
    public boolean collisionCheck(PhysicsEngine.PhysicsInterface other) {
        if(other instanceof CirclePhysicsView){
            if(Math.sqrt(Math.pow(this.posX - ((CirclePhysicsView) other).posX, 2) + Math.pow(this.posY - ((CirclePhysicsView) other).posY. 2))){ //거리 <= 반지름의 합

            }
        }
        else if(other instanceof RactanglePhysicsView){

        }
        return false;
    }

    //충돌점을 받아서 움직이는 함수
    @Override
    public void beCollided(PhysicsEngine.PhysicsInterface other) {
        act(posX, posY);
    }

    @Override
    public void act(double px, double py) {

    }
}

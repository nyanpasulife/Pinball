package com.example.pinball;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class PolygonPhysicsView  implements PhysicsEngine.PhysicsInterface {
    Vector MaterialPoint = new Vector(0,0); //물체의 무게중심(질점)
    ArrayList<Vector> VertexPoints = new ArrayList<>(); // 중심점을 기준으로 꼭지점까지의 벡터들 ,p0 ~ pn-1 , 반드시 예각이 없는 다면체여야함.
    ArrayList<Vector> PerpendicularsOfSides = new ArrayList<>(); //각 변과 수직인 법선벡터들, p1 ~ pn

    public void surfaceCreated(SurfaceHolder holder){

    }
    public void surfaceDestroyed(SurfaceHolder holder){

    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    public boolean collisionCheck(PhysicsEngine.PhysicsInterface other){

        if(other instanceof PolygonPhysicsView){

        }
        //else if (other instanceof RectanglePhysicsView){

       // }

        return false;

    }
    public void beCollided(PhysicsEngine.PhysicsInterface other){

    }
    public void act(Vector collisionPoint, Vector power){

    }
}

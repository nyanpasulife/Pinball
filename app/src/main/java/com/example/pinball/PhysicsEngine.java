package com.example.pinball;

import android.util.Log;
import android.view.SurfaceView;

public class PhysicsEngine extends Thread{
    public PhysicsEngine(){

    }

    @Override
    public void run() {
        while(true){
            Log.d("tag", "running");
        }
    }

    // 두 오브젝트가 충돌하면 두 오브젝트의 행동함수를 호출되는 함수
    private void isCollision(PhysicsEngine.PhysicsInterface a, PhysicsEngine.PhysicsInterface b){
        a.beCollided(b);
    }

    private void collisionCheck(PhysicsInterface a, PhysicsInterface b){
        a.collisionCheck(b);
    }
/*
    public PhysicsInterface getView(){
        return ;
    }
*/
    public interface PhysicsInterface{
        boolean collisionCheck(PhysicsInterface other);
        void beCollided(PhysicsInterface other);
        void act(double px, double py);
    }

    public class Grid{

    }
}
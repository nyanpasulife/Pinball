package com.example.pinball;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class PhysicsEngine extends Thread{




    //    @Override
    //    public void run() {
    //        while(true){
    //            Log.d("tag", "running");
    //        }
    //    }
    //
    //    // 두 오브젝트가 충돌하면 두 오브젝트의 행동함수를 호출되는 함수

    private void collisionCheck(PhysicsInterface a, PhysicsInterface b){
        a.collisionCheck(b);
    }


    private void isCollision(PhysicsEngine.PhysicsInterface a, PhysicsEngine.PhysicsInterface b){
        a.beCollided(b);
    }
/*
    public PhysicsInterface getView(){
        return ;
    }
*/


    public interface PhysicsInterface{
        boolean collisionCheck(PhysicsInterface x);
        void beCollided(PhysicsInterface x);
        void act(Vector collisionPoint, Vector force);


    }


    public class Grid{

    }


}
package com.example.pinball;

public class PhysicsEngine extends Thread{




    //    @Override
    //    public void run() {
    //        while(true){
    //            Log.d("tag", "running");
    //        }
    //    }
    //
    //    // 두 오브젝트가 충돌하면 두 오브젝트의 행동함수를 호출되는 함수

    private void collisionCheck(PhysicsObjectInterface a, PhysicsObjectInterface b){
        a.collisionCheck(b);
    }


    private void isCollision(PhysicsObjectInterface a, PhysicsObjectInterface b){
        a.beCollided(b);
    }
/*
    public PhysicsInterface getView(){
        return ;
    }
*/


    public class Grid{

    }


}
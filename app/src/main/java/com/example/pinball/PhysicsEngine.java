package com.example.pinball;

public class PhysicsEngine extends Thread{
    public PhysicsEngine(){

    }

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
        void act(double px, double py);


    }

    public class Vector{
        double X; double Y;
        Vector(double x, double y){X =x, Y = y}
        public Vector plus(Vector a, Vector b){
            return new Vector(a.X+b.X, a.Y+b.Y);
        }

    }

    public class Grid{

    }


}
package com.example.pinball;

import java.util.ArrayList;

public class PhysicsEngine extends Thread{
    boolean Run = false;
    static Vector2D gravity = new Vector2D(0,0);
    private ArrayList<PhysicsObjectInterface> GameObjectsList = new ArrayList<>();

    PhysicsEngine(ArrayList<PhysicsObjectInterface> pack){
        GameObjectsList = pack;
    }

    @Override
    public void run() {
        while(Run){
            for(PhysicsObjectInterface e :GameObjectsList){
                e.gravitationAct(gravity);
            }
        }
    }

        // 두 오브젝트가 충돌하면 두 오브젝트의 행동함수를 호출하는 함수

    private void collisionCheck(PhysicsObjectInterface a, PhysicsObjectInterface b){
        a.collisionCheck(b);
    }


    private void isCollision(PhysicsObjectInterface a, PhysicsObjectInterface b){
        a.beCollided(b);
    }

    //쓰레드가 작동하는지에 관한 논리값을 입력받는다.
    public void setRunning(boolean b){
        Run = b;
    }

    public class Grid{

    }
    public void setGameObjectsList(ArrayList<PhysicsObjectInterface> list){
        GameObjectsList = list;
    }


}
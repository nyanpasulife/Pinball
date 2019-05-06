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
                for(PhysicsObjectInterface other : GameObjectsList){ //그리드가 추가되면 수정되어야하는 부분. 현재는 모든 오브젝트에 대해 충돌 검사를 하도록 작성되어있음.
                    if(e != other){
                        e.collisionCheck(other);
                    }
                }

                e.addGravitation(gravity);
                e.act();
            }

            try {
                this.wait(10);
            }catch (Exception e){}
        }
    }

    // 두 오브젝트가 충돌하면 두 오브젝트의 행동함수를 호출하는 함수


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
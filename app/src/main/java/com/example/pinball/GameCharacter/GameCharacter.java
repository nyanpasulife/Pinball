package com.example.pinball.GameCharacter;

import android.content.res.Resources;

import com.example.pinball.Physics.PhysicsObject;
import com.example.pinball.Physics.PhysicsView;

import java.util.ArrayList;

/** 캐릭터가 공통으로 갖는 속성 정의 **/

public abstract class GameCharacter {

    private int life;

    ArrayList<PhysicsObject> GameObjectList = new ArrayList<>();
    Resources MResource;

    GameCharacter(Resources res){
        MResource = res;
    }

    public void skill(){}
    public int getLife(){
        return life;
    }

    public void setCharOnView(int position, PhysicsView view){
        view.pushObjects(GameObjectList);
    }
}

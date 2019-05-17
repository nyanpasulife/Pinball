package com.example.pinball.GameCharacter;

import android.content.res.Resources;

import com.example.pinball.Physics.PhysicsObject;
import com.example.pinball.Physics.PhysicsView;

import java.util.ArrayList;

public class GameCharacter {
    ArrayList<PhysicsObject> GameObjectList = new ArrayList<>();
    Resources MResource;

    GameCharacter(Resources res){
        MResource = res;
    }

    public void setCharOnView(int position, PhysicsView view){
        view.pushObjects(GameObjectList);
    }


}

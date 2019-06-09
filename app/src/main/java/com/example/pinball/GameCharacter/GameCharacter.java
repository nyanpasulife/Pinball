

package com.example.pinball.GameCharacter;



import android.content.res.Resources;


import com.example.pinball.Engines.PhysicsObject;
import com.example.pinball.Engines.PhysicsView;


import java.util.ArrayList;



abstract public class GameCharacter {

    ArrayList<PhysicsObject> GameObjectList = new ArrayList<>();

    Resources MResource;



    GameCharacter(Resources res){
        MResource = res;

    }



    public void setCharOnView(int position, PhysicsView view){

        view.pushObjects(GameObjectList);

    }


    abstract public void charAct(String actCall);
}
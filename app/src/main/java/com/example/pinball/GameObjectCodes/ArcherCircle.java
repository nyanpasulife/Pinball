package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.GameCharacter.CharacterArcher;
import com.example.pinball.GameCharacter.GameCharacter;
import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class ArcherCircle extends CirclePhysicsObject implements CharacterObj{

    CharacterArcher user;

    /*public ArcherCircle(Vector2D position, int r, Bitmap bitmap) {
        super(position, r, bitmap);
    }*/

    public ArcherCircle(Vector2D position, int r, int id, Resources resources) {
        super(position, r, id, resources);
    }

    public void act (){
        super.act();
        skill();
    }

    public void skill(){
        //get other obj
        //make obj velocity = 0
        //flipper 비활성화
        //
    }

    @Override
    public void setUser(GameCharacter u) {
        user = (CharacterArcher) u;
    }
}

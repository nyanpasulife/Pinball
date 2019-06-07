package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.GameCharacter.CharacterWarrior;
import com.example.pinball.GameCharacter.GameCharacter;
import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class WarriorBlock extends RectanglePhysicsObject implements CharacterObj{

    /*public WarriorBlock(Vector2D position, double width, double height, Bitmap bitmap, boolean move) {
        super(position, width, height, bitmap, move);
    }*/
    private CharacterWarrior user;
    public WarriorBlock(Vector2D position, double width, double height, int id, Resources resources, boolean move) {
        super(position, width, height, id, resources, move);
    }
    public void setRotation(double r){
        super.setRotation(r);
    }

    public void act(){
        super.act();
        skill();
    }
    public void skill(){

    }

    @Override
    public void setUser(GameCharacter u) {
        user = (CharacterWarrior) u;
    }
}

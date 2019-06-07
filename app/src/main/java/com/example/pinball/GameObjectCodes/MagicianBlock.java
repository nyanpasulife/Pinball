package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.GameCharacter.CharacterMagician;
import com.example.pinball.GameCharacter.GameCharacter;
import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class MagicianBlock extends RectanglePhysicsObject implements CharacterObj{

    CharacterMagician user;

   /* public MagicianBlock(Vector2D position, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap);
    }*/
   public MagicianBlock(Vector2D position, double width, double height, int id, Resources resources, boolean move) {
       super(position, width, height, id, resources, move);
   }
   public void setRotation(double r){
       super.setRotation(r);
   }
   public void act(){
       super.act();
   }
   public void skill(){
       //get other obj
       //get position and set new position
   }

    @Override
    public void setUser(GameCharacter u) {
        user = (CharacterMagician) u;
    }
}

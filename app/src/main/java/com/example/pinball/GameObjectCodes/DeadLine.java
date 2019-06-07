package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;

import com.example.pinball.GameCharacter.GameCharacter;
import com.example.pinball.Physics.PhysicsObject;
import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

import java.util.Random;

public class DeadLine extends RectanglePhysicsObject implements CharacterObj{
    GameCharacter user;
    Random random;

    public DeadLine(Vector2D position, double width, double height, int id, Resources resources, boolean move) {
        super(position, width, height, id, resources, move);
    }

    public void gameCollided(PhysicsObject other){
        super.gameCollided(other);
        other.setMaterialPoint(new Vector2D(other.getMaterialPoint().getX(), 2580/2));
        if(other instanceof Ball) {
            user.loseLife();
            if(other.getRadius() < 48){
                ((Ball) other).resize(48);
            }
        }
    }

    @Override
    public void skill() {

    }

    @Override
    public void setUser(GameCharacter u) {
        user = u;
    }
}

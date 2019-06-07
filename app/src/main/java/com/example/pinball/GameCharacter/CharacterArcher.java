package com.example.pinball.GameCharacter;

import android.content.res.Resources;

import com.example.pinball.GameObjectCodes.AccelerationBlock;
import com.example.pinball.GameObjectCodes.AccelerationCircle;
import com.example.pinball.GameObjectCodes.ArcherCircle;
import com.example.pinball.GameObjectCodes.DefaultBlock;
import com.example.pinball.GameObjectCodes.Flipper;
import com.example.pinball.GameObjectCodes.HealerBlock;
import com.example.pinball.GameObjectCodes.ReductionBlock;
import com.example.pinball.GameObjectCodes.ReductionCircle;
import com.example.pinball.GameObjectCodes.RefractionBlock;
import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.Vector2D;
import com.example.pinball.R;

import java.util.Random;

public class CharacterArcher extends GameCharacter {

    private int life = 4;
    private Random xgen = new Random();

    public CharacterArcher(Resources res) {
        super(res);

        int bDefault = R.drawable.block_default;
        int cArcher = R.drawable.circle_archer;
        int bReduction = R.drawable.block_reduction;
        int bAccel = R.drawable.block_acceleration;
        int bRefraction = R.drawable.block_refraction;
        int cReduction = R.drawable.circle_reduction;
        int cAccel = R.drawable.circle_acceleration;
        int cBall = R.drawable.ball;
        int leftFlipper = R.drawable.flipper_left;
        int rightFlipper = R.drawable.flipper_right;

        CirclePhysicsObject ball = new CirclePhysicsObject(new Vector2D(xgen.nextInt(1240)+100,1290), 48, cBall, MResource);

        Flipper flipper1 = new Flipper(new Vector2D(540, 2350), 0 , 250, 75, leftFlipper, MResource);
        Flipper flipper2 = new Flipper(new Vector2D(1440-540, 2350), 1 , 250, 75, rightFlipper, MResource);
        flipper2.setRotation(180);

        DefaultBlock obj1 = new DefaultBlock(new Vector2D(0-50,2400), 800,800, bDefault, MResource, false);
        obj1.setRotation(40);
        DefaultBlock obj2 = new DefaultBlock(new Vector2D(500,1800), 300,75, bDefault, MResource, false);
        DefaultBlock obj3 = new DefaultBlock(new Vector2D(1440-400+50,2235), 400,100, bDefault, MResource, false);
        obj3.setRotation(-40);
        DefaultBlock obj4 = new DefaultBlock(new Vector2D(1480,1985), 200,200, bDefault, MResource, false);
        obj4.setRotation(-40);
        DefaultBlock obj5 = new DefaultBlock(new Vector2D(1480-50,1985 + 800), 800,800, bDefault, MResource, false);
        obj5.setRotation(-40);

//        RefractionBlock obj6 = new RefractionBlock(new Vector2D(150,1400), 300,50,bRefraction, MResource, false);
//        RefractionBlock obj7 = new RefractionBlock(new Vector2D(1440-150,1600), 300,50,bRefraction, MResource, false);

        AccelerationBlock obj8 = new AccelerationBlock(new Vector2D(1000,1850), 350, 75, bAccel, MResource, false);
        AccelerationCircle obj9 = new AccelerationCircle(new Vector2D(700,1950), 50,cAccel, MResource, false);

        ReductionCircle obj11 = new ReductionCircle(new Vector2D(600,1400), 75,cReduction, MResource, false);
        ReductionCircle obj12 = new ReductionCircle(new Vector2D(1440 - 400,1500), 50,cReduction, MResource, false);

        ArcherCircle obj13 = new ArcherCircle(new Vector2D(1200, 2140), 50, cArcher, MResource);

        DefaultBlock outColliderBottom_forTest = new DefaultBlock(new Vector2D(750, 2560+75), 1500, 150, bDefault, MResource, false);
        DefaultBlock outColliderLeft = new DefaultBlock(new Vector2D(0-75, 1700), 150, 1186, bDefault, MResource, false);
        DefaultBlock outColliderRight = new DefaultBlock(new Vector2D(1440+75, 1700), 150, 1186, bDefault, MResource, false);

        GameObjectList.add(ball);
        GameObjectList.add(obj1);
        GameObjectList.add(obj2);
        GameObjectList.add(obj3);
        GameObjectList.add(obj4);
        GameObjectList.add(obj5);
//        GameObjectList.add(obj6);
//        GameObjectList.add(obj7);
        GameObjectList.add(obj8);
        GameObjectList.add(obj9);
        GameObjectList.add(obj11);
        GameObjectList.add(obj12);
        GameObjectList.add(obj13);


        GameObjectList.add(outColliderLeft);
        GameObjectList.add(outColliderRight);
//        GameObjectList.add(outColliderBottom_forTest);

        GameObjectList.add(flipper1);
        GameObjectList.add(flipper2);

        interactWithUser.add(flipper1);
        interactWithUser.add(flipper2);
        interactWithUser.add(obj13);
        interactWithUser.add(null);
    }

    public int getLife(){
        return life;
    }
}

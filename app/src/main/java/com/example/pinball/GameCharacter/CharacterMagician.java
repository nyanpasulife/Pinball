package com.example.pinball.GameCharacter;

import android.content.res.Resources;

import com.example.pinball.GameObjectCodes.AccelerationBlock;
import com.example.pinball.GameObjectCodes.AccelerationCircle;
import com.example.pinball.GameObjectCodes.Ball;
import com.example.pinball.GameObjectCodes.DeadLine;
import com.example.pinball.GameObjectCodes.DefaultBlock;
import com.example.pinball.GameObjectCodes.Flipper;
import com.example.pinball.GameObjectCodes.MagicianBlock;
import com.example.pinball.GameObjectCodes.ReductionCircle;
import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.Vector2D;
import com.example.pinball.R;

import java.util.Random;

public class CharacterMagician extends GameCharacter {

    private int life = 3;
    private Random xgen = new Random();

    public CharacterMagician(Resources res) {
        super(res);

        int bDefault = R.drawable.block_default;
        int bmagician = R.drawable.block_magician;
        int bReduction = R.drawable.block_reduction;
        int bAccel = R.drawable.block_acceleration;
        int cReduction = R.drawable.circle_reduction;
        int cAccel = R.drawable.circle_acceleration;
        int cBall = R.drawable.ball;
        int leftFlipper = R.drawable.flipper_left;
        int rightFlipper = R.drawable.flipper_right;

        Ball ball = new Ball(new Vector2D(xgen.nextInt(1240)+100,1290), 48, cBall, MResource);

        Flipper flipper1 = new Flipper(new Vector2D(540, 2350), 0 , 250, 75, leftFlipper, MResource);
        Flipper flipper2 = new Flipper(new Vector2D(1440-540, 2350), 1 , 250, 75, rightFlipper, MResource);

        DefaultBlock obj1 = new DefaultBlock(new Vector2D(0-50,2400), 800,800, bDefault, MResource, false);
        obj1.setRotation(40);
        DefaultBlock obj2 = new DefaultBlock(new Vector2D(500,1800), 200,75, bDefault, MResource, false);
        obj2.setRotation(-40);
        DefaultBlock obj3 = new DefaultBlock(new Vector2D(1440-400+50,2235), 400,100, bDefault, MResource, false);
        obj3.setRotation(-40);
        DefaultBlock obj4 = new DefaultBlock(new Vector2D(1480,1985), 200,200, bDefault, MResource, false);
        obj4.setRotation(-40);
        DefaultBlock obj5 = new DefaultBlock(new Vector2D(1480-50,1985 + 800), 800,800, bDefault, MResource, false);
        obj5.setRotation(-40);

        AccelerationBlock obj8 = new AccelerationBlock(new Vector2D(1440 - 400,1950), 300, 75, bAccel, MResource, false);
        obj8.setRotation(-40);
        AccelerationCircle obj9 = new AccelerationCircle(new Vector2D(1440 - 400,1600), 50,cAccel, MResource, false);

        ReductionCircle obj11 = new ReductionCircle(new Vector2D(600,1500), 50,cReduction, MResource, false);
        ReductionCircle obj12 = new ReductionCircle(new Vector2D(1440 - 200,1300), 75,cReduction, MResource, false);

        MagicianBlock obj13 = new MagicianBlock(new Vector2D(1440-50, 1500), 300, 75, bmagician, MResource, false);
        obj13.setRotation(40);
        MagicianBlock obj14 = new MagicianBlock(new Vector2D(50, 1500), 300, 75, bmagician, MResource, false);
        obj14.setRotation(-40);

        DeadLine deadLine = new DeadLine(new Vector2D(750, -200), 1500, 150, bDefault, MResource, false);
        DefaultBlock outColliderLeft = new DefaultBlock(new Vector2D(0-75, 1700), 150, 1186, bDefault, MResource, false);
        DefaultBlock outColliderRight = new DefaultBlock(new Vector2D(1440+75, 1700), 150, 1186, bDefault, MResource, false);

        GameObjectList.add(ball);
        GameObjectList.add(obj1);
        GameObjectList.add(obj2);
        GameObjectList.add(obj3);
        GameObjectList.add(obj4);
        GameObjectList.add(obj5);
        GameObjectList.add(obj8);
        GameObjectList.add(obj9);
        GameObjectList.add(obj11);
        GameObjectList.add(obj12);
        GameObjectList.add(obj13);
        GameObjectList.add(obj14);


        GameObjectList.add(outColliderLeft);
        GameObjectList.add(outColliderRight);
        GameObjectList.add(deadLine);

        GameObjectList.add(flipper1);
        GameObjectList.add(flipper2);

        interactWithUser.add(flipper1);
        interactWithUser.add(flipper2);
        interactWithUser.add(obj13);
        interactWithUser.add(obj14);
        interactWithUser.add(deadLine);
    }

    public int getLife(){
        return life;
    }
    public void loseLife(){
        life--;
        playView.updateLife(life, this);
    }
}

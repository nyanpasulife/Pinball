package com.example.pinball.GameCharacter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pinball.GameObjectCodes.AccelerationBlock;
import com.example.pinball.GameObjectCodes.AccelerationCircle;
import com.example.pinball.GameObjectCodes.DefaultBlock;
import com.example.pinball.GameObjectCodes.RefractionBlock;
import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.PolygonPhysicsObject;
import com.example.pinball.Physics.Vector2D;
import com.example.pinball.R;

public class CharacterWarrior extends GameCharacter {

    private int life = 5;

    public CharacterWarrior(Resources res) {
        super(res);

        Bitmap bDefault = BitmapFactory.decodeResource(MResource, R.drawable.block_default);      //  f: fixed
//        Bitmap fCir = BitmapFactory.decodeResource(MResource, R.drawable.circle);
        Bitmap bWarrior = BitmapFactory.decodeResource(MResource, R.drawable.block_warrior);
        Bitmap bReduction = BitmapFactory.decodeResource(MResource, R.drawable.block_reduction);
        Bitmap bRefraction = BitmapFactory.decodeResource(MResource, R.drawable.block_refraction);
        Bitmap cReduction = BitmapFactory.decodeResource(MResource, R.drawable.circle_reduction);
        Bitmap cAccel = BitmapFactory.decodeResource(MResource, R.drawable.circle_acceleration);

       /* PolygonPhysicsObject rect1 = new DefaultBlock(new Vector2D(500, 300), 50, 150, fRect);

        PolygonPhysicsObject rect2 = new DefaultBlock(new Vector2D(1000, 900), 200, 100, fRect);

        CirclePhysicsObject circle1 = new CirclePhysicsObject(new Vector2D(550, 300), 25, fCir);
        CirclePhysicsObject circle2 = new CirclePhysicsObject(new Vector2D(550, 1000), 100, fCir);
        CirclePhysicsObject circle3 = new CirclePhysicsObject(new Vector2D(300, 700), 100, fCir);*/

        DefaultBlock block1 = new DefaultBlock(new Vector2D(1,1), 1,1, bDefault, false);
        DefaultBlock block2 = new DefaultBlock(new Vector2D(1,1), 1,1, bDefault, false);
        DefaultBlock block3 = new DefaultBlock(new Vector2D(1,1), 1,1, bDefault, false);
        DefaultBlock block4 = new DefaultBlock(new Vector2D(1,1), 1,1, bDefault, false);
        DefaultBlock block5 = new DefaultBlock(new Vector2D(1,1), 1,1, bDefault, false);

        RefractionBlock block6 = new RefractionBlock(new Vector2D((1,1), 1,1,bRefraction, false);
        RefractionBlock block7 = new RefractionBlock(new Vector2D((1,1), 1,1,bRefraction, false);

        AccelerationCircle block8 = new AccelerationCircle(new Vector2D(1,1), 1,cAccel, false);
        AccelerationCircle block9 = new AccelerationCircle(new Vector2D(1,1), 1,cAccel,false);


        DefaultBlock outColliderBottom_forTest = new DefaultBlock(new Vector2D(750, 2400), 1500, 150, bDefault, false);
        DefaultBlock outColliderLeft = new DefaultBlock(new Vector2D(-75, 1700), 150, 1186, bDefault, false);
        DefaultBlock outColliderRight = new DefaultBlock(new Vector2D(1440+75, 1700), 150, 1186, bDefault, false);
//        PolygonPhysicsObject floor4 = new DefaultBlock(new Vector2D(750, -100), 1500, 200, fRect, false);

        GameObjectList.add(rect1);
        GameObjectList.add(rect2);
        GameObjectList.add(circle1);
        GameObjectList.add(circle2);
        GameObjectList.add(circle3);
        GameObjectList.add(outColliderLeft);
        GameObjectList.add(outColliderRight);
        GameObjectList.add(outColliderBottom_forTest);
//        GameObjectList.add(floor4);
}

    public void skill(){

    }

    public int getLife(){
        return life;
    }
}

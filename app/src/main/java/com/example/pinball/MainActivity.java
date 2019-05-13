package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.pinball.GameObjectCodes.Flipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Flipper flipper1;
    Flipper flipper2;

    private int screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // get screen size(pixel)
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGameInside();
    }

    void setGameInside() {
        PhysicsView gameView = findViewById(R.id.game_inside);

        ArrayList<PhysicsObject> movePack = new ArrayList<>();
        ArrayList<PhysicsObject> pack = new ArrayList<>();


        Bitmap rect = BitmapFactory.decodeResource(getResources(), R.drawable.bitmap1);
        Bitmap circle = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        Bitmap floorRect = BitmapFactory.decodeResource(getResources(), R.drawable.floor);
        Bitmap flipperTemp = BitmapFactory.decodeResource(getResources(),R.drawable.flipper);

//
//        PolygonPhysicsObject rect1 = new PolygonPhysicsObject(new Vector2D(500, 300), 40, 100, rect);
//        movePack.add(rect1);
//        pack.add(rect1);
//
//        PolygonPhysicsObject rect2 = new PolygonPhysicsObject(new Vector2D(1000, 900), 100, 100, rect);
//        movePack.add(rect2);
//        pack.add(rect2);

        flipper1 = new Flipper(new Vector2D(200,1500),0,900,80,floorRect);
        flipper2 = new Flipper(new Vector2D(1200,1500),1,900,80,floorRect);
        pack.add(flipper1);
        pack.add(flipper2);

        CirclePhysicsObject circle1 = new CirclePhysicsObject(new Vector2D(550, 0), 50, circle);
        movePack.add(circle1);
        pack.add(circle1);

        //CirclePhysicsObject circle2 = new CirclePhysicsObject(new Vector2D(550, 1000), 200, circle);
        //movePack.add(circle2);
        //pack.add(circle2);

        PolygonPhysicsObject floor = new PolygonPhysicsObject(new Vector2D(800, 2000), 1500, 300, floorRect, false);
        pack.add(floor);
        PolygonPhysicsObject c = new PolygonPhysicsObject(new Vector2D(100, 1000), 200, 3000, floorRect, false);
        pack.add(c);
        PolygonPhysicsObject d = new PolygonPhysicsObject(new Vector2D(1300, 1000), 200, 3000, floorRect, false);
        pack.add(d);



        gameView.setGameObjectsList(pack);
        gameView.setGameMovableObjectsList(movePack);
        gameView.getDrawEngine().setGameObjectsList(pack);

        gameView.getPhysicsEngine().setGameObjectsList(pack);
        gameView.getPhysicsEngine().setGameMovableObjectsList(movePack);
    }


    public void onclickLeft(View view){
        flipper1.powered();
    }
    public void onclickRight(View view){
        flipper2.powered();
    }
}

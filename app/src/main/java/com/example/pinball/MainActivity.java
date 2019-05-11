package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

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
    void setGameInside(){
        PhysicsView gameView = findViewById(R.id.game_inside);

        ArrayList<PhysicsObjectInterface> movePack = new ArrayList<>();
        ArrayList<PhysicsObjectInterface> pack = new ArrayList<>();

<<<<<<< HEAD

        BitmapFactory.Options originOp = new BitmapFactory.Options();
        originOp.inJustDecodeBounds = true;

        Bitmap img1= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
        PolygonPhysicsObject a = new PolygonPhysicsObject(new Vector2D(300,300),100,100,img1);
        movePack.add(a);
        pack.add(a);
        //Bitmap img2= BitmapFactory.decodeResource(getResources(), R.drawable.floor);
        // b = new PolygonPhysicsObject(new Vector2D(0,1100),img2,false);
        //pack.add(b);
=======
        Bitmap img1= BitmapFactory.decodeResource(getResources(), R.drawable.image);
        CirclePhysicsObject a = new CirclePhysicsObject(new Vector2D(screenWidth / 2.2,0),img1);
        pack.add(a);
        Bitmap img2= BitmapFactory.decodeResource(getResources(), R.drawable.image3);
        CirclePhysicsObject b = new CirclePhysicsObject(new Vector2D(screenWidth / 2,720),img2);
        pack.add(b);
//        Bitmap img4= BitmapFactory.decodeResource(getResources(), R.drawable.image4);
//        CirclePhysicsObject d = new CirclePhysicsObject(new Vector2D(screenWidth / 1.7,300),img4);
//        pack.add(d);
        Bitmap img3= BitmapFactory.decodeResource(getResources(), R.drawable.image2);
        PolygonPhysicsObject c = new PolygonPhysicsObject(new Vector2D(screenWidth / 2,screenHeight),img3);
        pack.add(c);
>>>>>>> origin/master

        gameView.setGameObjectsList(pack);
        gameView.setGameMovableObjectsList(movePack);
        gameView.getDrawEngine().setGameObjectsList(pack);

        gameView.getPhysicsEngine().setGameObjectsList(pack);
        gameView.getPhysicsEngine().setGameMovableObjectsList(movePack);
    }

}

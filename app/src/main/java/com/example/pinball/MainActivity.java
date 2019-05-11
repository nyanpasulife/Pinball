package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGameInside();
    }
    void setGameInside(){
        PhysicsView gameView = findViewById(R.id.game_inside);

        ArrayList<PhysicsObjectInterface> movePack = new ArrayList<>();
        ArrayList<PhysicsObjectInterface> pack = new ArrayList<>();


        BitmapFactory.Options originOp = new BitmapFactory.Options();
        originOp.inJustDecodeBounds = true;

        Bitmap img1= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
        PolygonPhysicsObject a = new PolygonPhysicsObject(new Vector2D(300,300),100,100,img1);
        movePack.add(a);
        pack.add(a);
        //Bitmap img2= BitmapFactory.decodeResource(getResources(), R.drawable.floor);
        // b = new PolygonPhysicsObject(new Vector2D(0,1100),img2,false);
        //pack.add(b);

        gameView.setGameObjectsList(pack);
        gameView.setGameMovableObjectsList(movePack);
        gameView.getDrawEngine().setGameObjectsList(pack);
        gameView.getPhysicsEngine().setGameObjectsList(pack);
        gameView.getPhysicsEngine().setGameMovableObjectsList(movePack);
    }

}

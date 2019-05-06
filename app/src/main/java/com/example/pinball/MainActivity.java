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

        ArrayList<PhysicsObjectInterface> pack = new ArrayList<>();

        Bitmap img1= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap1);
        PolygonPhysicsObject a = new PolygonPhysicsObject(new Vector2D(0,0),img1);
        pack.add(a);
        Bitmap img2= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap2);
        PolygonPhysicsObject b = new PolygonPhysicsObject(new Vector2D(720,1280),img2);
        pack.add(b);

        gameView.setGameObjectsList(pack);
        gameView.getDrawEngine().setGameObjectsList(pack);
        //gameView.getPhysicsEngine().setGameObjectsList(pack);
    }

}

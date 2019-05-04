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
        Bitmap img= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
        PolygonPhysicsObject a = new PolygonPhysicsObject(new Vector2D(500,500),img);
        pack.add(a);

        gameView.setGameObjectsList(pack);
        gameView.getDrawEngine().setGameObjectsList(pack);
        gameView.getPhysicsEngine().setGameObjectsList(pack);
    }

}

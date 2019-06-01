package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.pinball.GameCharacter.TestCharacter;
import com.example.pinball.GameObjectCodes.Flipper;
import com.example.pinball.Physics.PhysicsObject;
import com.example.pinball.Physics.PhysicsView;
import com.example.pinball.Physics.Vector2D;

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

        Bitmap floorRect = BitmapFactory.decodeResource(getResources(), R.drawable.floor);

        flipper1 = new Flipper(new Vector2D(400,2000),0,300,50,floorRect);
        flipper2 = new Flipper(new Vector2D(1100,2000),1,300,50,floorRect);
        pack.add(flipper1);
        pack.add(flipper2);


        TestCharacter hi = new TestCharacter(getResources());
        hi.setCharOnView(0,gameView);
    }


    public void onclickLeft(View view){
        flipper1.powered();
    }
    public void onclickRight(View view){
        flipper2.powered();
    }
}

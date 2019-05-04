package com.example.pinball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        HashMap<Integer, PhysicsObjectInterface> objectList = new HashMap<>();

    }
}

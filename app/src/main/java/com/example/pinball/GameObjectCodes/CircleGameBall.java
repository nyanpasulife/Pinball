package com.example.pinball.GameObjectCodes;

import android.graphics.Bitmap;

import com.example.pinball.Engines.CirclePhysicsObject;
import com.example.pinball.Engines.Vector2D;

public class CircleGameBall extends CirclePhysicsObject implements GameBall {
    CircleGameBall(Vector2D position, Bitmap bitmap){
        this(position, 50, bitmap);
    }
    CircleGameBall(Vector2D position, int r, Bitmap bitmap){
        super(position, r,bitmap);
        
    }


}

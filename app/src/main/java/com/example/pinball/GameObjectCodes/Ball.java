package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.Physics.CirclePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class Ball extends CirclePhysicsObject {

    public Ball(Vector2D position, Bitmap bitmap) {
        super(position, bitmap);
    }

    public Ball(Vector2D position, int r, int id, Resources resources) {
        super(position, r, id, resources);
    }

    public Ball(Vector2D position, int r, int id, Resources rsc, boolean move) {
        super(position, r, id, rsc, move);
    }

    public void resize(double r){
        radius = r;
        resizeBitmap(r * 2);
    }

}

package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class HealerBlock extends RectanglePhysicsObject {
    /*public HealerBlock(Vector2D position, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap);
    }
    public HealerBlock(Vector2D position, double width, double height, Bitmap bitmap, boolean move) {
        super(position, width, height, bitmap, move);
    }*/

    public HealerBlock(Vector2D position, double width, double height, int id, Resources resources) {
        super(position, width, height, id, resources);
    }
    public HealerBlock(Vector2D position, double width, double height, int id, Resources resources, boolean move) {
        super(position, width, height, id, resources, move);
    }

    public void setRotation(double r){
        super.setRotation(r);
    }
}

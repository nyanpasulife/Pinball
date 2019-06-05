package com.example.pinball.GameObjectCodes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.pinball.Physics.RectanglePhysicsObject;
import com.example.pinball.Physics.Vector2D;

public class MagicianBlock extends RectanglePhysicsObject {
   /* public MagicianBlock(Vector2D position, double width, double height, Bitmap bitmap) {
        super(position, width, height, bitmap);
    }*/
   public MagicianBlock(Vector2D position, double width, double height, int id, Resources resources) {
       super(position, width, height, id, resources);
   }
}

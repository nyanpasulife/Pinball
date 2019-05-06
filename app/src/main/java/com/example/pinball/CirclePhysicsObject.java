package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;

public class CirclePhysicsObject implements PhysicsObjectInterface{

    boolean movingObject = false;
    Vector2D materialPoint;
    double range;
    Bitmap image;
    Vector2D velocity = new Vector2D(0,0);

    public CirclePhysicsObject (Vector2D position, Bitmap bitmap){
        materialPoint = position;
        image = bitmap;

        range = bitmap.getWidth() / 2.0;
    }

    @Override
    public boolean collisionCheck(PhysicsObjectInterface x) {

        movingObject = false;

        if(x instanceof CirclePhysicsObject){
            CirclePhysicsObject other = (CirclePhysicsObject)x;
            if(calculateDistance(this.materialPoint, other.materialPoint) <= (this.range + other.range)){
                movingObject = true;
            }
        }

        if(x instanceof PolygonPhysicsObject){
            PolygonPhysicsObject other = (PolygonPhysicsObject) x;
            if(calculateDistance(this.materialPoint, other.MaterialPoint) <= (this.range + other.SuperRange)){
                if(isCollidedWithRect(other)){
                    movingObject = true;
                }
            }
        }

        return movingObject;
    }

    @Override
    public void beCollided(PhysicsObjectInterface x) {
        Vector2D collisionPoint = null;
        Vector2D force = null;

        // TODO: get collision point
        // TODO: get force

        collisionAct(collisionPoint, force);
    }

    @Override
    public void collisionAct(Vector2D collisionPoint, Vector2D force) {

    }

    @Override
    public void gravitationAct(Vector2D gravity) {
        velocity = velocity.plus(gravity);
        materialPoint = materialPoint.plus(velocity);
    }

    @Override
    public void paint(Canvas c) {
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        matrix.postTranslate((float)materialPoint.X, (float)materialPoint.Y);
        c.drawBitmap(image, matrix, paint);
    }

    private double calculateDistance(Vector2D a, Vector2D b){
        return Math.sqrt(Math.pow(a.X - b.X, 2) + Math.pow(a.Y - b.Y, 2));
    }

    private boolean isCollidedWithRect(PolygonPhysicsObject other) {
        boolean result = false;

        //collider setting
        ArrayList<Vector2D> vertexVector = other.VertexVectors;
        ArrayList<Vector2D> boxCollider = new ArrayList<>();
        double colliderHeight =  other.Image.getHeight() / 2 + range;
        double colliderWidth = other.Image.getWidth() / 2 + range;

        double newVectorSize = Math.sqrt(Math.pow(colliderHeight, 2)+Math.pow(colliderWidth, 2));

        for(int i = 0; i < 4; i++){
            boxCollider.add(vertexVector.get(i).reSize(newVectorSize));
        }

        //collision check
        if(boxCollider.get(0).X <= materialPoint.X || boxCollider.get(0).Y <= materialPoint.Y
                || boxCollider.get(2).X >= materialPoint.X || boxCollider.get(2).Y >= materialPoint.Y){
            result = true;
        }

        return result;
    }
}

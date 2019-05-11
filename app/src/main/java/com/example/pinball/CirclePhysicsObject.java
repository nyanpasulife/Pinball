package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class CirclePhysicsObject implements PhysicsObjectInterface{

    private Vector2D materialPoint;
    private double radius;
    private Bitmap image;
    private Vector2D velocity = new Vector2D(0,0);
    private ArrayList<Vector2D> colliderTest = new ArrayList<>();
    Vector2D collisionPoint = new Vector2D(0, 0);
    double m = 1;

    boolean flag = true;

    public CirclePhysicsObject (Vector2D position, Bitmap bitmap){
        materialPoint = position;
        image = bitmap;

        radius = image.getWidth() / 2.0;
    }

    @Override
    public void collisionCheck(PhysicsObjectInterface x) {
        if(x instanceof CirclePhysicsObject) {
            CirclePhysicsObject other = (CirclePhysicsObject) x;

            double distance = distance(this.materialPoint, other.getMaterialPoint());
            if(distance <= this.radius + other.radius){
                Log.d("debug", "collision");
            }
        }
    }

    private double getForce(CirclePhysicsObject other) {
        double j = 1 * velocity.plus(other.velocity).getSize() / (1/m + 1/other.m);
        return j/m;
    }

    @Override
    public void addGravitation(Vector2D gravity) {
        velocity = velocity.plus(gravity);
    }

    @Override
    public void act() {
//        addGravitation(new Vector2D(0, 1));
        materialPoint = materialPoint.plus(velocity);
    }

    @Override
    public void paint(Canvas c, double widthRate, double heightRate) {
        Paint imagePaint = new Paint();
        Matrix matrix = new Matrix();

        Vector2D conMaterialPoint = materialPoint.conversion(widthRate, heightRate);
        ArrayList<Vector2D> conVertexVectors = new ArrayList<>();
        for(Vector2D v : colliderTest){
            conVertexVectors.add(v.conversion(widthRate, heightRate));
        }
        matrix.setTranslate((float)(materialPoint.X - radius), (float)(materialPoint.Y - radius));
        c.drawBitmap(image, matrix, imagePaint);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        c.drawCircle((float)collisionPoint.X, (float)collisionPoint.Y, 10, paint);
    }

    @Override
    public void convertBitmap(double widthRate, double heightRate) {        // ???
        image= Bitmap.createBitmap(image, 0,0, (int)(Math.round(image.getWidth())*widthRate),(int)(Math.round(image.getHeight()*heightRate)));
    }

    @Override
    public Bitmap getBitmap() {
        return image;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        image = bitmap;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector2D getMaterialPoint() {
        return materialPoint;
    }

    private double distance(Vector2D a, Vector2D b){
        return Math.sqrt(Math.pow(a.X - b.X, 2) + Math.pow(a.Y - b.Y, 2));
    }

    private boolean isCollidedWithRect(PolygonPhysicsObject other) {
        boolean result = false;

        //collider setting
        ArrayList<Vector2D> vertexVector = other.VertexVectors;
        ArrayList<Vector2D> boxCollider = new ArrayList<>();
        double colliderHeight =  other.getBitmap().getHeight() / 2 + radius;
        double colliderWidth = other.getBitmap().getWidth() / 2 + radius;

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
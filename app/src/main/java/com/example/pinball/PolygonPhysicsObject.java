package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;

public class PolygonPhysicsObject implements PhysicsObjectInterface {
    boolean MovingObject = true; // 물체가 움직이는지 여부

    Vector2D MaterialPoint ; //물체의 무게중심(질점)
    ArrayList<Vector2D> VertexVectors = new ArrayList<>(); // 중심점을 기준으로 꼭지점까지의 벡터들 ,p0 ~ pn-1 , 반드시 예각이 없는 다면체여야함.
    ArrayList<Vector2D> PerpendicularsOfSides = new ArrayList<>(); //각 변과 수직인 법선벡터들, p1 ~ pn
    double SuperRange; //사각형을 감싸는 원의 반지름
    Bitmap Image; //비트맵 이미지.

    Vector2D Velocity = new Vector2D(0,0);

    //비트맵을 받아 비트맵을 감싸는 오브젝트 생성
    PolygonPhysicsObject(Vector2D position, Bitmap bitmap){
        MaterialPoint = position;
        Image =bitmap;
        double w = Image.getWidth()/2;
        double h = Image.getHeight()/2;

        VertexVectors.add(new Vector2D(-w,-h));
        VertexVectors.add(new Vector2D(w,-h));
        VertexVectors.add(new Vector2D(w,h));
        VertexVectors.add(new Vector2D(-w,h));

        PerpendicularsOfSides.add(new Vector2D(0,-1));
        PerpendicularsOfSides.add(new Vector2D(1,0));
        PerpendicularsOfSides.add(new Vector2D(0,1));
        PerpendicularsOfSides.add(new Vector2D(-1,0));

        SuperRange = Math.sqrt(w*w + h*h);
    }


    @Override
    public boolean collisionCheck(PhysicsObjectInterface other){

        if(other instanceof PolygonPhysicsObject){

    }
        //else if (other instanceof RectanglePhysicsView){

       // }

        return false;

    }

    @Override
    public void beCollided(PhysicsObjectInterface other){

    }

    @Override
    public void collisionAct(Vector2D collisionPoint, Vector2D power){

    }

    @Override
    public void gravitationAct(Vector2D gravity) {
        Velocity = Velocity.plus(gravity);
        MaterialPoint = MaterialPoint.plus(Velocity);
    }


    @Override
    public void paint(Canvas c) {


        Paint paint2 = new Paint();
        Matrix matrix = new Matrix();
        Vector2D firstPoint = MaterialPoint.plus(VertexVectors.get(0));
        matrix.postTranslate((float)firstPoint.X, (float)firstPoint.Y);
        c.drawBitmap(Image, matrix, paint2);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        for(Vector2D v  :VertexVectors){
            Vector2D otherPoint = MaterialPoint.plus(v);
            c.drawLine((float)MaterialPoint.X,(float)MaterialPoint.Y,(float)otherPoint.X,(float)otherPoint.Y,paint);
        }
    }
}

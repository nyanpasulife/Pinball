package com.example.pinball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class PolygonPhysicsObject  implements PhysicsObjectInterface {
    Vector2D MaterialPoint ; //물체의 무게중심(질점)
    ArrayList<Vector2D> VertexPoints = new ArrayList<>(); // 중심점을 기준으로 꼭지점까지의 벡터들 ,p0 ~ pn-1 , 반드시 예각이 없는 다면체여야함.
    ArrayList<Vector2D> PerpendicularsOfSides = new ArrayList<>(); //각 변과 수직인 법선벡터들, p1 ~ pn
    Bitmap Image;

    PolygonPhysicsObject(Vector2D position, Vector2D a, Vector2D b){
        MaterialPoint = position;

        VertexPoints.add(a.plus(b));
        VertexPoints.add(b.minus(a));
        VertexPoints.add(a.inverse().minus(b));
        VertexPoints.add(a.minus(b));

        PerpendicularsOfSides.add(b.reSize(1));
        PerpendicularsOfSides.add(a.inverse().reSize(1));
        PerpendicularsOfSides.add(b.inverse().reSize(1));
        PerpendicularsOfSides.add(a.reSize(1));
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
    public void act(Vector2D collisionPoint, Vector2D power){

    }

    @Override
    public void paint(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        for(Vector2D v  :VertexPoints){
            int index = VertexPoints.indexOf(v);
            Vector2D nextV;
            try{nextV = VertexPoints.get(index +1);}catch (Exception e){nextV = VertexPoints.get(0);}
            c.drawLine((float)v.X,(float)v.Y,(float)nextV.X,(float)nextV.Y, paint);
        }
    }
}

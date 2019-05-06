package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.util.Log;

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

    //비트맵을 받고 따로 충돌영역을 커스터마이징 하는 생성자 추가바람...


    @Override
    public void collisionCheck(PhysicsObjectInterface other){

        double rangeEachOthers = MaterialPoint.minus(other.getMaterialPoint()).getSize();
        if(MaterialPoint.minus(other.getMaterialPoint()).getSize() +0.02 <= SuperRange + other.getRadius()){
            collisionCheck2(other);
        }


    }

    public void collisionCheck2(PhysicsObjectInterface other){
        if(other instanceof PolygonPhysicsObject){
            PolygonPhysicsObject otherPol = (PolygonPhysicsObject) other;
            Vector2D otherMP =otherPol.MaterialPoint;
            int otherPolSize = otherPol.VertexVectors.size();
            boolean breakBool = false;
            for(Vector2D myVertexV : VertexVectors){
                Vector2D myV = MaterialPoint.plus(myVertexV);
                for(int j=0;j<otherPolSize;j++){
                    Vector2D otherVertexV = otherPol.VertexVectors.get(j);
                    Vector2D otherV = otherMP.plus(otherVertexV);
                    Vector2D compareV = myV.minus(otherV);

                    Vector2D otherPerpendicular = otherPol.PerpendicularsOfSides.get(j);
                    double comparedValue = otherPerpendicular.dotProduct(compareV);
                    if(comparedValue >=0){ break; }
                    if(j == otherPolSize-1){
                        collided(new Vector2D(0,0), other);
                        breakBool = true;
                        break;
                    }
                }
                if(breakBool){break;}
            }
        }
    }

    public void collided(Vector2D collisionPoint, PhysicsObjectInterface other){

    }

    public void addImpulse(Vector2D impulse) {

    }

    @Override
    public void addGravitation(Vector2D gravity) {
        Velocity = Velocity.plus(gravity);
    }

    @Override
    public void act() {

    }

    public void convertBitmap(double widthRate, double heightRate){
        Image = Bitmap.createBitmap(Image, 0,0, (int)(Math.round(Image.getWidth()*widthRate)),(int)(Math.round(Image.getHeight()*heightRate)));
    }

    @Override
    public void paint(Canvas c, double widthRate, double heightRate) {
        Paint paint2 = new Paint();
        Matrix matrix = new Matrix();

        Vector2D conMaterialPoint = MaterialPoint.conversion(widthRate,heightRate);
        ArrayList<Vector2D> conVertexVectors = new ArrayList<>();
        for(Vector2D v : VertexVectors){
            conVertexVectors.add(v.conversion(widthRate,heightRate));
        }
        Vector2D conFirstPoint = conMaterialPoint.plus(conVertexVectors.get(0));
        matrix.setTranslate((float)(conFirstPoint.X), (float)(conFirstPoint.Y));
        c.drawBitmap(Image, matrix, paint2);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        for(Vector2D v  :conVertexVectors){
            Vector2D otherPoint = conMaterialPoint.plus(v);
            c.drawLine((float)conMaterialPoint.X,(float)conMaterialPoint.Y,(float)otherPoint.X,(float)otherPoint.Y,paint);
        }
    }

    public Bitmap getBitmap(){
        return Image;
    }

    @Override
    public void setBitmap(Bitmap bitmap) {
        Image = bitmap;
    }

    @Override
    public double getRadius() {
        return SuperRange;
    }


    public Vector2D getMaterialPoint(){
        return MaterialPoint;
    }
}
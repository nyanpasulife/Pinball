package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;

public class PolygonPhysicsObject implements PhysicsObjectInterface {
    boolean MovingObject = true; // 물체가 움직이는지 여부

    Vector2D MaterialPoint; //물체의 무게중심(질점)
    ArrayList<Vector2D> VertexVectors = new ArrayList<>(); // 중심점을 기준으로 꼭지점까지의 벡터들 ,p0 ~ pn-1 , 반드시 예각이 없는 다면체여야함.
    ArrayList<Vector2D> PerpendicularsOfSides = new ArrayList<>(); //각 변과 수직인 법선벡터들, p1 ~ pn
    double SuperRange; //사각형을 감싸는 원의 반지름
    Bitmap Image; //비트맵 이미지.

    Vector2D Velocity = new Vector2D(0, 0);
    double Rotation = 0;
    double RotationSpeed = 0; //양수:반시계방향 음수:시계방향
    double InverseOfMass = 0.05; // 질량의 역수
    double InverseOfI; //관성모멘트의 역수. 복잡한 도형은 근사값만 사용.

    Matrix MMatrix = new Matrix();
    Paint MPaint = new Paint();
    Vector2D ImagePaintVector;

    //비트맵을 받아 비트맵을 감싸는 오브젝트 생성
    PolygonPhysicsObject(Vector2D position, double width, double height, Bitmap bitmap) {
        MaterialPoint = position;
        Image = bitmap;
        resizeBitmapToOrigin(width, height);
        double wHalf = width / 2;
        double hHalf = height / 2;

        VertexVectors.add(new Vector2D(-wHalf, -hHalf));
        VertexVectors.add(new Vector2D(wHalf, -hHalf));
        VertexVectors.add(new Vector2D(wHalf, hHalf));
        VertexVectors.add(new Vector2D(-wHalf, hHalf));

        ImagePaintVector = VertexVectors.get(0);

        PerpendicularsOfSides.add(new Vector2D(0, -1));
        PerpendicularsOfSides.add(new Vector2D(1, 0));
        PerpendicularsOfSides.add(new Vector2D(0, 1));
        PerpendicularsOfSides.add(new Vector2D(-1, 0));

        SuperRange = Math.sqrt(width * width + height * height);
        InverseOfI = (3 * InverseOfMass) / (width * width + height * height);
    }

    PolygonPhysicsObject(Vector2D position, double width, double height, Bitmap bitmap, boolean moveBool) {
        this(position, width, height, bitmap);
        MovingObject = moveBool;
        InverseOfMass = 0;
        InverseOfI = 0;
    }

    public void resizeBitmapToOrigin(double width, double height) {
        Image = Bitmap.createScaledBitmap(Image, (int) width, (int) height, false);
    }

    //비트맵을 받고 따로 충돌영역을 커스터마이징 하는 생성자 추가바람...


    @Override
    public void collisionCheck(PhysicsObjectInterface other) {

        double rangeEachOthers = MaterialPoint.minus(other.getMaterialPoint()).getSize();
        if (MaterialPoint.minus(other.getMaterialPoint()).getSize() + 0.02 <= SuperRange + other.getRadius()) {
            collisionCheck2(other);
        }


    }

    public void collisionCheck2(PhysicsObjectInterface other) {
        double collisionDepth;
        Vector2D collisionPoint;

        if (other instanceof PolygonPhysicsObject) {
            PolygonPhysicsObject otherPol = (PolygonPhysicsObject) other;
            GJK gjkResult = new GJK(this, otherPol);

            /*PolygonPhysicsObject otherPol = (PolygonPhysicsObject) other;
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

                    if(collisionDepth ==-4000){
                        collisionDepth= Math.abs(comparedValue);
                    }
                    else if(collisionDepth >= Math.abs(comparedValue)){
                        collisionDepth = Math.abs(comparedValue); //comparedValue 중에 작은값이 충돌깊이이다.
                   }

                    if(j == otherPolSize-1){
                        betweenPolygonCollided(myV, otherPerpendicular, collisionDepth,otherPol);
                        breakBool = true;
                        break;
                    }
                }
                if(breakBool){break;}
            }*/
        }
        //else if(){}
    }


    public void betweenPolygonCollided(Vector2D collisionPoint, Vector2D collisionDirection, double collisionDepth, PolygonPhysicsObject other) { //충돌점, 충돌점에서 충돌방향, 충돌깊이, 다른 폴리곤 오브젝트
        double impulse = 0;
        Vector2D n = collisionDirection;

        if (other.MovingObject == true) {
            //


            //두 물체를 중심점에서 질량의 역수 비율만큼 떨어트린다.


            //두 물체 사이에 작용하는 충격력을 구하고 각각 addImpulse 를 호출해서 두 폴리곤의 속력과 각속도를 바꾼다.
            /*Vector2D AMtoP = collisionPoint.minus(MaterialPoint);
            Vector2D normalAngularThis = AMtoP.getNormalVector();//A의 각속도 방향


            Vector2D BMtoP = collisionPoint.minus(other.MaterialPoint);
            Vector2D normalAngularOther = BMtoP.getNormalVector(); //B의 각속도 방향

            Vector2D velocityAatP = Velocity.plus(normalAngularThis.constantProduct(RotationSpeed));
            Vector2D velocityBatP = other.Velocity.plus(normalAngularOther.constantProduct(other.RotationSpeed));
            Vector2D vAB = velocityAatP.minus(velocityBatP);
            double impulseFormulaNumerator = vAB.constantProduct(-2).dotProduct(n);

            double denominator1 = n.dotProduct(n.constantProduct(InverseOfMass + other.InverseOfMass));
            double denominator2 = normalAngularThis.dotProduct(n)*normalAngularThis.dotProduct(n)*InverseOfMass;
            double denominator3 = normalAngularOther.dotProduct(n)*normalAngularOther.dotProduct(n)*other.InverseOfMass;
            double impulseFormulaDenominator = denominator1 + denominator2 + denominator3;

            impulse = impulseFormulaNumerator / impulseFormulaDenominator;
            this.addImpulse(impulse,collisionDirection,normalAngularThis);
            other.addImpulse(0-impulse,collisionDirection,normalAngularOther);*/

        } else { //other.MovingObject != false
            //자신을 충돌지점에서 충돌 깊이만큼 빼낸다.
            Vector2D collisionDepthVector = collisionDirection.constantProduct(collisionDepth);
            MaterialPoint.plus(collisionDepthVector);

            //자신에게 작용하는 충격력을 구하고 addImpulse 로 이동 방향을 정한다. 상대의 질량이 무한이라고 가정.
            Vector2D AMtoP = collisionPoint.minus(MaterialPoint);
            Vector2D normalAngularThis = AMtoP.getNormalVector();//A의 각속도 방향

            Vector2D velocityAatP = Velocity.plus(normalAngularThis.constantProduct(RotationSpeed));
            Vector2D vAB = velocityAatP;
            double impulseFormulaNumerator = vAB.constantProduct(-2).dotProduct(n);

            double denominator1 = n.dotProduct(n.constantProduct(InverseOfMass));
            double denominator2 = normalAngularThis.dotProduct(n) * normalAngularThis.dotProduct(n) * InverseOfMass;
            double impulseFormulaDenominator = denominator1 + denominator2;

            impulse = impulseFormulaNumerator / impulseFormulaDenominator;
            this.addImpulse(impulse, collisionDirection, normalAngularThis);
        }
    }

    public void addImpulse(double impulse, Vector2D collisionDirection, Vector2D normalAngular) {
        Vector2D n = collisionDirection;
        Velocity = Velocity.plus(n.constantProduct(impulse * InverseOfMass));
        RotationSpeed = RotationSpeed + (normalAngular.dotProduct(n.constantProduct(impulse)) * InverseOfI);
    }

    @Override
    public void addGravitation(Vector2D gravity) {
        Velocity = Velocity.plus(gravity);
    }

    @Override
    public void act() {
        MaterialPoint = MaterialPoint.plus(new Vector2D(0, 0.0001));
        //MaterialPoint = MaterialPoint.plus(Velocity);
        //Vector2D FirstPoint = MaterialPoint.plus(VertexVectors.get(0));
        /*Rotation +=RotationSpeed;
        if(Rotation <0 || Rotation >360);{
            Rotation = Rotation %360;
        }
        for(Vector2D e : VertexVectors){
            e.rotate(RotationSpeed);
        */
    }


    @Override
    public void paint(Canvas c, double widthRate, double heightRate) {
        Vector2D imagePaintPoint = getImagePaintPoint();
        MMatrix.setTranslate((float) imagePaintPoint.X, (float) imagePaintPoint.Y);
        MMatrix.postRotate((float) Rotation);
        c.drawBitmap(Image, MMatrix, new Paint());

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        for (Vector2D v : VertexVectors) {
            Vector2D otherPoint = MaterialPoint.plus(v);
            c.drawLine((float) MaterialPoint.X, (float) MaterialPoint.Y, (float) otherPoint.X, (float) otherPoint.Y, paint);
        }
    }

    public Vector2D getImagePaintPoint() {
        return MaterialPoint.plus(ImagePaintVector);
    }

    public Bitmap getBitmap() {
        return Image;
    }

    public void setBitmap(Bitmap bitmap) {
        Image = bitmap;
    }

    @Override
    public double getRadius() {
        return SuperRange;
    }


    public Vector2D getMaterialPoint() {
        return MaterialPoint;
    }
}
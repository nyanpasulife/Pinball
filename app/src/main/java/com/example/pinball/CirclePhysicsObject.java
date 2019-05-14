package com.example.pinball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class CirclePhysicsObject extends PhysicsObject {

    private boolean MovingObject = true;

    private Vector2D materialPoint;
    private double radius;
    private double InverseOfMass = 0.001;
    private double InverseOfI = 0;

    private Bitmap image;

    private Vector2D velocity = new Vector2D(0, 0);
    private ArrayList<Vector2D> colliderTest = new ArrayList<>();
    Vector2D collisionPoint = new Vector2D(0, 0);
    Vector2D collisionDirection = new Vector2D(0, 0);
    double collisionDepth = 0;

    //double m = 1;

    boolean flag = true;

    Paint imagePaint = new Paint();
    Matrix matrix = new Matrix();

    public CirclePhysicsObject(Vector2D position, Bitmap bitmap) {
        materialPoint = position;
        image = bitmap;
        radius = image.getWidth() / 2.0;
    }

    public CirclePhysicsObject(Vector2D position, int r, Bitmap bitmap) {
        materialPoint = position;
        image = bitmap;
        resizeBitmapToOrigin(r);
        radius = image.getWidth() / 2.0;
    }

    void resizeBitmapToOrigin(int r) { //TODO Bitmap을 drawble로 올리면 px 값이 아닌 dp 값이 적용되어서 강제로 px 값으로 변환하는게 규격 통일에 나을것 같습니다. (아예 오브젝트를 생성할때 픽셀 크기를 지정.)
        image = Bitmap.createScaledBitmap(image, r*2, r*2, false);
    }

    @Override
    public boolean collisionCheck(PhysicsObject x) {
        if (x instanceof CirclePhysicsObject) {
            CirclePhysicsObject other = (CirclePhysicsObject) x;

            double distance = distance(this.materialPoint, other.getMaterialPoint());

            if (distance <= this.radius + other.radius) {
                /*other.flag = false;
                moveByCollision(other);
                other.moveByCollision(this);  구버전 코드
                other.flag = true;

                moveByCollision(other);*/

                //other에서 this의 중점으로 향하는 벡터를 만들고, 그 벡터의 크기를 절반으로 줄였을 때, 도달하는 위치의 점이 충돌 지점.
                Vector2D OtherToThisVector= this.materialPoint.minus(other.getMaterialPoint());
                OtherToThisVector = OtherToThisVector.reSize(0.5);
                collisionPoint = other.getMaterialPoint().plus(OtherToThisVector);

                // 충돌 깊이 = distance - {(distance - this.r) + (distance - other.r)} = this.r + other.r -distance
                collisionDepth = this.radius + other.radius - distance;

                // 충돌 방향 = OtherToThisVector 의 정규(normal) 벡터
                collisionDirection = OtherToThisVector.reSize(1);

                outDepth_getImpuse(this,other,collisionPoint,collisionDirection,collisionDepth); //충격 공식
                return true;
            }
        }

        if (x instanceof PolygonPhysicsObject) {
            PolygonPhysicsObject other = (PolygonPhysicsObject) x;
            if (true) { // TODO 본래: distance(this.materialPoint, other.getMaterialPoint()) <= (this.radius + other.getRadius())
                if (isCollidedWithRect(other)) {
                    outDepth_getImpuse(this,other,collisionPoint,collisionDirection,collisionDepth);
                    return true;
                }
            }
        }
        return false;
    }


    private void moveByCollision(CirclePhysicsObject other) {

        double otherPositionVX = other.materialPoint.X - this.materialPoint.X;
        double otherPositionVY = other.materialPoint.Y - this.materialPoint.Y;
        Log.d("mpx", Double.toString(otherPositionVX));
        Log.d("mpy", Double.toString(otherPositionVY));

        this.collisionPoint = new Vector2D(otherPositionVX, otherPositionVY);
        double scalar = distance(this.materialPoint, other.materialPoint) * this.radius / (this.radius + other.radius);
        this.collisionPoint.reSize(scalar);

        Log.d("collision x", Double.toString(collisionPoint.X));
        Log.d("collision y", Double.toString(collisionPoint.Y));


        //repositioning
        other.materialPoint.X += this.collisionPoint.X / this.radius;
        other.materialPoint.Y += this.collisionPoint.Y / this.radius;

        //update velocity
        other.velocity.X += this.collisionPoint.X / this.radius;
        other.velocity.Y += this.collisionPoint.Y / this.radius;
    }

   /* private double getForce(CirclePhysicsObject other) {
        double j = 1 * velocity.minus(other.velocity).getSize() / (1 / m + 1 / other.m);
        return j / m / 100;
    }*/

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

        /*ArrayList<Vector2D> conVertexVectors = new ArrayList<>();
        for(Vector2D v : colliderTest){
            conVertexVectors.add(v.conversion(widthRate, heightRate));
        }*/
        matrix.setTranslate((float) (materialPoint.X - radius), (float) (materialPoint.Y - radius));
        c.drawBitmap(image, matrix, imagePaint);
    }


    public void convertBitmap(double widthRate, double heightRate) {        // ???
        image = Bitmap.createBitmap(image, 0, 0, (int) (Math.round(image.getWidth()) * widthRate), (int) (Math.round(image.getHeight() * heightRate)));
    }

    public Bitmap getBitmap() {
        return image;
    }

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

    private double distance(Vector2D a, Vector2D b) {
        return Math.sqrt(Math.pow(a.X - b.X, 2) + Math.pow(a.Y - b.Y, 2));
    }

    private boolean isCollidedWithRect(PolygonPhysicsObject other) {
        boolean result = false;

        //collider setting
        ArrayList<Vector2D> vertexVector = other.OriginVertexVectors;
        ArrayList<Vector2D> boxCollider = new ArrayList<>();
        /*double colliderHeight =  other.getBitmap().getHeight() / 2 + radius;
        double colliderWidth = other.getBitmap().getWidth() / 2 + radius; */

        //double newVectorSize = Math.sqrt(Math.pow(colliderHeight, 2)+Math.pow(colliderWidth, 2));

        //collision check
        for (int i = 0; i < 4; i++) {
            boxCollider.add(vertexVector.get(i).plus(other.MaterialPoint)); //충돌하는 사각형의, 회전되지 않은 상태의 점 정보 4개 (View 의 절대좌표상태)
        }
        Vector2D IR_materialPoint = materialPoint.rotateDotByPivot(-other.Rotation, other.MaterialPoint); //사각형이 중심점에서 회전하는 것을 기준으로 그 반대로 회전된 원의 중심점.

        int zoneStatus = getRectZone(IR_materialPoint, boxCollider); //회전된 원 중심점의 위치 확인.
        if (zoneStatus == 0) { //원의 중심점이 사각형 내부에 있을경우 반드시 충돌.
            result = true;
        } else if (zoneStatus == 1) { //원의 중심점이 사각형 모서리 방향에 있을경우 그 모서리가 원과 충돌하면 충돌.
            for (Vector2D e : boxCollider) {
                if (getCircleZone(e, IR_materialPoint, radius)) {
                    result = true;
                    collisionPoint = e;
                }
            }
        } else if (zoneStatus == 2) {  //원의 반지름과 사각형 중심의 Y값의 차이가, 높이값의 절반과 반지름의 합보다 작으면 충돌.
            double h = other.getBitmap().getHeight() / 2;
            double r = radius;
            double a = Math.abs(IR_materialPoint.Y - other.MaterialPoint.Y);
            if (h + r >= a) {
                result = true;
            }
        } else if (zoneStatus == 3) {  //원의 반지름과 사각형 중심의 X값의 차이가 넓이값의 절반보다 작으면 충돌.
            double h = other.getBitmap().getWidth() / 2;
            double r = radius;
            double a = Math.abs(IR_materialPoint.X - other.MaterialPoint.X);
            if (h + r >= a) {
                result = true;
            }
        }

        if (result == true && (zoneStatus == 0 || zoneStatus == 2 || zoneStatus == 3)) {
            Vector2D[] colliedSide = UtilFunc.getClosedAABBSideWithDot(IR_materialPoint, boxCollider);
            collisionPoint = colliedSide[2];
            collisionDirection = colliedSide[3];
            if (zoneStatus == 0) {
                collisionDepth = radius + Math.abs(IR_materialPoint.minus(collisionPoint).getSize());
            } else {
                collisionDepth = Math.abs(radius - Math.abs(IR_materialPoint.minus(collisionPoint).getSize()));
            }
        } else if (result == true && zoneStatus == 1) {
            // collisionPoint 충돌 체크에서 추가함
            collisionDirection = IR_materialPoint.minus(collisionPoint);
            collisionDepth = Math.abs(radius - collisionDirection.getSize());
        }

        /*if(boxCollider.get(0).X <= materialPoint.X || boxCollider.get(0).Y <= materialPoint.Y
                || boxCollider.get(2).X >= materialPoint.X || boxCollider.get(2).Y >= materialPoint.Y){
            result = true;
        }*/

        //구해진 충돌 정보를 사각형의 중점 기준으로 다시 회전시켜 본래의 충돌 정보를 구함.
        collisionPoint = collisionPoint.rotateDotByPivot(other.Rotation, other.MaterialPoint);
        collisionDirection = collisionDirection.rotate(other.Rotation);

        return result;
    }

    int getRectZone(Vector2D dot, ArrayList<Vector2D> RectDots) { //점이 사각형 어디에 위치하느냐에 따라 사각형 내부: 0, 사각형 모서리: 1, 사각형 위아래 변:2, 사각형 양옆 변: 3을 반환함.
        //TODO UtilFunc 으로 이동.
        double[] AABBxy = UtilFunc.get_AABB_XY(RectDots);
        double leastX = AABBxy[0], greatestX = AABBxy[1], leastY = AABBxy[2], greatestY = AABBxy[3];
        boolean dotX_inRect = (dot.X >= leastX && dot.X <= greatestX);
        boolean dotY_inRect = (dot.Y >= leastY && dot.Y <= greatestY);
        if (dotX_inRect && dotY_inRect) {
            return 0;
        } else if (dotX_inRect) {
            return 2;
        } else if (dotY_inRect) {
            return 3;
        } else {
            return 1;
        }

    }

    boolean getCircleZone(Vector2D dot, Vector2D center, double r) { //점이 원 내부에 존재하는지 판별.
        if (dot.minus(center).getSize() < r) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addImpulseAndFriction(double impulse, Vector2D n, Vector2D normalAngularA, double frictionForceScalar) {
        Vector2D impulseVector = n.constantProduct(impulse * InverseOfMass);
        velocity = velocity.plus(impulseVector);
    }

    @Override
    public void setMaterialPoint(Vector2D mP) {
        materialPoint = mP;
    }

    @Override
    protected boolean isMovingObject() {
        return MovingObject;
    }

    @Override
    public double getInverseOfMass() {
        return InverseOfMass;
    }

    @Override
    protected double getInverseOfI() {
        return 0;
    }

    @Override
    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Vector2D v) {
        velocity = v;
    }

    @Override
    protected Vector2D getVelocityAtP(Vector2D collisionPoint) {
        return velocity;
    }
}
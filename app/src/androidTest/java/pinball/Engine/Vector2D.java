package pinball.Engine;

import java.io.Serializable;

public class Vector2D implements Serializable {
    double X, Y;
    public Vector2D(double x, double y){
        X = x;
        Y = y;
    }

    public com.example.pinball.Engine.Vector2D plus(com.example.pinball.Engine.Vector2D other){
        return new com.example.pinball.Engine.Vector2D(X+other.X, Y+other.Y);
    }
    public com.example.pinball.Engine.Vector2D minus(com.example.pinball.Engine.Vector2D other){
        return new com.example.pinball.Engine.Vector2D(X-other.X, Y-other.Y);
    }
    public com.example.pinball.Engine.Vector2D conversion(double a, double b){
//        return new Vector2D(X*a, Y*b);        //TODO: 이미지 전체 비율에 문제가 있습니다. 일단 주석처리 해두겠습니다.
        return new com.example.pinball.Engine.Vector2D(X, Y);
    }
    public com.example.pinball.Engine.Vector2D inverse(){
        return new com.example.pinball.Engine.Vector2D(-X,-Y);
    }
    public double dotProduct(com.example.pinball.Engine.Vector2D other){
        return X*other.X + Y*other.Y;
    }
    public com.example.pinball.Engine.Vector2D constantProduct(double a){return new com.example.pinball.Engine.Vector2D(a*X,a*Y);}
    public double imaginaryZCrossProduct(com.example.pinball.Engine.Vector2D other){return X*other.Y - Y*other.X;}
    public double getSize(){
        return Math.sqrt(X*X + Y*Y);
    }
    public com.example.pinball.Engine.Vector2D reSize(double afterSize){
        double beforeSize = getSize();
        double ratio = afterSize / beforeSize;
        return new com.example.pinball.Engine.Vector2D(X *ratio,Y*ratio);
    }
    public com.example.pinball.Engine.Vector2D getNormalVector(){
        com.example.pinball.Engine.Vector2D returnVector = new com.example.pinball.Engine.Vector2D(-Y,X);
        returnVector = returnVector.reSize(1);
        return returnVector;
    } //90도 회전한 정규벡터
    public com.example.pinball.Engine.Vector2D rotate(double angle){
        double radianAngle = (Math.PI / 180) * angle;
        com.example.pinball.Engine.Vector2D returnVector = new com.example.pinball.Engine.Vector2D(0,0);
        returnVector.X = Math.cos(radianAngle)*X - Math.sin(radianAngle)*Y;
        returnVector.Y = Math.sin(radianAngle)*X + Math.cos(radianAngle)*Y;
        return returnVector;
    }
    public com.example.pinball.Engine.Vector2D rotateDotByPivot(double angle, com.example.pinball.Engine.Vector2D pivot){
        com.example.pinball.Engine.Vector2D IRVector = this.minus(pivot);
        IRVector = IRVector.rotate(angle);
        return pivot.plus(IRVector);
    }
    public com.example.pinball.Engine.Vector2D tripleCrossProduct(com.example.pinball.Engine.Vector2D other1, com.example.pinball.Engine.Vector2D other2){
        com.example.pinball.Engine.Vector2D A = this;
        com.example.pinball.Engine.Vector2D B = other1;
        com.example.pinball.Engine.Vector2D C = other2;
        return B.constantProduct(C.dotProduct(A)).minus(A.constantProduct(C.dotProduct(B))).reSize(1);
    }

    public LinearLine getLinearLine(com.example.pinball.Engine.Vector2D other){ //두 벡터가 정점일때, 그 두점을 지나는 일차방정식을 구함.
        double a =this.X;  double b = this.Y;  double c = other.X;  double d = other.Y;
        if(a==c){
            return new LinearNoYLine(a);
        }
        else {
            double A = (b - d) / (a - c);
            double B = ((a * d) - (b * c)) / (a - c);
            return new LinearEquation(A, B);
        }
    }

    @Override
    public boolean equals(Object obj){
        com.example.pinball.Engine.Vector2D other =(com.example.pinball.Engine.Vector2D)obj;
        if(this.X == other.X & this.Y ==other.Y){
            return true;
        }
        else{
            return false;
        }
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }
}

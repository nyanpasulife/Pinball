package com.example.pinball;

import android.content.MutableContextWrapper;

public class Vector2D {
    double X, Y;
    Vector2D(double x, double y){
        X = x;
        Y = y;
    }

    public Vector2D plus(Vector2D other){
        return new Vector2D(X+other.X, Y+other.Y);
    }
    public Vector2D minus(Vector2D other){
        return new Vector2D(X-other.X, Y-other.Y);
    }
    public Vector2D conversion(double a,double b){
        return new Vector2D(X*a, Y*b);
    }
    public Vector2D inverse(){
        return new Vector2D(-X,-Y);
    }
    public double dotProduct(Vector2D other){
        return X*other.X + Y*other.Y;
    }
    public Vector2D constantProduct(double a){return new Vector2D(a*X,a*Y);}
    public double imaginaryZCrossProduct(Vector2D other){return X*other.Y - Y*other.X;}
    public double getSize(){
        return Math.sqrt(X*X + Y*Y);
    }
    public Vector2D reSize(double afterSize){
        double beforeSize = getSize();
        double ratio = afterSize / beforeSize;
        return new Vector2D(X *ratio,Y*ratio);
    }
    public Vector2D getNormalVector(){
        return new Vector2D(-Y,X).reSize(1);
    }
    public Vector2D rotate(double angle){
        Vector2D returnVector = new Vector2D(0,0);
        returnVector.X = Math.cos(angle)*X - Math.sin(angle)*Y;
        returnVector.Y = Math.sin(angle)*X + Math.cos(angle)*Y;
        return returnVector;
    }
    public Vector2D tripleCrossProduct(Vector2D other1, Vector2D other2){
        Vector2D A = this;
        Vector2D B = other1;
        Vector2D C = other2;
        return B.constantProduct(C.dotProduct(A)).minus(A.constantProduct(C.dotProduct(B))).reSize(1);
    }

    @Override
    public boolean equals(Object obj){
        Vector2D other =(Vector2D)obj;
        if(this.X == other.X & this.Y ==other.Y){
            return true;
        }
        else{
            return false;
        }
    }

}

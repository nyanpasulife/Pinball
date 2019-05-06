package com.example.pinball;

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
    public Vector2D inverse(){
        return new Vector2D(-X,-Y);
    }
    public double dotProduct(Vector2D other){
        return X*other.X + Y*other.Y;
    }
    public double getSize(){
        return Math.sqrt(X*X + Y*Y);
    }
    public Vector2D reSize(double afterSize){
        double beforeSize = getSize();
        double ratio = afterSize / beforeSize;
        return new Vector2D(X *ratio,Y*ratio);
    }

}

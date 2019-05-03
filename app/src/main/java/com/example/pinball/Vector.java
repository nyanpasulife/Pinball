package com.example.pinball;

public class Vector {
    double X, Y;
    Vector(double x, double y){
        X = x;
        Y = y;
    }

    public Vector plus(Vector other){
        return new Vector(X+other.X, Y+other.Y);
    }
    public Vector minus(Vector other){
        return new Vector(X-other.X, Y-other.Y);
    }
    public double dotProduct(Vector other){
        return X*other.X + Y*other.Y;
    }
    public double getSize(){
        return Math.sqrt(X*X + Y*Y);
    }
    public Vector reSize(double afterSize){
        double beforeSize = getSize();
        double ratio = afterSize / beforeSize;
        return new Vector(X *ratio,Y*ratio);
    }

}

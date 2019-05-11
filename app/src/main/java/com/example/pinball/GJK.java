package com.example.pinball;

import java.util.ArrayList;

public class GJK {
    ArrayList<Vector2D> APoints = new ArrayList<>();
    ArrayList<Vector2D> BPoints = new ArrayList<>();
    ArrayList<Vector2D> Simplex = new ArrayList<>();

    Vector2D SearchD = new Vector2D(1, 0);

    boolean Result = false;

    GJK(PolygonPhysicsObject a, PolygonPhysicsObject b) {
        for (Vector2D e : a.VertexVectors) {
            APoints.add(a.MaterialPoint.plus(e));
        }
        for (Vector2D e : b.VertexVectors) {
            BPoints.add(b.MaterialPoint.plus(e));
        }
        Result = doGJK(APoints, BPoints);

    }
    GJK(ArrayList<Vector2D> a, ArrayList<Vector2D> b){
        APoints = a;
        BPoints = b;
        Result = doGJK(APoints, BPoints);
    }

    private boolean doGJK(ArrayList<Vector2D> aPoints, ArrayList<Vector2D> bPoints) {
        Simplex.add(supportFunction(SearchD));
        SearchD = SearchD.inverse();

        while (true) {
            Vector2D simplexLast = supportFunction(SearchD);
            Simplex.add(simplexLast);
            if (simplexLast.dotProduct(SearchD) <= 0) {
                return false;
            } else {
                if (isSimplexContainsOrigin(Simplex)) {
                    return true;
                }
            }
        }

    }

    private boolean isSimplexContainsOrigin(ArrayList<Vector2D> simplex) {
        Vector2D a = Simplex.get(Simplex.size()-1);
        Vector2D aInverse = a.inverse();
        if (Simplex.size() == 3) {
            Vector2D c = Simplex.get(0);
            Vector2D b = Simplex.get(1);

            Vector2D ab = b.minus(a);
            Vector2D ac = c.minus(a);
            Vector2D perp_ab = ac.tripleCrossProduct(ab,ab);
            Vector2D perp_ac = ab.tripleCrossProduct(ac,ac);

            if(perp_ab.dotProduct(aInverse) >0){
                Simplex.remove(c);
                SearchD = perp_ab;
            }
            else if(perp_ac.dotProduct(aInverse)>0){
                Simplex.remove(b);
                SearchD = perp_ac;
            }
            else{
                return true;
            }
        }
        else {
            Vector2D b = Simplex.get(0);
            Vector2D ab = b.minus(a);
            Vector2D perp_ab = ab.tripleCrossProduct(aInverse,ab);
            SearchD = perp_ab;
        }
        return false;
    }

    private Vector2D getSearchD(ArrayList<Vector2D> simplex) {
        Vector2D B = Simplex.get(0);
        Vector2D A = Simplex.get(1);
        Vector2D AB = A.minus(B);
        Vector2D AO = A;
        return AB.tripleCrossProduct(AO, AB);
    }


    Vector2D supportFunction(Vector2D d) {
        Vector2D p1 = getLargestDotProduct(APoints, d);
        Vector2D p2 = getLargestDotProduct(BPoints, d.inverse());
        return p1.minus(p2);
    }

    private Vector2D getLargestDotProduct(ArrayList<Vector2D> aPoints, Vector2D d) {
        Vector2D returnVector = null;
        boolean firstBool = true;
        double max = 0;
        double distance;
        for (Vector2D e : aPoints) {
            if (firstBool == true) {
                max = e.dotProduct(d);
                returnVector = e;
                firstBool = false;
            } else {
                distance = e.dotProduct(d);
                if (max < distance) {
                    max = distance;
                    returnVector = e;
                }
            }
        }
        return returnVector;
    }
}


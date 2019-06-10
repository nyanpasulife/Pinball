package pinball.Engine;

import android.graphics.Canvas;

import com.example.pinball.Engine.PhysicsObject;
import com.example.pinball.Engine.Vector2D;


public interface PhysicsObjectInterface {
    boolean collisionCheck(PhysicsObject other);
    void addGravitation(Vector2D gravity);
    void act();
    void paint(Canvas c);
    double getRadius();
    Vector2D getMaterialPoint();
}

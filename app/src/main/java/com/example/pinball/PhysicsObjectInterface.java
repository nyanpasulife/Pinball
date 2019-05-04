package com.example.pinball;

public interface PhysicsObjectInterface {
    boolean collisionCheck(PhysicsObjectInterface x);
    void beCollided(PhysicsObjectInterface x);
    void act(Vector collisionPoint, Vector force);


}

package com.example.pinball.Engine;

import java.util.ArrayList;

public class GameData{
    private ArrayList<PhysicsObject> MovableList = new ArrayList<>();
    private ArrayList<PhysicsObject> GameObjectsList = new ArrayList<>();




    public ArrayList<PhysicsObject> getGameObjectsList() {
        return GameObjectsList;
    }

    public ArrayList<PhysicsObject> getMovableList() {
        return MovableList;
    }

}

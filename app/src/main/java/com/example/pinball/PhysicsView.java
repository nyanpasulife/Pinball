package com.example.pinball;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class PhysicsView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawEngine DrawThread;
    private PhysicsEngine PhysicsThread;
    private ArrayList<PhysicsObject> GameMovableObjectsList = new ArrayList<>();
    private ArrayList<PhysicsObject> GameObjectsList = new ArrayList<>();

    public PhysicsView(Context context){
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder, GameObjectsList);
        PhysicsThread = new PhysicsEngine(GameMovableObjectsList, GameObjectsList);
    }
    public PhysicsView(Context context, AttributeSet attr){
        super(context, attr);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder, GameObjectsList);
        PhysicsThread = new PhysicsEngine(GameMovableObjectsList, GameObjectsList);
    }

    public DrawEngine getDrawEngine(){
        return DrawThread;
    }

    public PhysicsEngine getPhysicsEngine(){
        return PhysicsThread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawThread.setRunning(true);
        DrawThread.start();

        PhysicsThread.setRunning(true);
        PhysicsThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        DrawThread.setRunning(false);
        while(retry){
            try{
                DrawThread.join();
                retry = false;
            }catch(InterruptedException e){}
        }
        retry = true;
        PhysicsThread.setRunning(false);
        while(retry){
            try{
                PhysicsThread.join();
                retry = false;
            }catch(InterruptedException e){}
        }
    }

    public void setGameObjectsList(ArrayList<PhysicsObject> list){
        GameObjectsList = list;
    }
    public void setGameMovableObjectsList(ArrayList<PhysicsObject> list){
        GameMovableObjectsList = list;
    }
}

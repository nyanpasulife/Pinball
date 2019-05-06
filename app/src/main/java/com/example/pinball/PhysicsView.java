package com.example.pinball;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class PhysicsView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawEngine DrawThread;
    private PhysicsEngine PhysicsThread;
    private ArrayList<PhysicsObjectInterface> GameObjectsList = new ArrayList<>();

    public PhysicsView(Context context){
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder, GameObjectsList);
        PhysicsThread = new PhysicsEngine(GameObjectsList);
    }
    public PhysicsView(Context context, AttributeSet attr){
        super(context, attr);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder, GameObjectsList);
        PhysicsThread = new PhysicsEngine(GameObjectsList);
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
        DrawThread.convertDraw(getWidth(),getHeight());
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

    public void setGameObjectsList(ArrayList<PhysicsObjectInterface> list){
        GameObjectsList = list;
    }

}

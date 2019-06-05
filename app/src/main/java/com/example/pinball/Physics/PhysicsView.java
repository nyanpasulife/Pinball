package com.example.pinball.Physics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pinball.GameObjectCodes.Flipper;

import java.util.ArrayList;

public class PhysicsView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawEngine DrawThread;
    private PhysicsEngine PhysicsThread;
    private GameData Data = new GameData();

    public PhysicsView(Context context){
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder, Data);
        PhysicsThread = new PhysicsEngine(Data);
    }
    public PhysicsView(Context context, AttributeSet attr){
        super(context, attr);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder, Data);
        PhysicsThread = new PhysicsEngine(Data);
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


    public void pushObjects(ArrayList<PhysicsObject> list) {
        synchronized (Data) {
            for (PhysicsObject e : list) {
                if (e.MovingObject == true) {
                    Data.getGameObjectsList().add(e);
                    Data.getMovableList().add(e);
                } else if (e.MovingObject == false) {
                    Data.getGameObjectsList().add(e);
                }
            }
        }
    }

    public void setFlipper(Flipper flipperL, Flipper flipperR){}
}

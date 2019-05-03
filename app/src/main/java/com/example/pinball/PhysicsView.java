package com.example.pinball;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PhysicsView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawEngine DrawThread;
    private PhysicsEngine PhysicsThread;

    public PhysicsView(Context context){
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder);
    }

    public DrawEngine getEngine(){
        return DrawThread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawThread.setRunning(true);
        DrawThread.start();
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
    }

}

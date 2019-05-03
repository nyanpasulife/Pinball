package com.example.pinball;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PhysicsView extends SurfaceView implements SurfaceHolder.Callback{

    private PhysicsEngine EngineThread;

    public PhysicsView(Context context){
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        EngineThread = new PhysicsEngine(holder);
    }

    public PhysicsEngine getEngine(){
        return EngineThread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        EngineThread.setRunning(true);
        EngineThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;

        EngineThread.setRunning(false);
        while(retry){
            try{
                EngineThread.join();
                retry = false;
            }catch(InterruptedException e){}
        }
    }

}

package com.example.pinball.Engines.Network;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import com.example.pinball.Engines.DrawEngine;
import com.example.pinball.Engines.PhysicsView;
import com.example.pinball.GameCharacter.GameCharacter;

import java.net.Socket;
import java.util.ArrayList;

public class NetPhysicsView extends PhysicsView {
    NetPushEngine PushThread;
    NetListenEngine ListenTread;
    GameCharacter MyCharacter =null;
    GameCharacter EnemyCharacter =null;
    NetInputSet netInputSet;

    public NetPhysicsView(Context context){
        super(context);
    }
    public NetPhysicsView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void runThreads(boolean isPacemaker, Socket sock, ArrayList<GameCharacter> gameChars){
        netInputSet = new NetInputSet();

        startPushTread(sock, netInputSet);
        startDrawThread();
        startListenThread(isPacemaker, sock, netInputSet, PushThread.PushHandler);
        startPhysicsThread(isPacemaker, netInputSet, gameChars, PushThread.PushHandler);
    }

    private void startPhysicsThread(boolean isPaceMaker, NetInputSet netInputSet, ArrayList<GameCharacter> gameChars, Handler pushHandler) {

        PhysicsThread  = new NetPhysicsEngine(isPaceMaker, Data, netInputSet, gameChars, pushHandler);
        PhysicsThread.setRunning(true);
        PhysicsThread.start();
    }
    private void startDrawThread() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        DrawThread = new DrawEngine(holder, Data);
        DrawThread.setRunning(true);
        DrawThread.start();
    }
    private void startListenThread(boolean isPacemaker, Socket sock, NetInputSet netInputSet, Handler pushHandler) {
        if (isPacemaker == true) {
            ListenTread = new NetListenPaceEngine( this, sock, netInputSet,pushHandler);
        } else { //isServer == false
            ListenTread = new NetListenEngine(this, sock, netInputSet,pushHandler);
        }
        ListenTread.setRunning(true);
        ListenTread.start();
    }
    private void startPushTread(Socket sock, NetInputSet netInputSet) {
        PushThread = new NetPushEngine(this, sock, netInputSet);
        PushThread.setRunning(true);
        PushThread.start();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
       super.surfaceDestroyed(holder);
        closeTread(PushThread);
        closeTread(ListenTread);
    }

    public NetPhysicsEngine getNetPhysicsEngine() {
        return (NetPhysicsEngine) PhysicsThread;
    }

    long getFrame(){
        return getNetPhysicsEngine().FrameCount;
    }
}

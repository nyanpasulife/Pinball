package com.example.pinball.Engine.Network;


import android.os.Handler;

import com.example.pinball.Engine.CustomThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;

public class NetListenEngine extends Thread implements CustomThread {
    Handler PushHandler;
    boolean Run = false;

    NetPhysicsView NetView;
    NetInputSet Inputs;
    ObjectInputStream Reader;


    NetListenEngine(NetPhysicsView netView, ObjectInputStream reader, NetInputSet netInputSet,  Handler pushHandler){
        setInstanceVar(netView, reader, netInputSet, pushHandler);

    }

    private void setInstanceVar(NetPhysicsView netView, ObjectInputStream reader, NetInputSet netInputSet, Handler pushHandler) {
        NetView = netView;
        Inputs = netInputSet;
        PushHandler = pushHandler;
        Reader = reader;
    }

    public void setRunning(boolean run) {
        Run = run;
    }

    @Override
    public void run() {
        while (Run){
            listen();
        }
    }


    void listen() {
        LinkedHashMap listenData = getListenData();
        if (listenData!=null){
            synchronized (Inputs){
                stackToPaced(listenData);
            }
        }
    }

    private void stackToPaced(LinkedHashMap listenData) {
        Inputs.PacedInput = listenData;
    }

    protected LinkedHashMap getListenData() {
        LinkedHashMap listenData =null;
        try {
            listenData =(LinkedHashMap) Reader.readObject();
        } catch (ClassNotFoundException e) {} catch (IOException e) {} catch (NullPointerException e) {}
        return listenData;
    }

}

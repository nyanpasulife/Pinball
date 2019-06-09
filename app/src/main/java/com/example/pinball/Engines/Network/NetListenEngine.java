package com.example.pinball.Engines.Network;


import android.os.Handler;

import com.example.pinball.Engines.CustomThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedHashMap;

public class NetListenEngine extends Thread implements CustomThread {
    Handler PushHandler;
    boolean Run = false;

    NetPhysicsView NetView;
    Socket Sock;
    NetInputSet Inputs;
    ObjectInputStream Reader;


    NetListenEngine(NetPhysicsView netView, Socket sock, NetInputSet netInputSet,  Handler pushHandler){
        setInstanceVar(netView, sock, netInputSet, pushHandler);

    }

    private void setInstanceVar(NetPhysicsView netView, Socket sock, NetInputSet netInputSet,  Handler pushHandler) {
        NetView = netView;
        Sock = sock;
        Inputs = netInputSet;
        PushHandler = pushHandler;
    }

    public void setRunning(boolean run) {
        Run = run;
    }

    @Override
    public void run() {

        try {
            Reader = new ObjectInputStream(Sock.getInputStream());
        }catch (IOException e){} //TODO 예외처리 추가구현 필요.

        while (Run){
            listen();
        }
    }


    void listen() {
        LinkedHashMap listenData = getListenData();
        if (listenData!=null){
            synchronized (Inputs){
                stackToPaced(listenData)
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

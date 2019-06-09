package com.example.pinball.Engines.Network;

import android.os.Handler;
import android.util.Log;

import java.net.Socket;
import java.util.LinkedHashMap;

import static com.example.pinball.Engines.Network.NetPushEngine.PUSH_PACED_INPUT;

public class NetListenPaceEngine extends NetListenEngine {
    final static int CLIENT_NUM = 2; //TODO 접속자 수에 따라 유연하게 움직이는 서버 구현은 구현하기 매우 복잡하고 현 프로젝트에서는 필요 없으므로 상수값 2로 체크.

    NetListenPaceEngine(NetPhysicsView netView, Socket sock, NetInputSet netInputSet, Handler pushHandler) {
        super(netView, sock, netInputSet, pushHandler);
    }

    @Override
    void listen() {
        LinkedHashMap listenData = getListenData();
        if (listenData != null) {
            synchronized (Inputs) {
                Inputs.mergePacing(listenData);
            }
        }
    }

}

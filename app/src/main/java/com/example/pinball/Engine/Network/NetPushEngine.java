package com.example.pinball.Engine.Network;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.pinball.Engine.CustomThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;

public class NetPushEngine extends Thread implements CustomThread {
    boolean Run = false;
    NetPhysicsView NetView;
    NetInputSet Inputs;
    ObjectOutputStream Writer;
    Handler PushHandler;

    final static int PUSH_MY_INPUT = 0;
    final static int PUSH_PACED_INPUT = 1;


    NetPushEngine(NetPhysicsView netView, ObjectOutputStream writer, NetInputSet netInputSet) {
        setInstanceVar(netView, writer, netInputSet);
    }

    private void setInstanceVar(NetPhysicsView netView, ObjectOutputStream writer, NetInputSet netInputSet) {
        NetView = netView;
        Writer =writer;
        Inputs = netInputSet;
    }

    public void setRunning(boolean run) {
        Run = run;
    }

    @Override
    public void run() {
        Looper.prepare();
        PushHandler = new PHandler();
        /*try {
            Writer = new ObjectOutputStream(Sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Looper.loop();
    }

    private class PHandler extends Handler {
        public void handleMessage(Message msg) {
            Log.d("핸들러 작동 ", "작동핸들러->"+msg.what);
            synchronized (Inputs) {
                if(msg.what == PUSH_MY_INPUT){
                    pushMyInput();
                }
                else if(msg.what == PUSH_PACED_INPUT){
                    pushPacedInput();
                }
            }
        }

        private void pushPacedInput() {
            LinkedHashMap sendData = Inputs.IngInput;
            try {
                Writer.writeObject(sendData);
                Log.d("페이스메이커", "동기화 데이터 전송");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("페이스메이커", "동기화 데이터 전송 실패 ");
            }
        }

        private void pushMyInput() {
            LinkedHashMap sendData = Inputs.takeOutLocal();
            sendData.put("NPE", -3);
            try {
                Writer.writeObject(sendData);
                Log.d("클라이언트", "로컬데이터 전송");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("클라이언트", "로컬 데이터 전송 실패 ");
            }
        }
    }

    ;
}

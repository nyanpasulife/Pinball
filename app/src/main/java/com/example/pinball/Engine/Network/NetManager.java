package com.example.pinball.Engine.Network;

import android.renderscript.ScriptGroup;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;

public class NetManager {
    Socket Conn = null;
    public ObjectInputStream Reader;
    public ObjectOutputStream Writer;
    int EnemyCharId =1;

    public int getEnemyCharId(){
        return EnemyCharId;
    }

    public void makerServer() {
        new Thread(){
            @Override
            public void run(){
                try {
                    ServerSocket serverSocket = new ServerSocket(37000);
                    Conn = serverSocket.accept();
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                Writer = new ObjectOutputStream(Conn.getOutputStream());
                                Reader = new ObjectInputStream(Conn.getInputStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void excessServer(String ip) {
        final String ipString = ip;
        new Thread(){
            @Override
            public void run(){
                try {
                    Conn = new Socket(ipString,37000);
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                Writer = new ObjectOutputStream(Conn.getOutputStream());
                                Reader = new ObjectInputStream(Conn.getInputStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void sendMyChar(final int charId) {
        new Thread(){
            @Override
            public void run() {
                try {
                    Writer.writeObject(new LinkedHashMap().put("Char", charId));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public int getChar() {
        new Thread(){
            @Override
            public void run() {
                try {
                    LinkedHashMap a = (LinkedHashMap) Reader.readObject();
                    EnemyCharId = (int) a.get("Char");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return EnemyCharId;
    }



    public Socket getSocket(){
        return Conn;
    }
}

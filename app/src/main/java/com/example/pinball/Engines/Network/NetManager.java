package com.example.pinball.Engines.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetManager {
    Socket Conn = null;

    public void makerServer() {
        new Thread(){
            @Override
            public void run(){
                try {
                    ServerSocket serverSocket = new ServerSocket(37000);
                    Conn = serverSocket.accept();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public Socket getSocket(){
        return Conn;
    }
}

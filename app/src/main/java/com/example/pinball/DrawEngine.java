package com.example.pinball;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawEngine extends Thread{
    private boolean Run = false;
    private SurfaceHolder MSurfaceHolder;

    public DrawEngine(SurfaceHolder holder){
        MSurfaceHolder = holder;
    }

    @Override
    public void run(){
        while(Run){
            Canvas c = null;
            try{

            }
        }
    }
}

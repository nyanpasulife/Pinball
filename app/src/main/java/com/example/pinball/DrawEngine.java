package com.example.pinball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

//DrawEngine 은 SurfaceView 와 연동된 SurfaceHolder 객체를 받아와 화면에 게임 상태를 출력한다.
public class DrawEngine extends Thread{
    private boolean Run = false; //Run == true 여야 그리는 행동이 작동함.
    private SurfaceHolder MSurfaceHolder; // 이 객체를 이용해 SurefaceView에 그림을 그릴 수 있음.

     //생성자 : SurfaceHolder 를 받아와 자신의 인스턴스 변수로 저장한다.
    public DrawEngine(SurfaceHolder holder){
        MSurfaceHolder = holder;
    }

    @Override
    public void run(){
        int j =0;
        while(Run){
            Canvas c = null;
            try{
              doLockCanvas(c);
            }finally {
                if(c!=null){
                    doUnlockCanvas(c);
                }
            }
        }
    }
    public void setRunning(boolean b){
        Run = b;
    }

    void doLockCanvas(Canvas c){
        c = MSurfaceHolder.lockCanvas(null);
        c.drawColor(Color.BLACK);
        synchronized (MSurfaceHolder){

        }
    }

    void doUnlockCanvas(Canvas c){
        MSurfaceHolder.unlockCanvasAndPost(c);
    }

}

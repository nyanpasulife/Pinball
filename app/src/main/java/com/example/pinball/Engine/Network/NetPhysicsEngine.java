package com.example.pinball.Engine.Network;

import android.os.Handler;
import android.util.Log;

import com.example.pinball.Engine.GameData;
import com.example.pinball.Engine.PhysicsEngine;
import com.example.pinball.GameCharacter.GameCharacter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import static com.example.pinball.Engine.Network.NetPushEngine.PUSH_PACED_INPUT;

public class NetPhysicsEngine extends PhysicsEngine {
    final static int LATENCY = 100;
    boolean IsPaceMaker;

    int FrameRate = 60;
    Long RateRealTime;
    int FrameCount;

    boolean IsPaused = false;

    NetInputSet Inputs;

    Handler PushHandler;

    ArrayList<GameCharacter> GameChars = new ArrayList<>();

    public NetPhysicsEngine(boolean isPaceMaker, GameData gameData, NetInputSet netInputs, ArrayList<GameCharacter> gameChars, Handler pushHandler) {
        super(gameData);
        Inputs = netInputs;
        makeMilSecRate();
        GameChars = gameChars;
        PushHandler = pushHandler;
        IsPaceMaker = isPaceMaker;
    }
    private void makeMilSecRate() {
        double framePerS = (1.0d/FrameRate);
        RateRealTime = Math.round(1000 * (framePerS));
    }

    public void run() {
        makeDummyInputs();


        while (Run) {
            if (checkPaceComplete()) {
                Inputs.IngInput.put("Paced", 0);
                PushHandler.sendEmptyMessage(PUSH_PACED_INPUT);
                Inputs.PacedInput = Inputs.IngInput; //TODO 추후 스택으로 대체
                Inputs.IngInput = new LinkedHashMap<>();
                Log.d("페이스듣기엔진", "페이스완료19");
            }

                if (IsPaused == false && Inputs.PacedInput != null) {
                    long startTime = new Date().getTime();
                    synchronized (Data) {
                        objCalculate();
                    }
                        pace();
                    FrameCount++;
                    if (FrameCount == 100000) {
                        FrameCount = 0;
                    }
                    pause(startTime);
                }
        }
    }

    private boolean checkPaceComplete() {
        int checkmy = 1;
        int checkother = 1;
        try {
            checkmy = Inputs.IngInput.get("PE");
            checkother = Inputs.IngInput.get("NPE");
        } catch (NullPointerException e) {
        }
        if (checkmy + checkother == -3 * 2) {
            return true;
        }
        return false;
    }

    private void makeDummyInputs() {
        Inputs.PacedInput.put("Frame", FrameCount); //TODO  메세지가 전달이 안된다.
    }

    private void pace() {
        try {
            if (Inputs.getPacedInput().get("Frame") == FrameCount) {
                doPacedInput();
                if (isPaceMaker()) {
                    selfPacingInput();

                } else { //페이스 메이커가 아니면
                    shotPacingInput();
                }
            }
        }catch (NullPointerException e){e.printStackTrace();}
    }
    private boolean isPaceMaker() {
        return IsPaceMaker;
    }

    private void shotPacingInput() {
        Inputs.LocalInput.put("Frame", FrameCount + LATENCY);
        PushHandler.sendEmptyMessage(NetPushEngine.PUSH_MY_INPUT);
    }

    private void selfPacingInput() {
        Inputs.LocalInput.put("Frame", FrameCount + LATENCY);
        LinkedHashMap data = Inputs.takeOutLocal();
        data.put("PE", -3);
        Inputs.mergePacing(data);
    }

    private void doPacedInput() {
        LinkedHashMap<String, Integer> order = Inputs.takeOutPaced_nullBefore();
        Iterator<String> iter = order.keySet().iterator();
        while(iter.hasNext()){
            String key = ((String)iter.next());
            int value = order.get(key);
            checkOrder(key, value);
        }
    }

    private void checkOrder(String key, int value) {
        checkCharAct(key, value);
        checkPaused(key, value);
    }

    private void checkCharAct(String key, int value) {
        if(value == -1) {
            for (GameCharacter e : GameChars) {
                e.charAct(key);
            }
        }
    }

    private void checkPaused(String key, int value) {
        if(key == "cPause"){
            if(value ==-10){
                IsPaused =true;
            }
            else if(value ==-11){
                IsPaused =false;
            }
        }
    }

    private void pause(long startTime) {
        long endTime = new Date().getTime();
        long progressedTime = endTime - startTime;
        long waitTime = RateRealTime - progressedTime;
        if(waitTime >0){
            try {
                Thread.sleep(waitTime);
            }catch (Exception e){}
        }
    }


}

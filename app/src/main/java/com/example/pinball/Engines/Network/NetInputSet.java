package com.example.pinball.Engines.Network;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class NetInputSet {
    LinkedHashMap<String,Integer> LocalInput = new LinkedHashMap<>();
    LinkedHashMap<String,Integer> IngInput= new LinkedHashMap<>();
    LinkedHashMap<String,Integer> PacedInput= new LinkedHashMap<>();


    public LinkedHashMap takeOutLocal() {
        backupLocal(LocalInput);
        LinkedHashMap tempPointer = LocalInput;
        LocalInput = new LinkedHashMap();
        return tempPointer;
    }

    private void backupLocal(LinkedHashMap localInput) { //TODO 구현 필요
    }

    public LinkedHashMap takeOutPacing() {
        backupPacing(IngInput);
        LinkedHashMap tempPointer = IngInput;
        IngInput = new LinkedHashMap();
        return tempPointer;
    }

    private void backupPacing(LinkedHashMap<String, Integer> ingInput) {
    }


    public LinkedHashMap takeOutPaced_nullBefore() {
        backupPaced(PacedInput);
        LinkedHashMap tempPointer = PacedInput;
        PacedInput = null;
        return tempPointer;
    }

    private void backupPaced(LinkedHashMap<String, Integer> pacedInput) {
    }

    public void mergePacing(LinkedHashMap addList){
        Iterator<String> iter = addList.keySet().iterator();
        while(iter.hasNext()){
            String key = ((String)iter.next());
            int value = (int) addList.get(key);
            IngInput.put(key,value);
        }
    }

    public LinkedHashMap<String,Integer> getPacedInput(){
        return PacedInput;
    }

}

package com.example.pinball;
import com.example.pinball.Engine.Vector2D;

import org.junit.Test;

import java.util.LinkedHashMap;

public class HoeTest {
    @Test
    public void Test1() {
    }


    @Test
    public void t3(){
        Vector2D a = new Vector2D(2,1);
        a = a.rotate(0.1);
        int haha =1;
    }


    @Test
    public void t5(){
        LinkedHashMap a = new LinkedHashMap();
        a.put("냥", 454);
        System.out.print(a.get("냥"));
    }
}

package com.example.pinball.GameCharacter;

import android.content.res.Resources;

public class CharacterMagician extends GameCharacter {

    private int life = 3;

    public CharacterMagician(Resources res) {
        super(res);
    }

    public int getLife(){
        return life;
    }
    public void skill(){
        //TODO
    }
}

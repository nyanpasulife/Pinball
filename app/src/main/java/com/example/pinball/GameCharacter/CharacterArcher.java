package com.example.pinball.GameCharacter;

import android.content.res.Resources;

public class CharacterArcher extends GameCharacter {

    private int life = 4;

    public CharacterArcher(Resources res) {
        super(res);
    }

    public int getLife(){
        return life;
    }
    public void skill(){
        //TODO
    }
}

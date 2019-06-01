package com.example.pinball.GameCharacter;

import android.content.res.Resources;

public class CharacterPriest extends GameCharacter {

    private int life = 2;

    public CharacterPriest(Resources res) {
        super(res);
    }

    public int getLife(){
        return life;
    }
    public void skill(){
        //TODO
    }
}

package com.example.pinball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.pinball.GameCharacter.CharacterArcher;
import com.example.pinball.GameCharacter.CharacterMagician;
import com.example.pinball.GameCharacter.CharacterPriest;
import com.example.pinball.GameCharacter.CharacterWarrior;
import com.example.pinball.GameCharacter.GameCharacter;
import com.example.pinball.GameCharacter.TestCharacter;
import com.example.pinball.GameObjectCodes.DefaultBlock;
import com.example.pinball.GameObjectCodes.Flipper;
import com.example.pinball.Physics.PhysicsObjectInterface;
import com.example.pinball.Physics.PhysicsView;
import com.example.pinball.Physics.Vector2D;

import java.util.ArrayList;

/**     GamePlayActivity 위에 올릴 Game 화면    **/

public class GameView extends PhysicsView {
    private GameCharacter player, otherOne;
    ArrayList<PhysicsObjectInterface> gameObjectSet = new ArrayList<>();

    int playerCharacterId;
    int otherCharacterId;

    private Flipper left, right;

    public GameView(Context context){
        super(context);
    }

    public GameView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void pushObject(ArrayList<PhysicsObjectInterface> objectSet){
        gameObjectSet = objectSet;
    }

    public void setCharacterId(int playerCharacter, int otherCharacter) {
        playerCharacterId = playerCharacter;
        otherCharacterId = otherCharacter;
        viewSetting();
    }

    private void viewSetting(){
        switch(playerCharacterId){
            case 1:
                player = new CharacterWarrior(getResources());
                break;
            case 2:
                player = new CharacterMagician(getResources());
                break;
            case 3:
                player = new CharacterArcher(getResources());
                break;
            case 4:
                player = new CharacterPriest(getResources());
                break;
            default:
                Log.d("debug", "select error");
        }
        switch(otherCharacterId){
            case 1:
                otherOne = new CharacterWarrior(getResources());
                break;
            case 2:
                otherOne = new CharacterMagician(getResources());
                break;
            case 3:
                otherOne = new CharacterArcher(getResources());
                break;
            case 4:
                otherOne = new CharacterPriest(getResources());
                break;
            default:
                Log.d("debug", "select error");
        }

        player.setCharOnView(0, this);
        otherOne.setCharOnView(1, this);
    }

    public void setFlipper(Flipper flipperL, Flipper flipperR){
        left = flipperL;
        right = flipperR;
    }

    public Flipper getLeftFlipper() {
        return left;
    }

    public Flipper getRightFlipper(){
        return right;
    }
}
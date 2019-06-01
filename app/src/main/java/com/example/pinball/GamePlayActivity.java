package com.example.pinball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**     게임플레이 activity. GameView를 Inflate    **/

public class GamePlayActivity extends AppCompatActivity {

    int playerCharacter, otherCharacter;
    GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "create success");
        setContentView(R.layout.game_play);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        playerCharacter = bundle.getInt("character");
        //otherCharacter = getOtherCharacter();     //TODO: Network
        otherCharacter = 1;
        gv = findViewById(R.id.game_view);
        gv.setCharacterId(playerCharacter, otherCharacter);
    }
}

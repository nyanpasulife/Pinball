package com.example.pinball;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;

/**     캐릭터 선택창 activity     **/

public class CharacterSelectActivity extends AppCompatActivity {

    //TODO: Timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_select);
    }

    public void onCharacterClicked(View view){
        Intent intent = new Intent(CharacterSelectActivity.this, GamePlayActivity.class);
        int flag = 0;       //nothing selected
        switch(view.getId()) {
            case R.id.one:
                flag = 1;
                break;
            case R.id.two:
                flag = 2;
                break;
            case R.id.three:
                flag = 3;
                break;
            case R.id.four:
                flag = 4;
                break;
        }
        intent.putExtra("character", flag);
        startActivity(intent);
    }
}

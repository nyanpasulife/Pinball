package com.example.pinball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinball.Engine.Network.NetManager;
import com.example.pinball.GameCharacter.GameCharacter;
import com.example.pinball.GameObjectCodes.Flipper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

/**     캐릭터 선택창 activity     **/

public class GamePlayActivity extends AppCompatActivity {
    private NetManager netManager = new NetManager();
    private boolean isPaceMaker;

    int playerCharacter, otherCharacter;
    GameView gv;

    private View 	decorView;
    private int	uiOption;
    Flipper left, right;
    private int playerLife, otherLife;

    private TextView playerLifeText, otherLifeText;

    public Handler lifeTextHandler = new Handler(){
        public void handleMessage(Message message){
            updateLife(message.arg1, (GameCharacter)message.obj);
            Log.d("tag", Integer.toString(message.arg1));
        }
    };

    public void updateLife(int newLife, GameCharacter character){
        if(character == gv.player)
            playerLifeText.setText("LIFE: " + Integer.toString(newLife));
        else if(character == gv.otherOne)
            otherLifeText.setText("LIFE: " + Integer.toString(newLife));
    }

    //TODO: Timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_make);
    }

    public void onCharacterClicked(View view) throws InterruptedException {
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
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ConstraintLayout GAMELayout = (ConstraintLayout) inflater.inflate(R.layout.game_play, null);
        netManager.sendMyChar(flag);
        Thread.sleep(100);
        playerCharacter= flag;

        otherCharacter = netManager.getChar();;
        setContentView(GAMELayout);
        setGameInside();
    }

    public void makeServer(View view) {
        TextView waitMessage = findViewById(R.id.conn_wait_message);
        netManager.makerServer();
        waitMessage.setVisibility(View.VISIBLE);
        isPaceMaker = true;
    }

    public void excessServer(View view) {
        EditText editText = findViewById(R.id.text_ip);
        String ipAddress = editText.getText().toString();
        netManager.excessServer(ipAddress);
        isPaceMaker = false;
    }

    public void net_char_select(View view) {
        if (netManager.getSocket() == null) {
            Toast.makeText(getApplicationContext(), "네트워크가 연결되지 않았습니다.", Toast.LENGTH_LONG).show();
        } else { //소켓이 연결 됐으면t
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ConstraintLayout charSlecLayout = (ConstraintLayout) inflater.inflate(R.layout.character_select, null);
            setContentView(charSlecLayout);
        }
    }

    private void setGameInside() {
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

        //otherCharacter = getOtherCharacter();     //TODO: Network
        otherCharacter = 1;
        gv = findViewById(R.id.game_view);
        gv.setActivity(this);
        gv.setCharacterId(playerCharacter, otherCharacter);
        ArrayList<GameCharacter> temp = new ArrayList<>();
        temp.add(gv.player); temp.add(gv.otherOne);
        gv.runThreads(isPaceMaker, netManager.Writer, netManager.Reader, temp);

        left = gv.getLeftFlipper();
        right = gv.getRightFlipper();

        playerLifeText = findViewById(R.id.playerLife);
        otherLifeText = findViewById(R.id.otherLife);

        playerLife = gv.player.getLife();
        otherLife = gv.otherOne.getLife();
        playerLifeText.setText("LIFE: " + Integer.toString(playerLife));
        otherLifeText.setText("LIFE: " + Integer.toString(otherLife));

        Button leftButton = findViewById(R.id.left);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.powered();
            }
        });

        Button rightButton  = findViewById(R.id.right);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right.powered();
            }
        });
    }

    public void check_ip(View view) {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        Toast.makeText(getApplicationContext(),inetAddress.getHostAddress().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }
}

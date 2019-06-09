package com.example.pinball;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinball.Engines.Network.NetManager;
import com.example.pinball.Engines.Network.NetPhysicsView;
import com.example.pinball.GameCharacter.GameCharacter;
import com.example.pinball.GameCharacter.TestCharacter;
import com.example.pinball.GameObjectCodes.Flipper;
import com.example.pinball.Engines.PhysicsView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {   //127.0.0.1 자신 아이피

    Flipper flipper1;
    Flipper flipper2;

    private int screenWidth, screenHeight;
    private NetManager netManager = new NetManager();
    private boolean isPaceMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // get screen size(pixel)
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ConstraintLayout netLayout = (ConstraintLayout) inflater.inflate(R.layout.network_make, null);
        setContentView(R.layout.network_make);
    }

    void setGameInside() {
        NetPhysicsView gameView = findViewById(R.id.game_inside);
        TestCharacter hi = new TestCharacter(getResources());
        hi.setCharOnView(0, gameView);
        ArrayList<GameCharacter> GCList = new ArrayList<>();
        GCList.add(hi);
        gameView.runThreads(isPaceMaker,netManager.getSocket(), GCList);
    }

    public void onclickLeft(View view) {
        flipper1.powered();
    }

    public void onclickRight(View view) {
        flipper2.powered();
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

    public void net_game_start(View view) {
        if (netManager.getSocket() == null) {
            Toast.makeText(getApplicationContext(), "네트워크가 연결되지 않았습니다.", Toast.LENGTH_LONG);
        } else { //소켓이 연결 됐으면
            setContentView(R.layout.activity_main);
            setGameInside();
        }
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

package com.example.pinball.pinball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pinball.Engine.Network.NetManager;
import com.example.pinball.R;

public class NetworkActivity extends AppCompatActivity{
    private NetManager netManager = new NetManager();
    private boolean isPaceMaker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_make);
    }

}

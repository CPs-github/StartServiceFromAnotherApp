package com.cp.anotherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.startservicefromanotherapp.IAppServiceRemoteBinder;

public class MainActivity extends AppCompatActivity {

    private String tag = "AnotherAppLogInfo";
    private TextView txthowInfo;
    private EditText edtSetData;

    private Button btnConnectService;
    private Button btnDisConnectService;
    private Button btnBindService;
    private Button btnDisBindService;
    private Button btnSetData;

    private Intent startServiceIntent;

    private String serviceAction = "com.cp.startservicefromanotherapp.AppService";
    private String servicePackage = "com.cp.startservicefromanotherapp";


    private IAppServiceRemoteBinder serviceBinder = null;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            serviceBinder = IAppServiceRemoteBinder.Stub.asInterface(iBinder);
            Log.i(tag,"binded service");
            System.out.println(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSetData = findViewById(R.id.edt_set_data);

        btnConnectService = findViewById(R.id.btn_connect_service);
        btnDisConnectService = findViewById(R.id.btn_disconnect_service);
        btnBindService = findViewById(R.id.btn_bind_service);
        btnDisBindService = findViewById(R.id.btn_disbind_service);
        btnSetData = findViewById(R.id.btn_set_data);

        startServiceIntent = new Intent();
        startServiceIntent.setComponent(new ComponentName(servicePackage,serviceAction));

        btnConnectService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(startServiceIntent);
            }
        });
        btnDisConnectService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(startServiceIntent);
            }
        });
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(startServiceIntent,connection, Context.BIND_AUTO_CREATE);
            }
        });
        btnDisBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
                serviceBinder = null;
            }
        });

        btnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (serviceBinder!=null){
                        serviceBinder.setData(edtSetData.getText().toString());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }



}
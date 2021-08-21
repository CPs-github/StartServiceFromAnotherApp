package com.cp.startservicefromanotherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AppService extends Service {

    private String info = "AppService";
    private String data = "";
    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        Log.i(info,"started app service");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(info, "destroy app service");
        super.onDestroy();
    }

    class MyBinder extends IAppServiceRemoteBinder.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void setData(String data) throws RemoteException {
            AppService.this.data = data;

            Log.i(AppService.this.info+" remote",data);
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }
}
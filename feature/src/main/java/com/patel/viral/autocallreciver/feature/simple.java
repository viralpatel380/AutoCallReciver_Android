package com.patel.viral.autocallreciver.feature;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by rafar on 12/10/2016.
 */

public class simple extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override

    public void onCreate() {
        Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();
        ComponentName receiver=new ComponentName(getApplicationContext() ,CallReceiver.class);
        PackageManager p = getApplicationContext().getPackageManager();
        p.setComponentEnabledSetting(receiver,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        ComponentName receiver=new ComponentName(getApplicationContext() ,CallReceiver.class);
        PackageManager p = getApplicationContext().getPackageManager();
        p.setComponentEnabledSetting(receiver,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);

    }
}
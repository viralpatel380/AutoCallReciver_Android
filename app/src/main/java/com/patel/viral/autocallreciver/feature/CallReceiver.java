package com.patel.viral.autocallreciver.feature;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by Viral on 1/5/2018
 */

public class CallReceiver extends BroadcastReceiver {

    private static final String TAG = "Phone call";
    String num;
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
            num = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            Toast.makeText(context, "caller is "+num, Toast.LENGTH_LONG).show();
            if (num.equals(MainActivity.monNum)) {
                answerPhoneHeadsethook(context, intent);
            }

        }else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE) || intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){

            Toast.makeText(context, "accepter", Toast.LENGTH_LONG).show();
            try {
                synchronized(this) {
                    this.wait(10000);
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            return;
        }

    }

    public void answerPhoneHeadsethook(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            Log.d(TAG, "appel en provenence de " + num);
            Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
            buttonUp.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
            try {
                context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");
            }
            catch (Exception e) {
            }

            Intent headSetUnPluggedintent = new Intent(Intent.ACTION_HEADSET_PLUG);
            headSetUnPluggedintent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY);
            headSetUnPluggedintent.putExtra("state", 1); // 0 = unplugged  1 = Headset with microphone 2 = Headset without microphone
            headSetUnPluggedintent.putExtra("name", "Headset");
            // TODO: Should we require a permission?
            try {
                context.sendOrderedBroadcast(headSetUnPluggedintent, null);
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();

            }
            Log.d(TAG, "call: " + num);
        }
        Log.d(TAG, "accepter l'appel avec headsethook");
    }


}
package es.pue.eventos.presentationlayer.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by android-ed1 on 23/05/2016.
 */
public class LlamadasBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("LlamadasBCReceiver","Received Broadcast!");
    }
}

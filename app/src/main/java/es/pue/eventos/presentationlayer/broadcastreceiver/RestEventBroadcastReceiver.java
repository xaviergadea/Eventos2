package es.pue.eventos.presentationlayer.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by android-ed1 on 23/05/2016.
 */
public class RestEventBroadcastReceiver extends BroadcastReceiver {

    public static String REST_RESULT_EXTRA = "REST_RESULT_EXTRA";

    @Override
    public void onReceive(Context context, Intent intent) {

        Boolean result=intent.getBooleanExtra(REST_RESULT_EXTRA,false);
        Log.i("In Rest Receiver","Result i "+result);

        if (result) {
            Toast.makeText(context, "Guardat correctament",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Error al guardar",Toast.LENGTH_LONG).show();
        }
    }
}

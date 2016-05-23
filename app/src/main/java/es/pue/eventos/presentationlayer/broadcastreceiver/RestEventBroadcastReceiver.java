package es.pue.eventos.presentationlayer.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import es.pue.eventos.R;

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
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification notification=new Notification(R.drawable.ic_cast_dark,"Datos sincronizados correctamente",System.currentTimeMillis());

        /*PendingIntent launchIntent=PendingIntent.getActivity(context,0,new Intent(),0);*/
        int notificationId=1;
        notificationManager.notify(notificationId,notification);

    }
}

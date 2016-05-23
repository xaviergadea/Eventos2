package es.pue.eventos.presentationlayer.listeners;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by android-ed1 on 23/05/2016.
 */
public class LlamadasListener extends PhoneStateListener {
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        switch (state){
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i("LlamadasListener","Descolagado");
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i("LlamadasListener","Sonando: " + incomingNumber);
                break;

        }
    }
}

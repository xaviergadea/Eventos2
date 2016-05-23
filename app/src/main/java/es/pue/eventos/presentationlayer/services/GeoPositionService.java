package es.pue.eventos.presentationlayer.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import es.pue.eventos.presentationlayer.androidextends.application.PueAndroidApplication;
import es.pue.eventos.presentationlayer.listeners.GeoPositionListener;

/**
 * Created by android-ed1 on 11/05/2016.
 */

@SuppressWarnings("MissingPermission")
public class GeoPositionService extends Service {

    private LocationManager locationManager;
    private GeoPositionListener geoPositionListener;

    @Override
    public void onCreate() {
        super.onCreate();


        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        geoPositionListener= new GeoPositionListener(
                ((PueAndroidApplication)getApplication()).getAsistenciaActual());

        iniciarPosicionamiento();
    }



    private void iniciarPosicionamiento(){

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0,
                geoPositionListener);
    }

    @Override
    public void onDestroy() {

        locationManager.removeUpdates(geoPositionListener);

        super.onDestroy();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

package es.pue.eventos.presentationlayer.listeners;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

import es.pue.eventos.model.businesslayer.entities.Asistencia;
import es.pue.eventos.model.businesslayer.entities.Posicion;

/**
 * Created by android-ed1 on 11/05/2016.
 */
public class GeoPositionListener implements LocationListener {

    private Asistencia asistencia;

    public GeoPositionListener(Asistencia asistencia){
        this.asistencia=asistencia;
    }

    @Override
    public void onLocationChanged(Location location) {

        Posicion posicion = new Posicion();
        posicion.setLatitud(location.getLatitude());
        posicion.setLongitud(location.getLongitude());
        posicion.setPrecision((int)location.getAccuracy());
        posicion.setFecha(new Date());

        asistencia.getRecorrido().add(posicion);

        Log.d("Nueva Posicion!!",String.valueOf(asistencia.getRecorrido().size()));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

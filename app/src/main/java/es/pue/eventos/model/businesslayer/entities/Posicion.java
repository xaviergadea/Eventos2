package es.pue.eventos.model.businesslayer.entities;

import java.util.Date;

/**
 * Created by android-ed1 on 27/04/2016.
 */
public class Posicion {

    private double latitud;
    private double longitud;
    private int precision;
    private Date fecha;

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Posicion(){
        latitud=0;
        longitud=0;
        precision=0;
        fecha=null;
    }
}

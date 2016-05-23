package es.pue.eventos.presentationlayer.androidextends.application;

import android.app.Application;
import android.app.Service;

import java.util.ArrayList;
import java.util.List;

import es.pue.eventos.model.businesslayer.entities.Asistencia;
import es.pue.eventos.model.businesslayer.entities.Evento;
import es.pue.eventos.model.servicelayer.manager.ServiceManager;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class PueAndroidApplication extends Application {


    private ServiceManager serviceManager;

    private Asistencia asistenciaActual;

    private Evento evento;

    private List<Evento> eventos;

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Asistencia getAsistenciaActual() {
        return asistenciaActual;
    }

    public void setAsistenciaActual(Asistencia asistenciaActual) {
        this.asistenciaActual = asistenciaActual;
    }

    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    public PueAndroidApplication(){
        super();
        eventos=new ArrayList<Evento>();
        serviceManager=new ServiceManager(this);
        evento=null;
        asistenciaActual=null;

    }

}


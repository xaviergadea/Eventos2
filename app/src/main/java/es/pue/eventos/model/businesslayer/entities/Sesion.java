package es.pue.eventos.model.businesslayer.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.pue.eventos.model.businesslayer.entities.base.EntityBase;

/**
 * Created by android-ed1 on 27/04/2016.
 */
public class Sesion extends EntityBase{

    private Date fechaInicio;
    private Date fechaFin;
    private List<Asistencia> asistencias;

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public Sesion(){
        fechaInicio=null;
        fechaFin=null;
        asistencias= new ArrayList<Asistencia>();
    }
}

package es.pue.eventos.model.businesslayer.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.pue.eventos.model.businesslayer.entities.base.EntityBase;

/**
 * Created by android-ed1 on 27/04/2016.
 */
public class Asistencia extends EntityBase {

    private Date fechaInicio;
    private Date fechaFin;
    private Participante participante;
    private List<Posicion> recorrido;

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

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public List<Posicion> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<Posicion> recorrido) {
        this.recorrido = recorrido;
    }

    public Asistencia(){
        fechaInicio=null;
        fechaFin=null;
        participante=null;
        recorrido=new ArrayList<Posicion>();
    }

}

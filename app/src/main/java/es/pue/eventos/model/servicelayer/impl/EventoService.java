package es.pue.eventos.model.servicelayer.impl;

import android.content.pm.PackageInstaller;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.pue.eventos.model.businesslayer.entities.Asistencia;
import es.pue.eventos.model.businesslayer.entities.Evento;
import es.pue.eventos.model.businesslayer.entities.Participante;
import es.pue.eventos.model.businesslayer.entities.Sesion;
import es.pue.eventos.model.persistencelayer.impl.flatfile.manager.FlatFilePersistenceManager;
import es.pue.eventos.model.persistencelayer.impl.rest.manager.RestPersistenceManager;
import es.pue.eventos.model.persistencelayer.impl.sql.manager.SqlPersistenceManager;
import es.pue.eventos.model.servicelayer.api.IEventoService;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class EventoService implements IEventoService {

    //Referencias a los managers de persistencia
    //Que inyecta el servicemanager

    private FlatFilePersistenceManager flatFilePersistenceManager;
    private RestPersistenceManager restPersistenceManager;
    private SqlPersistenceManager sqlPersistenceManager;

    public EventoService(
            FlatFilePersistenceManager flatFilePersistenceManager,
            RestPersistenceManager restPersistenceManager,
            SqlPersistenceManager sqlPersistenceManager
    ){
        this.flatFilePersistenceManager=flatFilePersistenceManager;
        this.sqlPersistenceManager=sqlPersistenceManager;
        this.restPersistenceManager=restPersistenceManager;

    }

    @Override
    public Evento getEventoByDorsal(String dorsal) {
        return restPersistenceManager
        //return flatFilePersistenceManager
        .getEventoDAO().getEventoByDorsal(dorsal);

    }

    @Override
    public Asistencia addCurrentAsistenciaToEvent(Evento evento) {
        Asistencia asistencia=null;
        for(Sesion sesion : evento.getSesiones())
         {
           if(sesion.getFechaInicio().before(new Date()) &&
                   sesion.getFechaFin().after(new Date()))
             {
                 asistencia= new Asistencia();
                 asistencia.setFechaInicio(new Date());
                 asistencia.setParticipante(evento.getInscritos().get(0));
                 sesion.getAsistencias().add(asistencia);
                 break;


             }
         }

        return asistencia;
    }


    @Override
    public List<Evento> createInitialLocalEventos() throws ParseException {

        List<Evento> eventos = new ArrayList<Evento>();
        Evento evento = new Evento();
        eventos.add(evento);

        evento.setNombre("Evento1");
        evento.setDescripcion("Descripcion1");
        evento.setNumeroPlazas(50);

        List<Participante> corredores = new ArrayList<Participante>();

        Participante corredor1 = new Participante();
        corredor1.setNombre("corredor1");
        corredor1.setDorsal("00001");
        corredor1.setEmail("corredor1@eventos.com");
        corredores.add(corredor1);

        Participante corredor2 = new Participante();
        corredor2.setNombre("corredor2");
        corredor2.setDorsal("00002");
        corredor2.setEmail("corredor2@eventos.com");
        corredores.add(corredor2);

        Participante corredor3 = new Participante();
        corredor3.setNombre("corredor3");
        corredor3.setDorsal("00003");
        corredor3.setEmail("corredor3@eventos.com");
        corredores.add(corredor3);

        Participante corredor4 = new Participante();
        corredor4.setNombre("corredor4");
        corredor4.setDorsal("00004");
        corredor4.setEmail("corredor4@eventos.com");
        corredores.add(corredor4);

        Participante corredor5 = new Participante();
        corredor5.setNombre("corredor5");
        corredor5.setDorsal("00005");
        corredor5.setEmail("corredor5@eventos.com");
        corredores.add(corredor5);

        Participante corredor6 = new Participante();
        corredor6.setNombre("corredor6");
        corredor6.setDorsal("00006");
        corredor6.setEmail("corredor6@eventos.com");
        corredores.add(corredor6);

        evento.setInscritos(corredores);


        Sesion sesion1 = new Sesion();
        sesion1.setFechaInicio(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("02/05/2016 15:30:00"));
        sesion1.setFechaFin(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("02/05/2017 20:30:00"));
        evento.getSesiones().add(sesion1);

        Sesion sesion2 = new Sesion();
        sesion2.setFechaInicio(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("02/01/2017 10:00:00"));
        sesion2.setFechaFin(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("02/01/2017 18:00:00"));
        evento.getSesiones().add(sesion2);

        Evento evento2 = new Evento();
        eventos.add(evento2);

        evento2.setNombre("evento21");
        evento2.setDescripcion("Descripcion1");
        evento2.setNumeroPlazas(50);

        List<Participante> corredores2 = new ArrayList<Participante>();

        Participante corredor21 = new Participante();
        corredor21.setNombre("corredor21");
        corredor21.setDorsal("00021");
        corredor21.setEmail("corredor21@evento2s.com");
        corredores2.add(corredor21);

        Participante corredor22 = new Participante();
        corredor22.setNombre("corredor22");
        corredor22.setDorsal("00022");
        corredor22.setEmail("corredor22@evento2s.com");
        corredores2.add(corredor22);

        Participante corredor23 = new Participante();
        corredor23.setNombre("corredor23");
        corredor23.setDorsal("00023");
        corredor23.setEmail("corredor23@evento2s.com");
        corredores2.add(corredor23);

        Participante corredor24 = new Participante();
        corredor24.setNombre("corredor24");
        corredor24.setDorsal("00024");
        corredor24.setEmail("corredor24@evento2s.com");
        corredores2.add(corredor24);

        Participante corredor25 = new Participante();
        corredor25.setNombre("corredor25");
        corredor25.setDorsal("00005");
        corredor25.setEmail("corredor25@evento2s.com");
        corredores2.add(corredor25);

        Participante corredor26 = new Participante();
        corredor26.setNombre("corredor26");
        corredor26.setDorsal("00026");
        corredor26.setEmail("corredor26@evento2s.com");
        corredores2.add(corredor26);

        evento2.setInscritos(corredores2);

        Sesion sesion21 = new Sesion();
        sesion21.setFechaInicio(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("01/01/2018 10:00:00"));
        sesion21.setFechaFin(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("01/01/2018 18:00:00"));
        evento2.getSesiones().add(sesion21);

        Sesion sesion22 = new Sesion();
        sesion22.setFechaInicio(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("02/01/2018 10:00:00"));
        sesion22.setFechaFin(new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("02/01/2018 18:00:00"));
        evento2.getSesiones().add(sesion22);

        //To DO Persistir estos eventos en una tecnología de persistencia
        try {
            flatFilePersistenceManager.getEventoDAO().eventosSave(eventos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            sqlPersistenceManager.getEventoDAO().eventosSave(eventos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            restPersistenceManager.getEventoDAO().eventosSave(eventos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return eventos;

    }

}

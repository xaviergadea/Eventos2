package es.pue.eventos.model.persistencelayer.impl.rest.manager;

import es.pue.eventos.model.persistencelayer.api.IEventoDAO;
import es.pue.eventos.model.persistencelayer.impl.rest.daos.EventoDao;
import es.pue.eventos.model.persistencelayer.manager.PersistenceManager;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class RestPersistenceManager extends PersistenceManager {
    EventoDao eventoDao;
    @Override
    public IEventoDAO getEventoDAO() {
        if(eventoDao==null){
            eventoDao=new EventoDao();
        }
        return eventoDao;
    }
}

package es.pue.eventos.model.persistencelayer.impl.rest.manager;

import android.content.Context;

import es.pue.eventos.model.persistencelayer.api.IEventoDAO;
import es.pue.eventos.model.persistencelayer.impl.rest.daos.EventoDao;
import es.pue.eventos.model.persistencelayer.manager.PersistenceManager;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class RestPersistenceManager extends PersistenceManager {
    Context context;
    public RestPersistenceManager(Context context){
        this.context=context;
    }

    EventoDao eventoDao;
    @Override
    public IEventoDAO getEventoDAO() {
        if(eventoDao==null){
            eventoDao=new EventoDao(context);
        }
        return eventoDao;
    }
}

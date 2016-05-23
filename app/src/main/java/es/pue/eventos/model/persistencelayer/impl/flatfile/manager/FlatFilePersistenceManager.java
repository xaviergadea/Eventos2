package es.pue.eventos.model.persistencelayer.impl.flatfile.manager;

import android.content.Context;

import es.pue.eventos.model.persistencelayer.api.IEventoDAO;
import es.pue.eventos.model.persistencelayer.impl.flatfile.daos.EventoDAO;
import es.pue.eventos.model.persistencelayer.manager.PersistenceManager;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class FlatFilePersistenceManager extends PersistenceManager {

    private Context context;

    private IEventoDAO eventoDAO;

    @Override
    public IEventoDAO getEventoDAO() {

        if(eventoDAO==null){
            eventoDAO=new EventoDAO(context);
        }
        return  eventoDAO;
    }

    public FlatFilePersistenceManager(Context context){
        this.context=context;
    }

}

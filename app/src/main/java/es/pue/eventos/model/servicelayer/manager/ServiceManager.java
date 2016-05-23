package es.pue.eventos.model.servicelayer.manager;

import android.content.Context;

import es.pue.eventos.model.persistencelayer.impl.flatfile.manager.FlatFilePersistenceManager;
import es.pue.eventos.model.persistencelayer.impl.rest.manager.RestPersistenceManager;
import es.pue.eventos.model.persistencelayer.impl.sql.manager.SqlPersistenceManager;
import es.pue.eventos.model.persistencelayer.manager.PersistenceManager;
import es.pue.eventos.model.servicelayer.api.IEventoService;
import es.pue.eventos.model.servicelayer.impl.EventoService;
import es.pue.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class ServiceManager {

    //Singletones de gestores de persistencia
    private FlatFilePersistenceManager flatFilePersistenceManager;
    private RestPersistenceManager restPersistenceManager;
    private SqlPersistenceManager sqlPersistenceManager;

    public ServiceManager(Context context)
    {
        this.context=context;
        flatFilePersistenceManager=(FlatFilePersistenceManager)
                PersistenceManager.getPersistenceManager(
                        AppUtils.PersistenceTechnologies.FLAT_FILE,context);

        restPersistenceManager=(RestPersistenceManager)
                PersistenceManager.getPersistenceManager(
                        AppUtils.PersistenceTechnologies.REST,context);

        sqlPersistenceManager= (SqlPersistenceManager)
                PersistenceManager.getPersistenceManager(
                        AppUtils.PersistenceTechnologies.SQL,context
                );
    }

    private Context context;

    //Servicios
    private IEventoService eventoService;


    //Inicialización del gestor de servicios
    public IEventoService getEventoService(){
        if(eventoService==null){
            eventoService=new EventoService(
                    flatFilePersistenceManager,
                    restPersistenceManager,
                    sqlPersistenceManager
            );
        }
        return eventoService;
    }



}

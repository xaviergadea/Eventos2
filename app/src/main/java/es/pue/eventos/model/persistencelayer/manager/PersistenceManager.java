package es.pue.eventos.model.persistencelayer.manager;

import android.content.Context;

import java.io.BufferedReader;

import es.pue.eventos.model.persistencelayer.api.IEventoDAO;
import es.pue.eventos.model.persistencelayer.impl.flatfile.manager.FlatFilePersistenceManager;
import es.pue.eventos.model.persistencelayer.impl.rest.manager.RestPersistenceManager;
import es.pue.eventos.model.persistencelayer.impl.sql.manager.SqlPersistenceManager;
import es.pue.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public abstract class PersistenceManager {

    public abstract IEventoDAO getEventoDAO();


    public static  PersistenceManager getPersistenceManager(
            AppUtils.PersistenceTechnologies persistenceTechnologies,Context context
    )
    {
        PersistenceManager persistenceManager=null;

        switch (persistenceTechnologies)
        {
            case FLAT_FILE:
                persistenceManager  = new FlatFilePersistenceManager(context);
                break;
            case REST:
                persistenceManager=new RestPersistenceManager(context);
                break;

            case SQL:
                persistenceManager=new SqlPersistenceManager(context);
                break;
        }

        return persistenceManager;
    }


}

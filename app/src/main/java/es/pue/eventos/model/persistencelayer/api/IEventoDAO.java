package es.pue.eventos.model.persistencelayer.api;

import org.json.JSONException;

import java.util.List;

import es.pue.eventos.model.businesslayer.entities.Evento;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public interface IEventoDAO {

    void eventosSave(List<Evento>eventos) throws JSONException;

    Evento getEventoByDorsal(String dorsal);

}

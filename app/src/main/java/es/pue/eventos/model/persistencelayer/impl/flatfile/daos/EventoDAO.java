package es.pue.eventos.model.persistencelayer.impl.flatfile.daos;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import es.pue.eventos.model.businesslayer.entities.Evento;
import es.pue.eventos.model.businesslayer.entities.Participante;
import es.pue.eventos.model.persistencelayer.api.IEventoDAO;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class EventoDAO implements IEventoDAO {

    private Context context;

    public EventoDAO(Context context) {
        this.context = context;
    }

    @Override
    public void eventosSave(List<Evento> eventos)  {

        Gson gson=new Gson();
        String eventosJSON=gson.toJson(eventos);
        OutputStreamWriter osw=null;

        try {
            osw=new OutputStreamWriter(
                    context.openFileOutput("eventos.json",
                    Context.MODE_PRIVATE));

            osw.write(eventosJSON);
            osw.close();

        }catch(Exception ex){
            throw new RuntimeException("Error al guardar en disco el fichero eventos.json");
        }

    }

    @Override
    public Evento getEventoByDorsal(String dorsal) {

        List<Evento> eventos= getEventosFromFlatFile();

        Evento evento=null;

        for(Evento e:eventos){
            for(Participante inscrito:e.getInscritos()) {
                if(inscrito.getDorsal().equals(dorsal)){
                    e.setInscritos(new ArrayList<Participante>());
                    e.getInscritos().add(inscrito);
                    evento=e;
                    break;
                }
            }
        }
        return  evento;

    }

    private List<Evento> getEventosFromFlatFile(){

        Gson gson= new Gson();

        String eventosJSON="";

        try{
            BufferedReader br=
                    new BufferedReader(
                            new InputStreamReader(
                                    context.openFileInput("eventos.json")
                            )
                    );
            eventosJSON=br.readLine();
            br.close();
        }
        catch(Exception ex){
            Log.e("FlatFileEventoDAO",ex.getMessage());
        }


        return gson.fromJson(eventosJSON,new TypeToken<List<Evento>>(){}.getType());

    }


}

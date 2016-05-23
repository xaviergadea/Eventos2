package es.pue.eventos.model.persistencelayer.impl.sql.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.pue.eventos.model.businesslayer.entities.Evento;
import es.pue.eventos.model.businesslayer.entities.Participante;
import es.pue.eventos.model.persistencelayer.api.IEventoDAO;
import es.pue.eventos.model.persistencelayer.impl.sql.helpers.SQLiteDBHelper;
import es.pue.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 11/05/2016.
 */
public class EventoDAO implements IEventoDAO {

    private SQLiteDatabase db=null;
    private SQLiteDBHelper dbHelper=null;

    public EventoDAO(SQLiteDBHelper dbHelper){
        this.dbHelper=dbHelper;
    }

    @Override
    public void eventosSave(List<Evento> eventos) throws JSONException {

        if(db==null ||!db.isOpen())
        {
            db=dbHelper.getWritableDatabase();
        }

        Gson gson=new Gson();
        String json;
        ContentValues contentValues;
        for(Evento evento: eventos)
        {
            contentValues=new ContentValues();

            //Si nunca hemos guardado el objeto hacemos un insert.
            if(evento.getInsertedDBDate()==null)
            {
                int _id=0;
                Cursor cursor=db.query(
                        "sqlite_sequence",
                        new String[]{"seq"},
                        "name=?",
                        new String[]{AppUtils.TABLA_EVENTOS},
                        null,
                        null,
                        null
                        );
                if(cursor.moveToNext())
                {
                    _id=cursor.getInt(cursor.getColumnIndex("seq"));
                }
                cursor.close();

                _id++;

                evento.setId(_id);
                evento.setInsertedDBDate(new Date());
                json=gson.toJson(evento);

                contentValues.put(AppUtils.TABLA_EVENTOS_EVENTO,json);

                db.insert(
                        AppUtils.TABLA_EVENTOS,
                        null,
                        contentValues
                );
            }
            //Hacemos un update
            else{
                json=gson.toJson(evento);
                contentValues.put(AppUtils.TABLA_EVENTOS_EVENTO,json);
                db.update(
                        AppUtils.TABLA_EVENTOS,
                        contentValues,
                        AppUtils.TABLA_EVENTOS_ID+"=?",
                        new String[]{String.valueOf(evento.getId())}
                );
            }
        }

        if(db.isOpen())
        {
            db.close();
        }


    }

    @Override
    public Evento getEventoByDorsal(String dorsal) {

        if(db==null ||!db.isOpen())
        {
            db=dbHelper.getReadableDatabase();
        }

        Cursor cursor= db.query(
                AppUtils.TABLA_EVENTOS,
                null, null, null, null, null, null);


        Gson gson= new Gson();
        Evento evento=null;
        boolean encontrado=false;

        while (cursor.moveToNext()&&!encontrado)
        {
            evento=gson.fromJson(
              cursor.getString(
                      cursor.getColumnIndex(AppUtils.TABLA_EVENTOS_EVENTO)),
                      new TypeToken<Evento>(){}.getType());

            for(Participante participante:evento.getInscritos())
            {
                if(participante.getDorsal().equals(dorsal)){

                    evento.getInscritos().clear();
                    evento.getInscritos().add(participante);
                    encontrado=true;
                    break;
                }
            }
        }
        cursor.close();
        return evento;

    }
}

package es.pue.eventos.model.persistencelayer.impl.sql.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import es.pue.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 11/05/2016.
 */
public class SQLiteDBHelper extends SQLiteOpenHelper {

    public SQLiteDBHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(AppUtils.TABLA_EVENTOS);
        sb.append(" (");
        sb.append(AppUtils.TABLA_EVENTOS_ID);
        sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(AppUtils.TABLA_EVENTOS_EVENTO);
        sb.append(" TEXT");
        sb.append(" )");
        Log.d("Creación de DB",sb.toString());

        db.execSQL(sb.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

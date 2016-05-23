package es.pue.eventos.utilitieslayer;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public abstract class AppUtils {

    public static enum  PersistenceTechnologies{FLAT_FILE,REST,SQL};

    public final static String EVENTOS_DB="eventosDB";
    public final static int EVENTOS_DB_VERSION=1;
    public final static String TABLA_EVENTOS="eventos";
    public final static String TABLA_EVENTOS_ID="_id";
    public final static String TABLA_EVENTOS_EVENTO="evento";

}

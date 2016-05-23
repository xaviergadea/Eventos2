package es.pue.eventos.model.businesslayer.entities.base;

import java.util.Date;
import java.util.UUID;

/**
 * Created by android-ed1 on 27/04/2016.
 */
public abstract class EntityBase {

    public static final int ENTITY_UNSAVED_VALUE=-1;

    private UUID uuid;
    private int id;
    private Date insertedDBDate;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInsertedDBDate() {
        return insertedDBDate;
    }

    public void setInsertedDBDate(Date insertedDBDate) {
        this.insertedDBDate = insertedDBDate;
    }

    public EntityBase(){
            uuid=UUID.randomUUID();
            id=ENTITY_UNSAVED_VALUE;
            insertedDBDate=null;
    }

}

package tr.com.burakgul.profileapi.core.util;

import tr.com.burakgul.profileapi.model.entity.base.HistoricalBaseEntity;

import java.util.Date;

public class ObjectHistoryUtil {

    private ObjectHistoryUtil() {
    }

    public static <T extends HistoricalBaseEntity> void initHistoricalEntity(T entity){
        entity.setCreatedDate(new Date(System.currentTimeMillis()));
        setLastModifiedDate(entity);
    }

    public static <T extends HistoricalBaseEntity> void setLastModifiedDate(T entity){
        entity.setLastModifiedDate(new Date(System.currentTimeMillis()));
    }
}

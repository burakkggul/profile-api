package tr.com.burakgul.profileapi.core.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectUpdaterUtil {
    private ObjectUpdaterUtil(){
        throw new IllegalStateException("This class is a utility class");
    }

    public static <T> void updateObject(T first, T second, List<String> nonUpdatableFieldsName){
        try{
            Class<?> clazz = first.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                if(!nonUpdatableFieldsName.contains(field.getName())){
                    field.setAccessible(true);
                    field.set(first,field.get(second));
                    // field.set(first,5);
                }
            }
        }catch (IllegalAccessException illegalAccessException){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Beklenmedik bir hata oluştu.");
        }
    }
}

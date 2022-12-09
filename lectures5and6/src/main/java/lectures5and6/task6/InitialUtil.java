package lectures5and6.task6;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Properties;

public class InitialUtil {
    public static <T>T loadFromProperties(Class<T> cls, Path propertiesPath) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(cls == null){
            throw new NullPointerException();
        }
        T instance = cls.getDeclaredConstructor().newInstance();
        Properties properties = new Properties();
        InputStream is = InitialUtil.class.getClassLoader().getResourceAsStream("properties.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Field field : cls.getDeclaredFields()){
            System.out.println(field.getName());
        }
        return null;
    }
}

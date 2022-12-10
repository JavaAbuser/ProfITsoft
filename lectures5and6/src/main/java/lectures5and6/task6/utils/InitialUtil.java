package lectures5and6.task6.utils;

import lectures5and6.task6.annotations.Property;
import lectures5and6.task6.exceptions.InvalidValueException;
import org.apache.commons.beanutils.PropertyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Properties;

public class InitialUtil {
    public static <T>T loadFromProperties(Class<T> cls, Path propertiesPath) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InvalidValueException {
        if(cls == null){
            throw new NullPointerException();
        }
        T instance = cls.getDeclaredConstructor().newInstance();
        Properties properties = new Properties();
        InputStream is = InitialUtil.class.getClassLoader().getResourceAsStream(propertiesPath.toString());
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Field field : cls.getDeclaredFields()){
            field.setAccessible(true);
            String property = properties.getProperty(field.getName());
            String fieldName = field.getName();
            if(field.isAnnotationPresent(Property.class)) {
                Property annotation = field.getAnnotation(Property.class);
                if (!annotation.value().equals("")) {
                    if(!properties.containsKey(annotation.value())){
                        throw new InvalidValueException();
                    }
                    property = properties.getProperty(annotation.value());
                }
            }
            try{
                if(field.getType() == int.class || field.getType() == Integer.class){
                    PropertyUtils.setProperty(instance, fieldName, Integer.parseInt(property));
                }
                else if(field.getType() == Instant.class){
                    PropertyUtils.setProperty(instance, fieldName, Instant.parse(property));
                }
                else{
                    PropertyUtils.setProperty(instance, fieldName, property);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
        return instance;
    }
}

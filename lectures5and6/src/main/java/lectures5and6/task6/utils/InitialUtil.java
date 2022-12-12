package lectures5and6.task6.utils;

import lectures5and6.task6.annotations.Property;
import lectures5and6.task6.exceptions.FileDoesNotExistException;
import lectures5and6.task6.exceptions.InvalidInstantFormatException;
import lectures5and6.task6.exceptions.InvalidValueException;
import lectures5and6.task6.exceptions.UnknownFieldException;
import org.apache.commons.beanutils.PropertyUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitialUtil {
    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) throws InvalidValueException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InvalidInstantFormatException {
        if (cls == null) {
            throw new NullPointerException();
        }
        T instance;
        try {
            instance = cls.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Properties properties = new Properties();
        InputStream is = InitialUtil.class.getClassLoader().getResourceAsStream(propertiesPath.toString());
        try {
            properties.load(is);
        } catch (Exception e) {
            throw new FileDoesNotExistException(e);
        }
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            String property = properties.getProperty(field.getName());
            String fieldName = field.getName();
            String format = "dd.MM.yyyy HH:mm";
            if (field.isAnnotationPresent(Property.class)) {
                Property annotation = field.getAnnotation(Property.class);
                if (!annotation.value().equals("")) {
                    if (!properties.containsKey(annotation.value())) {
                        throw new InvalidValueException();
                    }

                    property = properties.getProperty(annotation.value());
                }
                if (!annotation.format().equals("")) {
                    Pattern formatPattern = Pattern.compile(format);
                    Matcher formatMatcher = formatPattern.matcher(property);
                    if (!formatMatcher.find()) {
                        throw new InvalidInstantFormatException();
                    }
                    format = annotation.format();
                }

            }
            if (field.getType() == int.class || field.getType() == Integer.class) {
                PropertyUtils.setProperty(instance, fieldName, Integer.parseInt(property));
            } else if (field.getType() == Instant.class) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                LocalDateTime dateAndTime = LocalDateTime.parse(property, formatter);
                Instant instant = dateAndTime.toInstant(ZoneOffset.UTC);
                PropertyUtils.setProperty(instance, fieldName, instant);
            } else if (field.getType() == String.class) {
                PropertyUtils.setProperty(instance, fieldName, property);
            } else {
                throw new UnknownFieldException();
            }
        }
        return instance;
    }
}

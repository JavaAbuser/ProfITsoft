package lectures5and6.task6;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Path path = Path.of("properties.properties");
        InitialUtil.loadFromProperties(Human.class, path);
    }
}

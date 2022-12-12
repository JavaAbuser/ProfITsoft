package lectures5and6;

import lectures5and6.task6.exceptions.FileDoesNotExistException;
import lectures5and6.task6.exceptions.InvalidInstantFormatException;
import lectures5and6.task6.exceptions.UnknownFieldException;
import lectures5and6.task6.models.*;
import lectures5and6.task6.exceptions.InvalidValueException;
import lectures5and6.task6.utils.InitialUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class InitialUtilTest {
    public final Path path = Path.of("properties.properties");

    @Test
    public void mustWorkWithoutAnnotation() throws InvalidValueException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InvalidInstantFormatException {
        LocalDateTime localDateTime = LocalDateTime.of(2010, 3, 17, 13, 36);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Human expectedHuman = new Human("John", 25, "Cole", 95, instant);
        Human actualHuman = InitialUtil.loadFromProperties(Human.class, path);
        Assertions.assertEquals(expectedHuman, actualHuman);
    }

    @Test
    public void mustWorkWithPropertyAnnotationValue() throws InvalidValueException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InvalidInstantFormatException {
        LocalDateTime localDateTime = LocalDateTime.of(2010, 3, 17, 13, 36);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Human1 expectedHuman = new Human1("John", 25, "Cole", 95, instant);
        Human1 actualHuman = InitialUtil.loadFromProperties(Human1.class, path);
        Assertions.assertEquals(expectedHuman, actualHuman);
    }

    @Test
    public void mustWorkWithPropertyAnnotationFormat() throws InvalidValueException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InvalidInstantFormatException {
        LocalDateTime localDateTime = LocalDateTime.of(1988, 9, 23, 21, 55, 14);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Human2 expectedHuman = new Human2("John", 25, "Cole", 95, instant);
        Human2 actualHuman = InitialUtil.loadFromProperties(Human2.class, path);
        Assertions.assertEquals(expectedHuman, actualHuman);
    }

    @Test
    public void mustThrowExceptionIfClassHasUnknownField() {
        Assertions.assertThrows(UnknownFieldException.class,
                () -> InitialUtil.loadFromProperties(Human3.class, path));
    }

    @Test
    public void mustThrowExceptionIfPropertiesDoesNotExists() {
        Assertions.assertThrows(FileDoesNotExistException.class,
                () -> InitialUtil.loadFromProperties(Human.class, Path.of("unknown.properties")));
    }

    @Test
    public void mustThrowExceptionIfValueIsNotValid() {
        Assertions.assertThrows(InvalidValueException.class,
                () -> InitialUtil.loadFromProperties(Human4.class, path));
    }

    @Test
    public void mustThrowExceptionIfFormatIsNotValid() {
        Assertions.assertThrows(InvalidInstantFormatException.class,
                () -> InitialUtil.loadFromProperties(Human5.class, path));
    }
}

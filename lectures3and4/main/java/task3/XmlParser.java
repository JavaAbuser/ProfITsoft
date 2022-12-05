package task3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser {
    public static void parseXml(String inputFileName) {
        final Pattern namePattern = Pattern.compile("(name\\s*=\\s*)\"([А-Яа-яіІЇїєЄʼ]+)\""); // regexp for string: name="Тарас"
        final Pattern surnamePattern = Pattern.compile("surname\\s*=\\s*\"([А-Яа-яіІЇїєЄʼ-]+)\""); // regexp for string: surname="Шевченко"
        final String outputFileName = "output.xml";

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            int valueOfSymbol;
            while ((valueOfSymbol = reader.read()) != -1) {
                char character = (char) valueOfSymbol;
                if (character != '>') {
                    builder.append(character);
                } else {
                    builder.append('>');
                    Matcher nameMatcher;
                    Matcher surnameMatcher = surnamePattern.matcher(builder);
                    if (surnameMatcher.find()) {
                        String surname = surnameMatcher.group(1);
                        builder.delete(surnameMatcher.start() - 1, surnameMatcher.end());
                        nameMatcher = namePattern.matcher(builder);
                        if (nameMatcher.find()) {
                            String name = nameMatcher.group(2);
                            builder.replace(nameMatcher.start(), nameMatcher.end(),
                                    nameMatcher.group(1).concat("\"").concat(name).concat(" ").concat(surname.concat("\""))); // replace first name to first name and second name
                        }
                    }
                    writer.write(builder.toString());
                    builder.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

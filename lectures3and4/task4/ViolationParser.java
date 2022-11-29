package task4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ViolationParser {
    private final List<File> files = new ArrayList<>();
    private final Map<String, Double> map = new HashMap<>();
    private File folder;

    public void findFiles() {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                findFiles();
            } else {
                files.add(fileEntry);
            }
        }
    }

    public void parse(String folderName) {
        final Pattern typePattern = Pattern.compile("\"type\"\\s*:\\s*\"(\\w+)\"");
        final Pattern fineAmountPattern = Pattern.compile("\"fine_amount\"\\s*\\s*:\\s*([\\d]*.[\\d]*)");

        folder = new File(folderName);

        findFiles();

        StringBuilder lineBuilder = new StringBuilder();
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.endsWith("},") && !line.endsWith("}")) {
                        lineBuilder.append(line);
                    } else {
                        Matcher typeMatcher = typePattern.matcher(lineBuilder);
                        Matcher fineAmountMatcher = fineAmountPattern.matcher(lineBuilder);
                        if (typeMatcher.find() && fineAmountMatcher.find()) {
                            String type = typeMatcher.group(1);
                            double fineAmount = Double.parseDouble(fineAmountMatcher.group(1));
                            lineBuilder.setLength(0);

                            saveToMap(type, fineAmount);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            createOutput(map);
        }
    }

    public void saveToMap(String type, double fineAmount) {
        if (map.containsKey(type)) {
            map.put(type, map.get(type) + fineAmount);
        } else {
            map.put(type, fineAmount);
        }
    }

    public Map<String, Double> sortMapDesc(Map<String, Double> map) {
        Map<String, Double> result = map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new));
        return result;
    }

    public void createOutput(Map<String, Double> map) {
        map = sortMapDesc(map);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("violations");
            document.appendChild(root);
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                Element element = document.createElement(entry.getKey().toLowerCase());
                Text text = document.createTextNode(entry.getValue().toString());
                root.appendChild(element);
                element.appendChild(text);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File("violations_output.xml")));
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

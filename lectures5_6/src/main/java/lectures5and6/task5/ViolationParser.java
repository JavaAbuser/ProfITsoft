package lectures5and6.task5;

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
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ViolationParser {
    private final List<File> files = new ArrayList<>();
    private final Map<String, Double> map = new HashMap<>();

    final Pattern typePattern = Pattern.compile("\"type\"\\s*:\\s*\"(\\w+)\"");
    final Pattern fineAmountPattern = Pattern.compile("\"fine_amount\"\\s*\\s*:\\s*([\\d]*.[\\d]*)");

    private File folder;

    StringBuilder lineBuilder = new StringBuilder();

    public void findFiles() {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                findFiles();
            } else {
                files.add(fileEntry);
            }
        }
    }

    public void parse(String folderName) throws InterruptedException {


        folder = new File(folderName);

        findFiles();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long start = System.currentTimeMillis();
        for (File file : files) {
            CompletableFuture.supplyAsync(() -> file, executorService)
                    .thenAccept(currentFile -> {
                        readAndMatch(currentFile);
                        System.out.println(Thread.currentThread().getName());
                    });
//            System.out.println(Thread.currentThread().getName());
//            readAndMatch(file);
            createOutput(map);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void saveToMap(String type, double fineAmount) {
        if (map.containsKey(type)) {
            map.put(type, map.get(type) + fineAmount);
        } else {
            map.put(type, fineAmount);
        }
    }

    public void readAndMatch(File file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int valueOfSymbol;
            while ((valueOfSymbol = reader.read()) != -1) {
                char character = (char) valueOfSymbol;
                if (character != '}') {
                    lineBuilder.append(character);
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
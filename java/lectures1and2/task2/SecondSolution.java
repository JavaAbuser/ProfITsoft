package lectures1and2.task2;

import java.util.*;
import java.util.stream.Collectors;

public class SecondSolution {
    public Map<String, Integer> getTopFiveHashTags(List<String> list) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (String line : list) {
            boolean wasMentioned = false;
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (word.startsWith("#")) {
                    if (!resultMap.containsKey(word)) {
                        resultMap.put(word, 1);
                        wasMentioned = true;
                    } else {
                        if (!wasMentioned) {
                            resultMap.put(word, resultMap.get(word) + 1);
                        }
                    }
                }
            }
        }
        resultMap = resultMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new));

        return resultMap;
    }
}

package task2;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

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

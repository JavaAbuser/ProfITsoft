package lectures1and2.task1;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class FirstSolution {
    public int[] getArrayOfPositiveNumbers(int[] array) {
        IntStream stream = Arrays.stream(array);
        return stream
                .boxed()
                .filter(num -> num >= 0)
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }
}

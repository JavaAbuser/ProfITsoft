package lectures1and2;

import lectures1and2.task1.FirstSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FirstSolutionTest {
    public static FirstSolution solution;

    @BeforeAll
    public static void createFirstSolution() {
        solution = new FirstSolution();
    }

    @Test
    public void shouldWorkCorrectly() {
        int[] actual = new int[]{7, 0, -10, 8, 4, -6, -1, 12};
        int[] expected = new int[]{12, 8, 7, 4, 0};

        Assertions.assertArrayEquals(expected, solution.getArrayOfPositiveNumbers(actual));
    }

    @Test
    public void shouldBeSortedDesc() {
        int[] actual = new int[]{-3, 5, 1, -9, 0};
        int[] expected = new int[]{5, 1, 0};

        Assertions.assertArrayEquals(expected, solution.getArrayOfPositiveNumbers(actual));
    }

    @Test
    public void mustBeNotNull() {
        Assertions.assertThrows(NullPointerException.class, () -> solution.getArrayOfPositiveNumbers(null));
    }
}

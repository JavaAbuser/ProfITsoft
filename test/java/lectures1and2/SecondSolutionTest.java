package lectures1and2;

import lectures1and2.task2.SecondSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SecondSolutionTest {
    private static SecondSolution solution;

    private static final List<String> actual = new ArrayList<>();
    private static final Map<String, Integer> expected = new LinkedHashMap<>();

    @BeforeAll
    public static void createSecondSolution() {
        solution = new SecondSolution();
    }

    @BeforeAll
    public static void initialize() {
        actual.add("We also do some #sport activity. Save your #health");
        actual.add("You can join us #sport #sport #sport. Be healthy and #strong ");
        actual.add("#java #self-studying #java is our opportunity. #human can do #sport and be with      #java");
        actual.add("#java");

        expected.put("#sport", 3);
        expected.put("#java", 2);
        expected.put("#health", 1);
        expected.put("#self-studying", 1);
        expected.put("#human", 1);
    }

    @Test
    public void shouldWorkCorrectly() {
        Assertions.assertEquals(expected, solution.getTopFiveHashTags(actual));
    }

    @Test
    public void mustBeNotNull() {
        Assertions.assertThrows(NullPointerException.class, () -> solution.getTopFiveHashTags(null));
    }
}

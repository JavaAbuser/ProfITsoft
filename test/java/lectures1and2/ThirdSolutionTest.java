package lectures1and2;

import lectures1and2.task3.ThirdSolution;
import lectures1and2.task3.figures.Cube;
import lectures1and2.task3.figures.Cylinder;
import lectures1and2.task3.figures.Shape;
import lectures1and2.task3.figures.Sphere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ThirdSolutionTest {
    private static ThirdSolution solution;

    private static final List<Shape> actual = new ArrayList<>();
    private static final List<Shape> expected = new ArrayList<>();

    @BeforeAll
    public static void createThirdSolution() {
        solution = new ThirdSolution();
    }

    @BeforeAll
    public static void initialize() {
        Cube cube = new Cube(4);
        Cylinder cylinder = new Cylinder(3, 5);
        Sphere sphere = new Sphere(2);

        actual.add(cube);
        actual.add(cylinder);
        actual.add(sphere);

        expected.add(sphere);
        expected.add(cube);
        expected.add(cylinder);
    }

    @Test
    public void shouldWorkCorrectly() {
        Assertions.assertEquals(expected, solution.sortByVolume(actual));
    }

    @Test
    public void mustBeNotNull() {
        Assertions.assertThrows(NullPointerException.class, () -> solution.sortByVolume(null));
    }
}

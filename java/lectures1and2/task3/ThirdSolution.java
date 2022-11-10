package lectures1and2.task3;

import lectures1and2.task3.figures.Shape;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ThirdSolution {
    public List<Shape> sortByVolume(List<Shape> unsortedList) {
        return unsortedList.stream()
                .sorted(Comparator.comparingDouble(Shape::calculateVolume))
                .collect(Collectors.toList());
    }
}

package lectures1and2.task3.figures;

public class Cube implements Shape {
    private double side;

    public Cube() {
    }

    public Cube(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double calculateVolume() {
        return Math.pow(side, 3);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "side=" + side +
                '}';
    }
}

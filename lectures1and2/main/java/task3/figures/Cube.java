package task3.figures;

public class Cube implements Shape {
    private double side;

    public Cube() {
    }

    public Cube(double side) {
        if (side > 0) {
            this.side = side;
        } else {
            System.out.println("Side must be more than 0.");
        }
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        if (side > 0) {
            this.side = side;
        } else {
            System.out.println("Side must be more than 0.");
        }
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

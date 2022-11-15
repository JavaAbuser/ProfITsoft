package task3.figures;

public class Cylinder implements Shape {
    private double radius;
    private double height;

    public Cylinder() {
    }

    public Cylinder(double radius, double height) {
        if (radius > 0 & height > 0) {
            this.radius = radius;
            this.height = height;
        } else {
            System.out.println("Radius and height must be more than 0.");
        }
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            System.out.println("Radius must be more than 0.");
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.out.println("Height must be more than 0.");
        }
    }

    @Override
    public double calculateVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "radius=" + radius +
                ", height=" + height +
                '}';
    }
}

package figures;

public class Triangle extends Shape {
    public Triangle() {
        properties = new Object[3][2];
        properties[0][0] = "A side length";
        properties[1][0] = "B side length";
        properties[2][0] = "D side length";
    }

    @Override
    public double calculateArea() {
        double halfPerimeter = calculatePerimeter() / 2;
        return (Math.sqrt(halfPerimeter
                * (halfPerimeter - Double.parseDouble(String.valueOf(properties[0][1]))
                * (halfPerimeter - Double.parseDouble(String.valueOf(properties[1][1]))
                * (halfPerimeter - Double.parseDouble(String.valueOf(properties[2][1])))))));
    }

    @Override
    public double calculatePerimeter() {
        return Double.parseDouble(String.valueOf(properties[0][1])) + Double.parseDouble(String.valueOf(properties[1][1])) + Double.parseDouble(String.valueOf(properties[2][1]));
    }
}

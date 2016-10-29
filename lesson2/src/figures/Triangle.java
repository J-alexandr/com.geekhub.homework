package figures;

public class Triangle extends Shape {
    public Triangle() {
        properties = new Object[3][2];
        properties[0][0] = "A side length";
        properties[1][0] = "B side length";
        properties[2][0] = "D side length";
    }

    @Override
    public int calculateArea() {
        double halfPerimeter = (double) calculatePerimeter() / 2;
        return (int) Math.sqrt(halfPerimeter
                * (halfPerimeter - (int) properties[0][1])
                * (halfPerimeter - (int) properties[1][1])
                * (halfPerimeter - (int) properties[2][1]));
    }

    @Override
    public int calculatePerimeter() {
        return (int) properties[0][1] + (int) properties[1][1] + (int) properties[2][1];
    }
}

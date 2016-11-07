package figures;

public class Circle extends Shape {
    public Circle() {
        properties = new Object[1][2];
        properties[0][0] = "radius";
    }

    @Override
    public double calculateArea() {
        return (Math.PI * Double.parseDouble(String.valueOf(properties[0][1])) * Double.parseDouble(String.valueOf(properties[0][1])));
    }

    @Override
    public double calculatePerimeter() {
        return (2 * Math.PI * Double.parseDouble(String.valueOf(properties[0][1])));
    }
}

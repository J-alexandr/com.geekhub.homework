package figures;

public class Circle extends Shape {
    public Circle() {
        properties = new Object[1][2];
        properties[0][0] = "radius";
    }

    @Override
    public int calculateArea() {
        return ((int) Math.PI * (int) properties[0][1] * (int) properties[0][1]);
    }

    @Override
    public int calculatePerimeter() {
        return (2 * (int) Math.PI * (int) properties[0][1]);
    }
}

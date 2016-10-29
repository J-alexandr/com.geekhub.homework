package figures;

public class Rectangle extends Shape {
    public Rectangle() {
        properties = new Object[2][2];
        properties[0][0] = "width";
        properties[1][0] = "height";
    }

    @Override
    public int calculateArea() {
        return (int) properties[0][1] * (int) properties[1][1];
    }

    @Override
    public int calculatePerimeter() {
        return ((int) properties[0][1] + (int) properties[1][1]) * 2;
    }
}

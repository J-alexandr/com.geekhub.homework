package figures;

public class Square extends Shape {
    public Square() {
        properties = new Object[1][2];
        properties[0][0] = "side length";
    }

    @Override
    public int calculateArea() {
        return (int) properties[0][1] * (int) properties[0][1];
    }

    @Override
    public int calculatePerimeter() {
        return (int) properties[0][1] * 4;
    }
}


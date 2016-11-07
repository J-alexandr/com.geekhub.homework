package figures;

public class Rectangle extends Shape implements Triangable {
    public Rectangle() {
        properties = new Object[2][2];
        properties[0][0] = "width";
        properties[1][0] = "height";
    }

    @Override
    public double calculateArea() {
        return Double.parseDouble(String.valueOf(properties[0][1])) * Double.parseDouble(String.valueOf(properties[1][1]));
    }

    @Override
    public double calculatePerimeter() {
        return (Double.parseDouble(String.valueOf(properties[0][1])) + Double.parseDouble(String.valueOf(properties[1][1])) * 2);
    }

    @Override
    public Triangle[] representAsTriangles() {
        Triangle[] triangles = new Triangle[2];
        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = Square.configureInnerTriangle(this, new Triangle());
        }
        return triangles;
    }

    @Override
    public int calculateMedian() {
        return (int) Math.sqrt(Math.pow((int) properties[0][1], 2) + Math.pow((int) properties[1][1], 2));
    }
}

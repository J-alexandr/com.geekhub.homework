package figures;

public class Square extends Shape implements Triangable {
    public Square() {
        properties = new Object[1][2];
        properties[0][0] = "side length";
    }

    @Override
    public double calculateArea() {
        return Double.parseDouble(String.valueOf(properties[0][1])) * Double.parseDouble(String.valueOf(properties[0][1]));
    }

    @Override
    public double calculatePerimeter() {
        return Double.parseDouble(String.valueOf(properties[0][1])) * 4;
    }

    @Override
    public Triangle[] representAsTriangles() {
        Triangle[] triangles = new Triangle[2];
        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = configureInnerTriangle(this, new Triangle());
        }
        return triangles;
    }

    @Override
    public int calculateMedian() {
        return (int) (Math.sqrt(2) * Double.valueOf(String.valueOf(properties[0][1])));
    }

    static Triangle configureInnerTriangle(Shape parent, Triangle triangle) {
        Object[][] properties = triangle.getProperties();
        properties[0][1] = parent.properties[0][1];
        properties[1][1] = parent.properties[0][1];
        properties[2][1] = ((Triangable) parent).calculateMedian();
        triangle.setProperties(properties);
        return triangle;
    }
}


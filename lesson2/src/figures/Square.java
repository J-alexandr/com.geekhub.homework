package figures;

public class Square extends Shape implements Triangable {
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

    static Triangle configureInnerTriangle(Shape parrent, Triangle triangle) {
        Object[][] properties = triangle.getProperties();
        properties[0][1] = parrent.properties[0][1];
        properties[1][1] = parrent.properties[0][1];
        properties[2][1] = ((Triangable) parrent).calculateMedian();
        triangle.setProperties(properties);
        return triangle;
    }
}


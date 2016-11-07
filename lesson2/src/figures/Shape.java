package figures;

public abstract class Shape {
    Object[][] properties;

    public String getName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    public Object[][] getProperties() throws NullPointerException {
        return properties;
    }

    public void setProperties(Object[][] properties) {
        this.properties = properties;
    }

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    @Override
    public String toString() {
        return getName().substring(0, 1).toUpperCase() + getName().substring(1) + "'s area is " + calculateArea() + " , perimeter is " + calculatePerimeter() + ".";
    }
}

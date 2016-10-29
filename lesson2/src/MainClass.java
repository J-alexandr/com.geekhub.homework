import figures.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
    private static Shape shape;
    private static ShapeTypes selectedType;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (selectedType == null) {
            try {
                System.out.println("Enter shape name:");
                selectedType = ShapeTypes.valueOf(reader.readLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect shape! Try again.");
            } catch (NullPointerException e) {
                System.out.println("You must enter shape name! Try again.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Configure " + selectedType.name().toLowerCase() + ". Fractional values aren't supported.");

        switch (selectedType) {
            case CIRCLE: {
                shape = configureShape(new Circle());
                break;
            }
            case SQUARE: {
                shape = configureShape(new Square());
                break;
            }
            case RECTANGLE: {
                shape = configureShape(new Rectangle());
                break;
            }
            case TRIANGLE: {
                shape = configureShape(new Triangle());
                break;
            }
        }

        System.out.println(shape.toString());

        reader.close();
    }

    private static Shape configureShape(Shape shape) {
        Object[][] properties = shape.getProperties();

        for (Object[] property : properties) {
            while (true) {
                System.out.println("Enter " + property[0] + " for " + shape.getName() + ":");
                try {
                    property[1] = Integer.parseInt(reader.readLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Wrong value for " + shape.getName() + "'s " + property[0] + ". It's must be an integer value.");
                }
            }
        }

        shape.setProperties(properties);
        return shape;
    }
}

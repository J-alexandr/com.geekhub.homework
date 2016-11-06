import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
        Inventory.getInventory().addProduct("Xbox 360", 1, 300);
        Inventory.getInventory().addProduct("PS4", 1, 400);
        Inventory.getInventory().addProduct("Dendy", 4, 25);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Welcome to JInventory! Enter HELP to display available commands.");
            readCommands(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readCommands(BufferedReader reader) {
        Command command = null;
        do {
            System.out.println("Enter command:");
            try {
                command = Command.valueOf(reader.readLine().toUpperCase());
                switch (command) {
                    case ADD:
                        addToInventory();
                        break;
                    case DELETE:
                        System.out.println("Enter product name to delete:");
                        deleteFromInventory(reader.readLine());
                        break;
                    case UPDATEQUANTITY:
                        break;
                    case INSTOCK:
                        System.out.println("Products in stock: " + Arrays.toString(Inventory.getInventory().getInStockProducts()));
                        break;
                    case INVENTORYVALUE:
                        System.out.println("Total inventory value: " + Inventory.getInventory().getInventoryValue());
                        break;
                    case PRODUCTVALUE:
                        System.out.println("Enter product name:");
                        getProductValue(reader.readLine());
                        break;
                    case HELP:
                        printHelp();
                        break;
                    case EXIT:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Unknown command.");
                e.printStackTrace();
            }
        } while (command != Command.EXIT);
    }

    private static void deleteFromInventory(String productName) {
        if (Inventory.getInventory().deleteProduct(productName)) {
            System.out.println("Product " + productName + " removed from the inventory.");
        } else {
            System.out.println("Product " + productName + " doesn't exist in the inventory");
        }
    }

    private static void getProductValue(String productName) {
        int productValue = Inventory.getInventory().getProductValue(productName);
        if (productValue == 0)
            System.out.println("There are no such product in the inventory.");
        else
            System.out.println("Inventory: " + productName + " value is " + productValue);
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        for (Command command :
                Command.values()) {
            System.out.print(command + ", ");
        }
    }

    private static void addToInventory() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter product name:");
            String productName = reader.readLine();
            System.out.println("Enter product price:");
            int price = Integer.parseInt(reader.readLine());
            System.out.println("Enter product quantity:");
            int quantity = Integer.parseInt(reader.readLine());
            Inventory.getInventory().addProduct(productName,quantity,price);
            System.out.println("Inventory successfully updated.");
        } catch (Exception e) {
            System.out.println("Product didn't put to the inventory.");
            e.printStackTrace();
        }
    }
}

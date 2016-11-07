import products.ProductCategory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainClass {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        fillInInventory();

        try {
            System.out.println("Welcome to JInventory! Enter HELP to display available commands.");
            readCommands();
            System.out.println("Goodbye!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    private static void fillInInventory() {
        Inventory.getInventory().addProduct(ProductCategory.GAME_CONSOLE, "Xbox 360", 1, 300);
        Inventory.getInventory().addProduct(ProductCategory.GAME_CONSOLE, "PS4", 1, 400);
        Inventory.getInventory().addProduct(ProductCategory.GAME_CONSOLE, "Dendy", 4, 25);
        Inventory.getInventory().addProduct(ProductCategory.LAPTOP, "Asus z466", 2, 555);
        Inventory.getInventory().addProduct(ProductCategory.TV, "LG K911", 5, 210);
    }

    private static void readCommands() {
        Command command = null;
        do {
            System.out.print("Enter command:\n>> ");
            try {
                command = Command.valueOf(reader.readLine().toUpperCase());
                switch (command) {
                    case ADD:
                        addProductToInventory();
                        break;
                    case REMOVE:
                        removeProductFromInventory();
                        break;
                    case IN_STOCK:
                        getProductsInStock();
                        break;
                    case INVENTORY_VALUE:
                        getInventoryValue();
                        break;
                    case PRODUCT_VALUE:
                        getProductValue();
                        break;
                    case CATEGORY_VALUE:
                        getCategoryValue();
                        break;
                    case HELP:
                        printHelp();
                        break;
                    case EXIT:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Unknown command. Error message: " + e.getMessage());
                printHelp();
            }
        } while (command != Command.EXIT);
    }

    private static void getCategoryValue() {
        try {
            System.out.println("Available categories: " + Arrays.toString(ProductCategory.values()));
            System.out.println("Enter product category:");
            ProductCategory productCategory = ProductCategory.valueOf(reader.readLine().toUpperCase());
            int categoryValue = Inventory.getInventory().getCategoryValue(productCategory);
            if (categoryValue == 0)
                System.out.println("There are no such category in the inventory.");
            else
                System.out.println("Inventory: category " + productCategory + " value is " + categoryValue + ".");
        } catch (Exception e) {
            System.out.println("Error while processing command. Error message: " + e.getMessage());
        }
    }

    private static void getProductsInStock() {
        System.out.println("Products in stock: " + Arrays.toString(Inventory.getInventory().getInStockProducts()));
    }

    private static void removeProductFromInventory() {
        try {
            System.out.println("Enter product name to delete:");
            String productName = reader.readLine();
            if (Inventory.getInventory().removeProduct(productName)) {
                System.out.println("Product " + productName + " removed from the inventory.");
            } else {
                System.out.println("Product " + productName + " doesn't exist in the inventory.");
            }
        } catch (Exception e) {
            System.out.println("Error while processing command. Error message: " + e.getMessage());
        }
    }

    private static void getProductValue() {
        try {
            System.out.println("Enter product name:");
            String productName = reader.readLine();
            int productValue = Inventory.getInventory().getProductValue(productName);
            if (productValue == 0)
                System.out.println("There are no such product in the inventory.");
            else
                System.out.println("Inventory: " + productName + " value is " + productValue + ".");
        } catch (Exception e) {
            System.out.println("Error while processing command. Error message: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        for (Command command :
                Command.values()) {
            System.out.print(command + ", ");
        }
        System.out.println();
    }

    private static void addProductToInventory() {
        try {
            System.out.println("Available categories: " + Arrays.toString(ProductCategory.values()));
            System.out.println("Enter product category:");
            ProductCategory productCategory = ProductCategory.valueOf(reader.readLine().toUpperCase());
            System.out.println("Enter product name:");
            String productName = reader.readLine();
            System.out.println("Enter product price:");
            int price = Integer.parseInt(reader.readLine());
            System.out.println("Enter product quantity:");
            int quantity = Integer.parseInt(reader.readLine());
            Inventory.getInventory().addProduct(productCategory, productName, quantity, price);
            System.out.println("Inventory successfully updated.");
        } catch (Exception e) {
            System.out.println("Product didn't put to the inventory. Error message: " + e.getMessage());
        }
    }

    private static void getInventoryValue() {
        System.out.println("Total inventory value: " + Inventory.getInventory().getInventoryValue());
    }
}

import products.Product;
import products.ProductCategory;

import java.util.*;

class Inventory {
    private static Inventory inventory;
    private Map<Product, Integer> store = new HashMap<>();

    static Inventory getInventory() {
        if (inventory == null)
            inventory = new Inventory();
        return inventory;
    }

    int getCategoryValue(ProductCategory productCategory) {
        Iterator iterator = store.entrySet().iterator();
        int summaryValue = 0;
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if (productCategory == ((Product) pair.getKey()).getProductCategory())
                summaryValue += ((Product) pair.getKey()).getPrice() * Integer.parseInt(pair.getValue().toString());
        }
        return summaryValue;
    }

    int getInventoryValue() {
        Iterator iterator = store.entrySet().iterator();
        int summaryValue = 0;
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            summaryValue += ((Product) pair.getKey()).getPrice() * Integer.parseInt(pair.getValue().toString());
        }
        return summaryValue;
    }

    void addProduct(ProductCategory productCategory, String name, int quantity, int price) {
        Product product = new Product(productCategory, name, price);
        if (store.containsKey(product)) {
            int updatedQuantity = store.get(product) + quantity;
            store.put(product, updatedQuantity);
        } else
            store.put(product, quantity);
    }

    int getProductValue(String productName) {
        Iterator iterator = store.entrySet().iterator();
        int summaryValue = 0;
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if (productName.toLowerCase().equals(((Product) pair.getKey()).getName().toLowerCase()))
                summaryValue += ((Product) pair.getKey()).getPrice() * Integer.parseInt(pair.getValue().toString());
        }
        return summaryValue;
    }

    Product[] getInStockProducts() {
        List<Product> products = new ArrayList<>();
        Iterator iterator = store.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if ((Integer) pair.getValue() > 0) {
                products.add((Product) pair.getKey());
            }
        }
        return products.toArray(new Product[products.size()]);
    }

    boolean removeProduct(String productName) {
        boolean success = false;
        Iterator iterator = store.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if (productName.toLowerCase().equals(((Product) pair.getKey()).getName().toLowerCase())) {
                iterator.remove();
                success = true;
            }
        }
        return success;
    }
}

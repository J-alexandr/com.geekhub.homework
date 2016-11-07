package products;

public class Product {
    private String name;
    private int price;
    private ProductCategory productCategory;

    public Product(ProductCategory productCategory, String name, int price) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", productCategory=" + productCategory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return price == product.price && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price;
        return result;
    }
}

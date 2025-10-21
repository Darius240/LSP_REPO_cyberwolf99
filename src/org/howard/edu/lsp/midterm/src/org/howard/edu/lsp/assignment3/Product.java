package org.howard.edu.lsp.assignment3;

/**
 * Model class representing a product record.
 *
 * Immutable-ish: fields are private with getters. If you need mutability,
 * you can add setters.
 */
public class Product {
    private final String id;
    private final String name;
    private final String category;
    private final double price;
    private final int quantity;

    /**
     * Create a Product.
     *
     * @param id       product id (non-null)
     * @param name     product name
     * @param category product category
     * @param price    price as double
     * @param quantity available quantity
     */
    public Product(String id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name == null ? "" : name;
        this.category = category == null ? "" : category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Return a CSV line representation for writing.
     * Adjust if you need a different output format.
     *
     * @return CSV-ready string
     */
    public String toCsvLine() {
        // Ensure commas in fields are escaped or removed as appropriate for your A2
        String safeName = name.replace(",", " ");
        String safeCategory = category.replace(",", " ");
        return String.format("%s,%s,%s,%.2f,%d", id, safeName, safeCategory, price, quantity);
    }

    @Override
    public String toString() {
        return "Product{" + id + ", " + name + ", " + category + ", " + price + ", " + quantity + "}";
    }
}

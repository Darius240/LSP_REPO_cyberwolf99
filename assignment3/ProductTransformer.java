package org.howard.edu.lsp.assignment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Transformer implementing product-level transformations.
 *
 * NOTE: Replace the sample transformations with those from your Assignment 2
 * so the final output matches A2 exactly.
 */
public class ProductTransformer implements Transformer {

    /**
     * Transform products by applying a sample set of rules:
     * - normalize product name (trim)
     * - uppercase category
     * - round price to 2 decimals
     * - (sample) remove negative quantities
     *
     * Modify this method to match A2's exact transformations.
     */
    @Override
    public List<Product> transform(List<Product> input) {
        List<Product> out = new ArrayList<>();
        for (Product p : input) {
            String id = p.getId();
            String name = p.getName().trim();
            String category = p.getCategory().trim().toUpperCase();
            double price = Math.round(p.getPrice() * 100.0) / 100.0;
            int quantity = Math.max(0, p.getQuantity()); // sample rule

            // If A2 required computed fields (e.g., discounted price), compute them here
            out.add(new Product(id, name, category, price, quantity));
        }
        return out;
    }
}

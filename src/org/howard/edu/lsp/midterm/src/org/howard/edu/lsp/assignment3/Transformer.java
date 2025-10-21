package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Interface describing a transformation step for a list of Product objects.
 */
public interface Transformer {
    /**
     * Transform the input list and return a new list.
     *
     * Implementations should not mutate the input list in-place unless documented.
     *
     * @param input list of products to transform
     * @return transformed list
     */
    List<Product> transform(List<Product> input);
}

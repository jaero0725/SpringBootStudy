package kr.ac.hansung.service;

import java.util.List;

import kr.ac.hansung.entity.Category;
import kr.ac.hansung.entity.Product;

public interface ProductService {

    /**
     * Gets all products present in the system.
     * The result is paginated.
     *
     * @param page" the page to fetch results from
     * @return list of products
     */
    List<Product> getAllProducts();
    
    /**
     * Gets a specific product by looking for its id.
     *
     * @param id" the id of the product to look for
     * @return the product (if any)
     */
    Product getProductById(Long id);

    /**
     * Creates a product.
     * 
     * @param name: the name of the product
     * @param price: the price of the product
     * @return the new product
     */
    Product createProduct(String name, double price);

    /**
     * Updates an existing product.
     *
     * @param product: the product to update
     * 
     */
    void updateProduct(Product product);

    /**
     * Deletes a product in the system.
     *
     * @param product the product to delete
     */
    void deleteProduct(Product product);

    /**
     * Adds a category to the product.
     *
     * @param product: the product to add the category to
     * @param category: the category to add
     */
    void addCategory(Product product, Category category);
    
    /**
     * Removes a category from the product.
     *
     * @param product: the product to remove the category from
     * @param category: the category to remove
     */
    void removeCategory(Product product, Category category);

    
    /**
    * Checks whether the product has a given category.
    *
    * @param product: the product to check
    * @param category: the category to check
    * @return true if the product is associated to the category
    */
   boolean hasCategory(Product product, Category category);
   
}
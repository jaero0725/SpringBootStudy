package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.dao.ProductDao;
import kr.ac.hansung.entity.Category;
import kr.ac.hansung.entity.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productRepository;
          
    @Override
    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Product createProduct(String name, double price) {        
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        Long id = productRepository.addProduct(product);
        product.setId(id);
        
        return product;       
    }

    @Override
    public void updateProduct(Product product) {       
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }

    @Override
    public void addCategory(Product product, Category category) {
    	product.getCategories().add(category);
    	
        productRepository.updateProduct(product);
    }    
    
    @Override
    public void removeCategory(Product product, Category category) {
        product.getCategories().remove(category);
        productRepository.updateProduct(product);
    }

    @Override
    public boolean hasCategory(Product product, Category category) {
        return product.getCategories().contains(category);
    }
}

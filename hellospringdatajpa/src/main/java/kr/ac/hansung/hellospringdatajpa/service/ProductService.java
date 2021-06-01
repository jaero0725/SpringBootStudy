package kr.ac.hansung.hellospringdatajpa.service;

import kr.ac.hansung.hellospringdatajpa.entity.Product;
import kr.ac.hansung.hellospringdatajpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product get(long id) {
        return repo.findById(id).get();
    }
    public List<Product> listAll() {
        return repo.findAll();
    }
    public void save(Product product) {
        repo.save(product);
    }
    public void delete(long id) {
        repo.deleteById(id);
    }
}
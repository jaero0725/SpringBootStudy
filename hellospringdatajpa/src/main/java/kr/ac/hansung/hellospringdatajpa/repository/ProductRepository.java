package kr.ac.hansung.hellospringdatajpa.repository;

import kr.ac.hansung.hellospringdatajpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByNameContaining(String searchKeyword, Pageable paging);
    //Page<Product> findByNameContaining(String searchKeyword, Pageable paging);

    @Query("select p from Product p where p.name like %?1%")
    List<Product> searchByName(String name);
}
package kr.ac.hansung.hellospringdatajpa.repository;

import kr.ac.hansung.hellospringdatajpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //이름을 바탕으로 조회 - 정확히 이름이 일치해야됨.
    Product findByName(String name);

    //이름을 바탕으로 검색 - 이름을 포함하면 된다.
    List<Product> findByNameContaining(String searchKeyword, Pageable paging);

    //Page<Product> findByNameContaining(String searchKeyword, Pageable paging);

    @Query("select p from Product p where p.name like %?1%")
    List<Product> searchByName(String name);
}
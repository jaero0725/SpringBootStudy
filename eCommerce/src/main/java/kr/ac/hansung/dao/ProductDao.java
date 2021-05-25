package kr.ac.hansung.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.entity.Product;

@Repository
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Product getProductById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
	
		return product;
	}

	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Product";

		Query<Product> query = session.createQuery(hql, Product.class);
		List<Product> productList = query.getResultList();

		return productList;
	}

	public Long addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		Long id = (Long) session.save(product);
		session.flush();
		return id;
	}

	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}

	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.update(product);
		session.flush();
	}
}

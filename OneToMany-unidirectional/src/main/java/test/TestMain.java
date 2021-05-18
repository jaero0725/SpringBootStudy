package test;

import java.util.List;

import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Category;
import entity.Product;

public class TestMain {
	
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		//Transient 상태 
		Category category1 = new Category();
		category1.setName("컴퓨터");
		
		Category category2 = new Category();
		category2.setName("자동차");
		
		Product product1 = new Product();
		product1.setName("Notebook1");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook");
		product1.setCategory(category1);
		
		Product product2 = new Product();
		product2.setName("Notebook2");
		product2.setPrice(3000);
		product2.setDescription("Powerful notebook");
		product2.setCategory(category1);
		
		Product product3 = new Product();
		product3.setName("Sonata");
		product3.setPrice(300000);
		product3.setDescription("Popular car");
		product3.setCategory(category2);
		
		Product product4 = new Product();
		product4.setName("Benz");
		product4.setPrice(9500000);
		product4.setDescription("good car");
		product4.setCategory(category2);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//Persitance 상태
		session.save(product1);
		session.save(product2);
		session.save(product3);
		session.save(product4);
		
		session.delete(product3);
		session.delete(product4);
		
		//Category는 삭제 하지 않고 product만 삭제 시키기 위함. 
		product1.setCategory(null);	//product1 -> null;
		session.delete(product1);
		
		//Error
		//deleted object would be re-saved by cascade (remove deleted object from associations)
		//product1, product2 가 같이 Category를 가리키고 있기때문에, 문제가 생김. 
		//product가 저장되면, Cascade에 의해서 Category도 같이 저장이 된다.
		//cascade=CascadeType.ALL
		
		tx.commit();
		
		session.close();
		sessionFactory.close();
	}
}

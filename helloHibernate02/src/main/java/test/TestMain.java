package test;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Product;

public class TestMain {
	
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		Configuration conf = new Configuration(); //설정객체
		conf.configure("hibernate.cfg.xml"); //설정파일 명시
		sessionFactory = conf.buildSessionFactory();
		
		//Chained method
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Product product1 = new Product();
		product1.setName("Notebook2");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook");
		
		Product product2 = new Product();
		product2.setName("Notebook1");
		product2.setPrice(3000);
		product2.setDescription("Powerful notebook");
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(product1);
		session.save(product2);
		
		//DB에서 저장한걸 읽는게 아니고 캐쉬에 있는 걸 읽는다. 
		//commit을 했을 때 데이터베이스에 반영이 된다. 
		//performance에 영향을 주기 떄문이다. 
		//Product savedProduct = session.get(Product.class, id1);
		//System.out.println("saved product" + savedProduct);

		//전체 조회
		Query <Product> aQuery = session.createQuery("from Product order by name", Product.class); //HQL
		List<Product> products = aQuery.getResultList();
		System.out.println(products);
		
		tx.commit();
		
		session.close();
		sessionFactory.close();
	}
	//Reverse Engineer을 통해 데이터베이스에 뭐가 저장되었는지 확인도해라
}

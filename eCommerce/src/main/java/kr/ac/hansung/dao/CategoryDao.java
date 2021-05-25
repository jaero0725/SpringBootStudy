package kr.ac.hansung.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.entity.Category;

@Repository
public class CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Category getCategoryById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = (Category) session.get(Category.class, id);
		
		return category;
	}

	public List<Category> getCategories() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Category";

		Query<Category> query = session.createQuery(hql, Category.class);
		List<Category> categories = query.getResultList();

		return categories;
	}

	public Long addCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		Long id = (Long) session.save(category);
		session.flush();
		return id;
	}

	public void deleteCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(category);
		session.flush();
	}

	public void updateCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.update(category);
		session.flush();
	}
}

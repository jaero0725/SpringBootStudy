package kr.ac.hansung.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.model.Offer;


@Repository
@Transactional  //각각 Transaction 넣는다. 
public class OfferDao {
	
	@Autowired
	private SessionFactory sessionFactory;	//sessionFactory 주입.
    
    public Offer getOfferById(int id) {
    	Session session = sessionFactory.getCurrentSession();
    	Offer offer = (Offer) session.get(Offer.class, id); //session.get()
    	return offer;
    }
    
    // cRud method
    public List<Offer> getOffers() {
    	Session session = sessionFactory.getCurrentSession();
    	String hql = "from Offer";	//복잡한 쿼리는 HQL사용
    	Query <Offer> query = session.createQuery(hql, Offer.class);  //org.hibernate.query.Querty 넣기
        List<Offer> offerList =  query.getResultList();
        return offerList;
    }
    
    // Crud method
    public void insert(Offer offer) {
    	Session session = sessionFactory.getCurrentSession();
    	session.saveOrUpdate(offer);
    	session.flush();	//flush는 굳이 넣어도 그만 안넣어도 그만, Transaction이 들어가서 commit을 할떄 flush를 해주기 때문이다. 
    }
    
    // crUd method
    public void update(Offer offer) {
    	Session session = sessionFactory.getCurrentSession();
    	session.saveOrUpdate(offer);
    	session.flush();
    }
    
    //cruD method
    public void delete(Offer offer) {
    	Session session = sessionFactory.getCurrentSession();
    	session.delete(offer);
    	session.flush();
    }
}

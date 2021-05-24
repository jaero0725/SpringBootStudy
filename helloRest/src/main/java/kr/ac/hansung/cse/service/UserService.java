package kr.ac.hansung.cse.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.User;

@Service
@Transactional
public class UserService {
	
	//AtomicLong ? 
	/*
	 * 톰캣은 멀티 스레드 환경
	 * request 마다 하나의 Thread가 생김. 
	 * 따라서 하나의 접근을 할때 "상호배제" 한다. 
	 *  => AtomicLong
	 */
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	public UserService() {
		
		users = new ArrayList<User>();
		
		//incrementAndGet : 증가시키고 가져옴 .
		users.add(new User(counter.incrementAndGet(), "Alice", 30, 70000));
		users.add(new User(counter.incrementAndGet(), "Bob", 40, 50000));
		users.add(new User(counter.incrementAndGet(), "Charlie", 45, 30000));
		users.add(new User(counter.incrementAndGet(), "Daniel", 50, 40000));
	}
	
	public List<User> findAllUsers(){
		return users;
	}
	
	public User findById(long id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user: users) {
			if(user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}
	
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}
	
	public void deleteUserById(long id) {
		for(Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public boolean doesUserExist(User user) {
		return findByName(user.getName()) != null;
	}
	
	public void deleteAllUsers() {
		users.clear();
	}
}

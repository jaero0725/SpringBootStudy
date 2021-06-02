# helloHibernate
> Hibernate 연습

## Benefits of Hibernate
> 1) Hiberante provides the Object-to-Relational Mapping(ORM)
> 2) Hibernate handles all of the low-level SQL
> - JDBC 코드의 양을 최소화 해준다. 
<br>

## Application Architecture
<img src="https://user-images.githubusercontent.com/55049159/111074777-068caa00-8528-11eb-8b47-e13b846b0ed2.png" width="700" heigth="400">
<img src="https://user-images.githubusercontent.com/55049159/111074758-ef4dbc80-8527-11eb-8d0a-220adfd652b7.png" width="700" heigth="400">

# (1) Hibernate-introduction
## Ex. Spring 없이 Java Application을 이용해서 Hibernate 사용 

#### pom.xml
> <b>의존성 추가</b>
> 1) Hibernate-core <br>
> 2) MySQL Connector<br>
> 3) lombok<br>
> 4) logback<br><br>

~~~xml
		<!-- Hibernate Dependency 추가. -->
		<!-- Hibernate-core -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.4.29.Final</version>
		</dependency>
		<!-- MySQL Connector -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.20</version>
		</dependency>

		<!-- lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.12</version>
			<scope>provided</scope>
		</dependency>

		<!-- logback -->
		<!-- logback-classic만 넣어주면, core도 들어감 -->
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>1.2.3</version>
			<scope>runtime</scope>
		</dependency>
~~~

### src/main/resources
#### 1. hibernate.cfg.xml 파일 생성

~~~xml
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>

	<session-factory>
		<!-- JDBC Database connection settings -->
		<property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
		<property name="connection.url">jdbc:mysql://localhost:3306/testdb?useSSL=false&amp;characterEncoding=UTF-8&amp;serverTimezone=Asia/Seoul</property>
		<property name="connection.username">root</property>
		<property name="connection.password">hansung</property>
		
		<!-- Select our SQL dialect 방언..-->
		<property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>
		
		<property name="show_sql">true</property>
		<property name="current_session_context_class">thread</property>
		<property name="hbm2ddl.auto">create</property>	<!-- create 테이블을 자동으로 생성해 준다.  drop : 종료되면 삭제  -->
		
		<mapping class="helloHibernate.Product"/>	<!-- mapping 해서 바로 만들어 준다.  -->
	</session-factory>
</hibernate-configuration>
~~~

#### 2. logback.xml
~~~xml
<?xml version="1.0" encoding="UTF-8"?>
    <configuration>
    
    	<!-- console Appender -->
    	<appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
    		<encoder>
    			<Pattern>.%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg %n
    			</Pattern>
    		</encoder>
    		<filter class="ch.qos.logback.classic.filter.ThresholdFilter">
    			<level>TRACE</level>
    		</filter>
    	</appender>
    
    	<logger name="kr.ac.hansung.cse">
    	    <level value="DEBUG" />
    	</logger>
    	
    	<!-- org.hibernate에 대해서 자세히 보기 위해 추가. -->
    	<logger name="org.hibernate.type.descriptor.sql">
    	    <level value="Trace" />
    	</logger>
    	
    	<root>
    		<level value="INFO" />
    		<appender-ref ref="consoleAppender" />
    	</root>
    </configuration>
~~~

###  src/main/java
####  3. Product.java

~~~java
package helloHibernate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Entity class : Java class that is mapped to a database table
 * 
 * Java Annotations 
 * - map class to database table
 * - map fields to database columns
 * 
 */

@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product {
	
	@Id							//id로 사용. primary key
	@GeneratedValue						//키를 생성할때는, 자동으로 생성한다. 
	@Column(name="product_id")				//컬럼 내용을 지정해줄 수 있다.  - 만약에 name을 지정하지 않는다면, field이름과 같아 진다. 
	private int id;
	
	private String name;
	
	private int price;
	
	private String description;
}

~~~

#### 4. TestMain.java

~~~java
package helloHibernate;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//JAVA Application에서 사용하는 법. 
public class TestMain {
	
	private static SessionFactory sessionFactory;		//Spring 에서는 DI 
	
	public static void main(String[] args) {
		
		//세션 펙토리 얻어 오는 과정. 
		/*
		 * Configuration conf = new Configuration(); conf.configure();
		 * 
		 * sessionFactory = conf.buildSessionFactory();					//설정파일 명시 = Default 이름 : hibernate.cfg.xml
		 */
		
		sessionFactory = new Configuration().configure().buildSessionFactory();		//chained method 
		
		Product product1 = new Product();
		product1.setName("Notebook");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook");
		
		Session session = sessionFactory.openSession(); 		//세션을 만든다.
		Transaction tx = session.beginTransaction(); 			//트랜젝션 시작
		
		session.save(product1); 					//자동적으로 데이터베이스에 저장됨.
		
		tx.commit(); 							//트랜젝션 commit
		session.close();						//세션을 닫음.
		sessionFactory.close();						//세션 팩토리 닫음. 
		
	}

}
~~~

### <실행결과 (1) product1 추가>
<b>logback에 추가하여 데이터베이스에 어떤 값이 나왔는지 console에서 확인 가능</b>
~~~xml
	<!-- org.hibernate에 대해서 자세히 보기 위해 추가. -->
    	<logger name="org.hibernate.type.descriptor.sql">
    	    <level value="Trace" />
    	</logger>
~~~
![console02](https://user-images.githubusercontent.com/55049159/111074923-bc57f880-8528-11eb-9b00-fd6c82861a30.PNG)
<br>

<b>MySQL workbench |  testdb 확인</b>

![캡처](https://user-images.githubusercontent.com/55049159/111074921-bbbf6200-8528-11eb-9a7e-55b730b8c07b.PNG)
<br>

### <실행결과 (2) product1, product2 추가 , createQuery() 메서드 사용>
#### TestMain.java 변경
~~~java
package helloHibernate;



import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//JAVA Application에서 사용하는 법. 
public class TestMain {
	
	private static SessionFactory sessionFactory;		//Spring 에서는 DI 
	
	public static void main(String[] args) {
		
		//세션 펙토리 얻어 오는 과정. 
		/*
		 * Configuration conf = new Configuration(); conf.configure();
		 * 
		 * sessionFactory = conf.buildSessionFactory();					//설정파일 명시 = Default 이름 : hibernate.cfg.xml
		 */
		
		sessionFactory = new Configuration().configure().buildSessionFactory();		//chained method 
		
		Product product1 = new Product();
		product1.setName("Notebook");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook");
		
		Product product2 = new Product();
		product2.setName("Notebook2");
		product2.setPrice(3000);
		product2.setDescription("Powerful notebook");
		
		Session session = sessionFactory.openSession(); 		//세션을 만든다.
		Transaction tx = session.beginTransaction(); 			//트랜젝션 시작
	
		session.save(product1);	
		session.save(product2);	
		
		//바로 DB에 저장되지 않음. 
		//캐시에 잇음.
		/*
		 * Serializable id1 = session.save(product1);				//id를 기억함.
		 * Product savedProduct = session.get(Product.class, id1);
		 * System.out.println("saved product " + savedProduct); //캐시에 저장된걸 읽어옴.
		 * session.save(product1);
		 */								
		
		Query<Product> aQuery = session.createQuery("from Product order by name", Product.class);	//HQL 사용
		List <Product> products = aQuery.getResultList(); //조회
		System.out.println(products);
		
		tx.commit(); 				//트랜젝션 commit	- 이때 DB에 저장됨. 
		session.close();			//세션을 닫음.
		sessionFactory.close();		//세션 팩토리 닫음. 
		
	}

}

~~~
<b>추가된 정보들 조회</b><br/>
![리스트 조회](https://user-images.githubusercontent.com/55049159/111075875-4609c500-852d-11eb-864b-85e1e9069169.PNG)<br/>

<b>MySQL workbench |  testdb 확인</b><br/>
![22222222](https://user-images.githubusercontent.com/55049159/111075876-46a25b80-852d-11eb-9587-4c6601d73f86.PNG)<br/>

<b>데이터베이스 구성</b><br/>
![reverse](https://user-images.githubusercontent.com/55049159/111075878-47d38880-852d-11eb-803c-65e9d888d5b6.PNG)<br/>

---
# (2)OneToMany - Uni
#### Category.java 추가
<br/>

~~~java
package helloHibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue
	@Column(name="category_id")
	private int id;
	
	private String name;
}
~~~

#### Product.java 수정
<br/>

~~~java
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
~~~

<br/>

#### TestMain.java 수정
<br/>

~~~java

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//JAVA Application에서 사용하는 법. 
public class TestMain {
	private static SessionFactory sessionFactory;		//Spring 에서는 DI 
	
	public static void main(String[] args) {
		
		//세션 펙토리 얻어 오는 과정. 
		/*
		 * Configuration conf = new Configuration(); conf.configure();
		 * 
		 * sessionFactory = conf.buildSessionFactory();					//설정파일 명시 = Default 이름 : hibernate.cfg.xml
		 */
		
		sessionFactory = new Configuration().configure().buildSessionFactory();		//chained method 
		
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
		product3.setPrice(100000);
		product3.setDescription("Popular Car");
		product3.setCategory(category2);
		
		Session session = sessionFactory.openSession(); 		//세션을 만든다.
		Transaction tx = session.beginTransaction(); 			//트랜젝션 시작
	
		session.save(product1);	
		session.save(product2);	
		session.save(product3);	
		
		product1.setCategory(null);
		session.delete(product1);
		//바로 DB에 저장되지 않음. 
		//캐시에 잇음.
		/*
		 * Serializable id1 = session.save(product1);				//id를 기억함.
		 * Product savedProduct = session.get(Product.class, id1);
		 * System.out.println("saved product " + savedProduct); //캐시에 저장된걸 읽어옴.
		 * session.save(product1);
		 */								
		
		/*
		 * Query<Product> aQuery = session.createQuery("from Product order by name",
		 * Product.class); //HQL 사용 List <Product> products = aQuery.getResultList();
		 * //조회 System.out.println(products);
		 */
		
		tx.commit(); 				//트랜젝션 commit	- 이때 DB에 저장됨. 
		session.close();			//세션을 닫음.
		sessionFactory.close();		//세션 팩토리 닫음. 
	}

}
~~~

<br/>

#### hibernate.cfg.xml , mapping class 추가

~~~xml
	<mapping class="helloHibernate.Category"/>
~~~


#### <테이블>
![2 결과](https://user-images.githubusercontent.com/55049159/111104191-6ec4a580-8593-11eb-8562-dc0c1eeb1bcc.PNG)

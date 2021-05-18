package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//Child - Many
//Parent - One

@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product {
	@Id							//id로 사용. primary key
	@GeneratedValue				//키를 생성할때는, 자동으로 생성한다. 
	@Column(name="product_id")	//컬럼 내용을 지정해줄 수 있다.  - 만약에 name을 지정하지 않는다면, field이름과 같아 진다. 
	private int id;
	
	private String name;
	private int price;
	private String description;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category; // foreign key로 변환이 된다. 
}

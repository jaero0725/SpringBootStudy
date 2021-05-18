package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	//bidirectional - many 사이드는 default 값이 LAZY
	@OneToMany(mappedBy ="category", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<Product>();
}


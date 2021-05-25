package kr.ac.hansung.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*
 * 1) Product: Category는 ManyToMany 관계로 설정하였으며 양방향임, JoinTable을 사용
 * 2) FetchType은 구현의 간소함으로 위해 Eager로 설정
 * 3) Product와 Category간의 양방향으로 인해
 *    Product 조회시 categories 필드 -> Category -> Product을 읽는 Cycle을 막기 위해 @JsonIgnore 사용
*/

@Getter
@Setter
@ToString
@Entity
@Table(name = "app_product")
public class Product extends AbstractEntity {

	@Column(name = "name", nullable = false)
	private String name;	
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_product_category", joinColumns = @JoinColumn(name = "productid"), inverseJoinColumns = @JoinColumn(name = "categoryid"))
	@JsonIgnore
	private Set<Category> categories;

}
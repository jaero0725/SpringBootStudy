package kr.ac.hansung.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Category와 childCategories 관계는 1:n으로 매핑
 * Product와 Category는 N:N으로 매핑
*/

@Getter
@Setter
@ToString

@Entity
@Table(name="app_category")
public class Category extends AbstractEntity {

	@Column(name = "name", nullable = false)
	private String name;	
	
    @ManyToMany( fetch = FetchType.EAGER, mappedBy = "categories")
	private Set<Product> products;
	
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private Set<Category> childCategories;

    @ManyToOne
    @JoinColumn(name = "parentid")
	@JsonIgnore
    private Category parent;

}
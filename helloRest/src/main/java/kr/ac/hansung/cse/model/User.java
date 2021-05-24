package kr.ac.hansung.cse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
	private long id;
	private String name;
	private int age;
	private double salary;
}

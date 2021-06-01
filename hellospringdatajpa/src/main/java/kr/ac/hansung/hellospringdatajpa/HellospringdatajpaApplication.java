package kr.ac.hansung.hellospringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql
@SpringBootApplication
public class HellospringdatajpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellospringdatajpaApplication.class, args);
    }

}

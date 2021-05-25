package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.entity.Category;
import kr.ac.hansung.exception.NotFoundException;
import kr.ac.hansung.service.CategoryService;


/* URL: /api/categories

To get list of categories:  GET "http://localhost:8080/ecommerce/api/categories"
To get category info:  GET "http://localhost:8080/ecommerce/api/categories/{id}"
To create category: POST "http://localhost:8080/ecommerce/api/categories" -d '{ "name": "C1" }'
To update category:  PUT "http://localhost:8080/ecommerce/api/categories/{id}" -d '{ "name": "C1" }'
To delete category: DELETE "http://localhost:8080/ecommerce/api/categories/{id}"
*/

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllCategories() {
        // Getting all categories in application...
        final List<Category> categories = categoryService.getAllCategories();

        if (categories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

        return ResponseEntity.ok(categories);
		//return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveCategory(@PathVariable Long id) {
    	
       
		
    }

    // DTO(Data Transfer Object) : 계층간 데이터 교환을 위한 객체, 여기서는 클라이언트(Postman)에서 오는 데이터를 수신할 목적으로 사용
    // Category와 CategoryDto와의 차이를 비교해서 살펴보기 바람
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto request) {
    	
        // Creating a new category in the application...
        final Category category = categoryService.createCategory(request.getName());

		//return new ResponseEntity<Category>(category, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto request) {
    	
       
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
    	
        // Getting the requiring category; or throwing exception if not found
        final Category category = categoryService.getCategoryById(id);
        
        if(category == null)
			throw new NotFoundException(id);

        // Deleting category from the application...
        categoryService.deleteCategory(category);

        return ResponseEntity.noContent().build();
		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    static class CategoryDto {
        @NotNull(message = "name is required")
        @Size(message = "name must be equal to or lower than 100", min = 1, max = 100)
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
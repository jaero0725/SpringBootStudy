package kr.ac.hansung.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.entity.Category;
import kr.ac.hansung.exception.NotFoundException;
import kr.ac.hansung.service.CategoryService;


/* API Endpoint for categories and subcategories association
 * 
 * To see the current child categories for a given category, you can do a GET on
 * 		/categories/{parentid}/subcategories
 * 
 * Add / Remove child categories To associate / dis-associate a child category
 * 		with / from a parent category you can use the following URL:
 * 		/categories/{parentid}/subcategories/{childid} 
 * 
 */

@RestController
@RequestMapping(path = "/api/categories/{parentid}/subcategories")
public class CategorySubcategoriesController {

    @Autowired
    private CategoryService categoryService;    

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllSubcategories(@PathVariable Long parentid) {
    	
        // Getting the requiring category; or throwing exception if not found
        final Category parent = categoryService.getCategoryById(parentid);
        if( parent == null)
        	throw  new NotFoundException(parentid);
        
        // Getting all categories in application...
        final Set<Category> subcategories = parent.getChildCategories();

        return ResponseEntity.ok(subcategories);
    }

    @RequestMapping(path = "/{childid}", method = RequestMethod.POST)
    public ResponseEntity<?> addSubcategory(@PathVariable Long parentid, @PathVariable Long childid) {
    	
       
    }

    @RequestMapping(path = "/{childid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeSubcategory(@PathVariable Long parentid, @PathVariable Long childid) {
    	
        // Getting the requiring category; or throwing exception if not found
        final Category parent = categoryService.getCategoryById(parentid);
        if( parent == null)
        	throw  new NotFoundException(parentid);
        
        // Getting the requiring category; or throwing exception if not found
        final Category child = categoryService.getCategoryById(childid);
        if( child == null)
        	throw  new NotFoundException(childid);

        // Validating if association exists...
        if (!categoryService.isChildCategory(child, parent)) {
            throw new IllegalArgumentException("category " + parent.getId() + " does not contain subcategory " + child.getId());
        }

        // Dis-associating parent with subcategory...
        categoryService.removeChildCategory(child, parent);

        return ResponseEntity.noContent().build();
    }

}
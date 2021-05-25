package kr.ac.hansung.service;

import java.util.List;

import kr.ac.hansung.entity.Category;


/**
 * Service 인터페이스를 정의하고 이에 대한 구현 클래스 ServiceImple을 정의하는 것이 일반적인 관례
 *
 */
public interface CategoryService {

    /**
     * Gets all categories present in the system.
     *
     * @return all the categories
     */
    List<Category> getAllCategories();

    /**
     * Gets a specific category by looking for its id.
     *
     * @param id: the id of the category to look for
     * @return the category (if any)
     */
    Category getCategoryById(Long id);

    /**
     * Creates a category.
     *
     * @param name: the name of the category
     * @return the new category
     */
    Category createCategory(String name);

    /**
     * Updates an existing category.
     *
     * @param category the category to update
     */
    void updateCategory(Category category);

    /**
     * Deletes a category in the system.
     *
     * @param category the category to delete
     */
    void deleteCategory(Category category);

    /**
     * Checks whether the given categories are associated each other.
     *
     * @param child: the child category to check
     * @param parent: the parent category to check
     * @return true if the given child category is associated with the parent category
     */
    boolean isChildCategory(Category child, Category parent);

    /**
     * Adds a child category into the given parent category.
     *
     * @param child: the child category to add
     * @param parent: the parent category to add the child to
     */
    void addChildCategory(Category child, Category parent);

    /**
     * Removes a child category from the given parent category.
     *
     * @param child: the child category to remove
     * @param parent: the parent category to remove the child from
     */
    void removeChildCategory(Category child, Category parent);

}
package com.binh.motel.service;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.binh.motel.dto.CategoryDto;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.User;

import javassist.NotFoundException;

public interface CategoryService {
	/**
	 * Create a category
	 * 
	 * @param category A category to be created
	 * @return A Category
	 * @throws NotFoundException 
	 */
	public Category saveCategory(CategoryDto category) throws NotFoundException;

	/**
	 * Get a Category by given code
	 * 
	 * @param code
	 * @return
	 * @throws NotFoundException 
	 */
	public Category getCategoryByCode(String code) throws NotFoundException;

	/**
	 * Get all category
	 * @return
	 */
	public List<Category> getAll();
	
	public Set<Category> findChildrenByCode(String code);

	public Category get(String code);

	public void deleteCategory(String code) throws NotFoundException;

	public void editCategory(String code, @Valid CategoryDto dto);

}

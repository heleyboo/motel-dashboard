package com.binh.motel.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.binh.motel.dto.CategoryDto;
import com.binh.motel.entity.Category;
import com.binh.motel.repository.CategoryRepository;
import com.binh.motel.service.CategoryService;
import com.binh.motel.util.SlugUtil;

import javassist.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repo;

	@Override
	public Category saveCategory(CategoryDto categoryReq) throws NotFoundException {
		Category newCategory = new Category();
		newCategory.setCode(categoryReq.getCode());
		newCategory.setName(categoryReq.getName());
		newCategory.setPosition(categoryReq.getPosition());
		newCategory.setIsEnable(categoryReq.getIsEnable());
		newCategory.setIsVisible(categoryReq.getIsVisible());
		newCategory.setDescription(categoryReq.getDescription());
		newCategory.setMetaTagTitle(categoryReq.getMetaTagTitle());
		newCategory.setMetaTagDescription(categoryReq.getMetaTagDescription());
		newCategory.setMetaTagKeywords(categoryReq.getMetaTagKeywords());

		if (ObjectUtils.isEmpty(categoryReq.getSlug())) {
			newCategory.setSlug(SlugUtil.toSlug(categoryReq.getName()));
		}

		if (!ObjectUtils.isEmpty(categoryReq.getParent())) {
			Category parent = repo.findById(categoryReq.getParent())
					.orElseThrow(() -> new NotFoundException("Parent not found"));
			newCategory.setParent(parent);
			newCategory.setLevel(parent.getLevel() + 1);
		} else {
			newCategory.setLevel(0);
		}

		return repo.save(newCategory);
	}

	@Override
	public Category getCategoryByCode(String code) throws NotFoundException {
		return repo.findById(code).orElseThrow(() -> new NotFoundException("Category notfound"));
	}

	@Override
	public List<Category> getAll() {
		return repo.findAll();
	}

	@Override
	public Set<Category> findChildrenByCode(String code) {
		Set<Category> ret = new HashSet<Category>();
		
		Optional<Category> parent = repo.findById(code);
		
		if (parent.isPresent()) {
			ret = parent.get().getChildren();
		}
		return ret;
	}

}

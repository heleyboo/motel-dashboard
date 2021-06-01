package com.binh.motel.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.binh.motel.dto.CategoryDto;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.RoomImage;
import com.binh.motel.entity.User;
import com.binh.motel.repository.CategoryRepository;
import com.binh.motel.repository.MotelRoomRepository;
import com.binh.motel.repository.RoomImageRepository;
import com.binh.motel.service.CategoryService;
import com.binh.motel.util.SlugUtil;

import javassist.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repo;

	@Autowired
	private MotelRoomRepository postRepository;

	@Autowired
	private RoomImageRepository roomImageRepo;

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

	@Override
	public Category get(String code) {
		// TODO Auto-generated method stub
		return repo.findById(code).get();
	}

	@Override
	public void deleteCategory(String code) throws NotFoundException {

		Category category = getCategoryByCode(code);
		Set<Category> cats = category.getChildren();
		for (Category cat : cats) {
			deleteRooms(cat);
		}

		deleteRooms(category);

		repo.deleteAll(cats);
		repo.delete(category);
	}

	private void deleteRooms(Category category) {
		List<MotelRoom> motelRooms = category.getRooms();
		for (MotelRoom room : motelRooms) {
			List<RoomImage> images = room.getImages();
			roomImageRepo.deleteAll(images);
		}
		postRepository.deleteAll(motelRooms);
	}

}

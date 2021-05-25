package com.binh.motel.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.binh.motel.data.domain.FilterPageRequest;
import com.binh.motel.dto.UserFilter;
import com.binh.motel.entity.Role;
import com.binh.motel.entity.User;
import com.binh.motel.repository.RoleRepository;
import com.binh.motel.repository.UserRepository;
import com.binh.motel.service.UserService;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ModelMapper mapper;

	public User findUserByEmail(String email) throws NotFoundException {
		return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(""));
	}

	public User findUserByUserName(String userName) throws UsernameNotFoundException {
		return userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(""));
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public List<Role> getRoles() {
		return roleRepo.findAll();
	}

	@Override
	public Page<User> searchUsers(UserFilter userFilter) {
		Pageable page = FilterPageRequest.of(userFilter);
		Specification<User> spec = userFilter.buildSpec();
		return userRepository.findAll(spec, page);
	}

	public void updatePassword(String password, int userId) {
		userRepository.updatePassword(password, userId);
	}

	@Override
	public User findUserByUserNameOrEmail(String userName) throws UsernameNotFoundException {
		return userRepository.findByUserNameOrEmail(userName, userName)
				.orElseThrow(() -> new UsernameNotFoundException(""));
	}

}

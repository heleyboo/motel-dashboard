package com.binh.motel.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.binh.motel.dto.AdminUserDto;
import com.binh.motel.entity.Role;
import com.binh.motel.entity.User;
import com.binh.motel.repository.RoleRepository;
import com.binh.motel.repository.UserRepository;
import com.binh.motel.service.SetupService;

@Service
public class SetupServiceImpl implements SetupService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Override
	public void setup(AdminUserDto adminUser) {
		saveAdminUser(adminUser);
	}

	public User saveAdminUser(AdminUserDto userDto) {
		User user = new User();
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		user.setActive(true);
		user.setUserName(userDto.getUserName());
		user.setFirstName("Admin");
		user.setLastName("Admin");
		user.setPhoneNumber("");
		user.setEmail(userDto.getEmail());
		user.setActive(true);

		Role adminRole = roleRepo.findByRole("ROLE_ADMIN");
		if (null == adminRole) {
			adminRole = new Role("ROLE_ADMIN", "Quản trị");
			roleRepo.save(adminRole);
		}
		
		

		List<Role> roles = Arrays.asList(adminRole);
		Set<Role> setRoles = new HashSet<>(roles);
		user.setRoles(setRoles);

		return userRepository.save(user);
	}

	private void removeAllRoles() {
		List<Role> roles = roleRepo.findAll();
		roleRepo.deleteAll(roles);
	}

	private void removeAllUsers() {
		List<User> users = userRepository.findAll();
		userRepository.deleteAll(users);
	}

	private void createRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role("ROLE_ADMIN", "Quản trị"));
		roles.add(new Role("ROLE_MANAGER", "Quản lý cửa hàng"));
		roles.add(new Role("ROLE_STAFF", "Nhân viên"));
		roleRepo.saveAll(roles);
	}

}

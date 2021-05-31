package com.binh.motel.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.binh.motel.data.domain.FilterPageRequest;
import com.binh.motel.dto.UserDto;
import com.binh.motel.dto.UserFilter;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Role;
import com.binh.motel.entity.RoomImage;
import com.binh.motel.entity.User;
import com.binh.motel.repository.RoleRepository;
import com.binh.motel.repository.UserRepository;
import com.binh.motel.service.UserService;
import com.binh.motel.util.SlugUtil;

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

	@Override
	public User getUserById(int id) throws NotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User notfound"));
	}

	@Override
	public void approveUser(int userId) throws NotFoundException {
		User user = getUserById(userId);
		user.setActive(false);
		userRepository.save(user);
	}

//		@Override
//  	  public Optional<User> findUserById(int id) {  
//		    return userRepository.findById(id);  
//		  }  

	@Override
	public void deleteUser(int id) throws NotFoundException {

		User user = getUserById(id);
		userRepository.delete(user);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

//	@Override
//	public Role getRoleById(int id) throws NotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public void saveUser(@Valid UserDto userDto) throws NotFoundException {
		User newUser = new User();
		newUser.setEmail(userDto.getEmail());
		newUser.setFirstName(userDto.getFirstName());
		newUser.setUserName(userDto.getUserName());
		newUser.setLastName(userDto.getLastName());
		newUser.setPassword(userDto.getPassword());
		newUser.setPhoneNumber(userDto.getPhoneNumber());

		userRepository.save(newUser);

	}

	@Override
	public long countUsers() {
		return userRepository.count();
	}

}

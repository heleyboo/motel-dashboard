package com.binh.motel.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.binh.motel.entity.User;
import com.binh.motel.dto.UserDto;
import com.binh.motel.dto.UserFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Role;

import javassist.NotFoundException;

public interface UserService {
	public User findUserByEmail(String email) throws NotFoundException;

	public User findUserByUserName(String userName) throws UsernameNotFoundException;

	public User findUserByUserNameOrEmail(String userName) throws UsernameNotFoundException;

	public List<User> getAll();

	public List<Role> getRoles();
	
	public Page<User> searchUsers(UserFilter userFilter);

	public void toggleStatus(int id, boolean status) throws NotFoundException;
	
	
	public void deleteUser(int id) throws NotFoundException;
	
	User getUserById(int id) throws NotFoundException;
//	Role getRoleById(int id) throws NotFoundException;

//	Optional<User> findUserById(int id);

	public User get(int id);


	void saveUser(@Valid UserDto userDto) throws NotFoundException;


	public long countUsers();

	public void editUser(int id, @Valid UserDto userDto);
	
	

}

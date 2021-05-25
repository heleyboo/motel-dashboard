package com.binh.motel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.binh.motel.entity.User;
import com.binh.motel.dto.UserFilter;
import com.binh.motel.entity.Role;

import javassist.NotFoundException;

public interface UserService {
	public User findUserByEmail(String email) throws NotFoundException;

	public User findUserByUserName(String userName) throws UsernameNotFoundException;

	public User findUserByUserNameOrEmail(String userName) throws UsernameNotFoundException;

	public List<User> getAll();

	public List<Role> getRoles();
	
	public Page<User> searchUsers(UserFilter userFilter);
}

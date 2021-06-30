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
import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.UserDto;
import com.binh.motel.dto.UserFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.Comment;
import com.binh.motel.entity.Message;
import com.binh.motel.entity.MessageLine;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Role;
import com.binh.motel.entity.RoomImage;
import com.binh.motel.entity.User;
import com.binh.motel.repository.CommentRepository;
import com.binh.motel.repository.MessageLineRepository;
import com.binh.motel.repository.MessageRepository;
import com.binh.motel.repository.MotelRoomRepository;
import com.binh.motel.repository.RoleRepository;
import com.binh.motel.repository.RoomImageRepository;
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
	
	@Autowired
	private MotelRoomRepository motelRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private RoomImageRepository roomImageRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private MessageLineRepository messageLineRepo;

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
	public PageResponse<User> searchUsers(UserFilter userFilter) {
		Pageable pageAble = FilterPageRequest.of(userFilter);
		Specification<User> spec = userFilter.buildSpec();
		Page<User> paged = userRepository.findAll(spec, pageAble);
		return new PageResponse<User>(paged, userFilter);
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
	public Message getMessageById(int id) throws NotFoundException {
		return messageRepo.findById(id).orElseThrow(() -> new NotFoundException("Message notfound"));
	}


	@Override
	public void deleteUser(int id) throws NotFoundException {

		User user = getUserById(id);
		Message message = getMessageById(id);
		Set<MessageLine> messages = message.getMessageLines();
		List<MotelRoom> motels = user.getMotelRooms();
		for (MotelRoom room : motels) {
			List<Comment> comments = room.getComments();
			commentRepo.deleteAll(comments);
			List<RoomImage> images = room.getImages();
			roomImageRepo.deleteAll(images);
//			deleteMessages(room);

		}
		messageLineRepo.deleteAll(messages);
		messageRepo.delete(message);
		motelRepo.deleteAll(motels);
		userRepository.delete(user);
//		messageRepo.deleteAll(motels);
	}
	
//	private void deleteMessages(MotelRoom motel) {
//		Message message = motel.getMessage();
//		Set<MessageLine> mesLine = message.getMessageLines();
//		messageLineRepo.deleteAll(mesLine);
//	}



	@Override
	public User get(int id) {
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

	@Override
	public void toggleStatus(int id, boolean status) throws NotFoundException {
		User user = findById(id);
		user.setActive(status);
		userRepository.save(user);
		
	}

	private User findById(int id) throws NotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tồn tại phản hồi"));
	}

	@Override
	public void editUser(int id, @Valid UserDto userDto) {
		User newUser = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(""));
		newUser.setEmail(userDto.getEmail());
		newUser.setFirstName(userDto.getFirstName());
		newUser.setUserName(userDto.getUserName());
		newUser.setLastName(userDto.getLastName());
		newUser.setPassword(userDto.getPassword());
		newUser.setPhoneNumber(userDto.getPhoneNumber());

		userRepository.save(newUser);
		
	}
	

	@Override
	public void toggleDelete(int id, boolean status) throws NotFoundException {
		User user = findById(id);
		user.setDeleted(status);
		userRepository.save(user);
	}

	
	
	
}

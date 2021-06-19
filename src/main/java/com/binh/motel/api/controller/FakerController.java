package com.binh.motel.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.Comment;
import com.binh.motel.entity.District;
import com.binh.motel.entity.Message;
import com.binh.motel.entity.MessageLine;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Province;
import com.binh.motel.entity.Role;
import com.binh.motel.entity.RoomImage;
import com.binh.motel.entity.User;
import com.binh.motel.entity.Ward;
import com.binh.motel.enums.RoleEnum;
import com.binh.motel.enums.RoomDirection;
import com.binh.motel.repository.CategoryRepository;
import com.binh.motel.repository.CommentRepository;
import com.binh.motel.repository.MessageLineRepository;
import com.binh.motel.repository.MessageRepository;
import com.binh.motel.repository.MotelRoomRepository;
import com.binh.motel.repository.RoleRepository;
import com.binh.motel.repository.RoomImageRepository;
import com.binh.motel.repository.UserRepository;
import com.binh.motel.repository.WardRepository;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.DistrictService;
import com.binh.motel.service.MotelRoomService;
import com.binh.motel.service.ProvinceService;
import com.binh.motel.service.WardService;
import com.binh.motel.util.SlugUtil;
import com.github.javafaker.Faker;

@Controller
@RequestMapping("/api/v1/fake")
public class FakerController {
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private WardService wardService;
	
	@Autowired WardRepository repo;
	
	@Autowired CategoryRepository categoryRepo;
	
	@Autowired MotelRoomRepository roomRepo;
	
	@Autowired AuthenticationService authService;
	
	@Autowired RoomImageRepository roomImageRepo;
	
	@Autowired CommentRepository commentRepo;
	
	@GetMapping
	public String createRooms() {
		
		
		List<Ward> wards = repo.findAll();
		List<Category> categories = categoryRepo.findAll();
		List<MotelRoom> motels = new ArrayList<MotelRoom>();
		Faker faker = new Faker(new Locale("vi-VN"));
		for (int i = 0; i < 100; i++) {
			MotelRoom motelRoom = createRandomMotelRoom(faker, wards, categories);
			motelRoom.setCreatedBy(authService.currentUser());
			MotelRoom savedRoom = roomRepo.save(motelRoom);
			
			int imgIndex= faker.random().nextInt(1, 7);
			List<RoomImage> images = new ArrayList<RoomImage>();
			for (int j = imgIndex; j <= imgIndex + 2; j++) {
				RoomImage ri = new RoomImage();
				ri.setUrl(String.format("/images/%s.jpg", Integer.toString(j)));
				ri.setRoom(savedRoom);
				images.add(ri);
			}
			
			roomImageRepo.saveAll(images);
			
		}
		
		return "redirect:/administrator/dashboard";
		
	}
	
	private MotelRoom createRandomMotelRoom(Faker faker, List<Ward> wards, List<Category> categories) {
		
		MotelRoom motelRoom = new MotelRoom();
		motelRoom.setPrice(BigDecimal.valueOf(faker.number().numberBetween(2000000, 10000000)));
		motelRoom.setArea(faker.random().nextInt(20, 50));
		motelRoom.setDepositAmount(BigDecimal.valueOf(faker.number().numberBetween(2000000, 10000000)));
		
		List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
		
		int indexDr = faker.random().nextInt(0, directions.size() - 1);
		int indexDr1 = faker.random().nextInt(0, directions.size() - 1);
		
		motelRoom.setBalconyDirection(directions.get(indexDr1));
		motelRoom.setDoorDirection(directions.get(indexDr));
		motelRoom.setNumOfBedrooms(faker.random().nextInt(1, 4));
		motelRoom.setNumOfToilets(faker.random().nextInt(1, 2));
		motelRoom.setDescription(faker.address().fullAddress());
		
		int wardIndex = faker.random().nextInt(0, wards.size() - 1);
		Ward ward = wards.get(wardIndex);
		
		District district = ward.getDistrict();
		
		Province province = district.getProvince();
		
		String address = faker.address().buildingNumber() + "," + faker.address().streetName();
		
		
		String fullAddress = String.format(
				"%s, %s, %s, %s",
				address,
				ward.getNameWithType(),
				district.getNameWithType(),
				province.getNameWithType());
		
		motelRoom.setFullAddress(fullAddress);
		motelRoom.setProvince(province.getNameWithType());
		
		motelRoom.setWardCode(ward.getCode());
		motelRoom.setDistrictCode(district.getCode());
		motelRoom.setProvinceCode(province.getCode());
		
		motelRoom.setTitle("Cho thuê phòng trọ " + address);
		
		int categoryIndex = faker.random().nextInt(0, categories.size() - 1);
		motelRoom.setCategory(categories.get(categoryIndex));
		motelRoom.setAddress(address);
		motelRoom.setPhoneNumber(faker.phoneNumber().phoneNumber());
		motelRoom.setSlug(SlugUtil.toSlug(fullAddress));
		motelRoom.setApprove(false);
		
		
		return motelRoom;
	}
	
	@Autowired MotelRoomService roomService;
	@GetMapping("/comments")
	public String fakeComments() {
		Faker faker = new Faker(new Locale("vi-VN"));
		RoomFilter roomRilter = new RoomFilter("0", "100", null);
		PageResponse<MotelRoom> paged = roomService.searchRooms(roomRilter);
		
		List<MotelRoom> rooms = paged.getPageData().getContent();
		int index = faker.random().nextInt(0, rooms.size() - 1);
		MotelRoom motelRoom = rooms.get(index);
		if (null != motelRoom) {

			List<Comment> comments = new ArrayList<Comment>();
			for (int i = 0; i < 100; i++) {
				
				Comment cmt = new Comment();
				cmt.setEmail(faker.bothify(("????##@gmail.com")));
				cmt.setPhoneNumber(faker.phoneNumber().phoneNumber());
				cmt.setCommentedBy(faker.name().fullName());
				cmt.setContent(faker.address().fullAddress());
				cmt.setRoom(motelRoom);
				comments.add(cmt);
			}
			
			commentRepo.saveAll(comments);
		}
		
		return "redirect:/administrator/comment/list";
	}
	
	@GetMapping("/dates")
	public String fakeDates() {
		List<MotelRoom> allRooms = roomRepo.findAll();
		Faker faker = new Faker(new Locale("vi-VN"));
		for (Iterator iterator = allRooms.iterator(); iterator.hasNext();) {
			MotelRoom motelRoom = (MotelRoom) iterator.next();
			int ranromIdx = faker.random().nextInt(1, 10);
			Date past = faker.date().past(ranromIdx, TimeUnit.DAYS);
			motelRoom.setCreatedDate(past);
			
		}
		roomRepo.saveAll(allRooms);
		return "redirect:/";
	}
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private MessageLineRepository msgLineRepo;
	
	@GetMapping("/messages")
	public String fakeMessages() {
		List<MotelRoom> allRooms = roomRepo.findAll();
		Role userRole = roleRepo.findByRole(RoleEnum.ROLE_USER.toString());
		List<User> allUsers = userRepo.findByRoles(userRole);
		Faker faker = new Faker(new Locale("vi-VN"));
		
		int idxUser = faker.random().nextInt(1, allUsers.size() - 1);
		
		for (int i=0; i<10; i++) {
			
			MotelRoom room = allRooms.get(i);
			User hostuser = allUsers.get(1);
			User clientUser = allUsers.get(idxUser);
			String uuidStr = room.getTitle() + hostuser.getUserName() + clientUser.getUserName();
			String uuid = Base64.getEncoder().encodeToString(uuidStr.getBytes());
			Message message = messageRepo.findByUuid(uuid).orElse(new Message());
			message.setMotelRoom(room);
			message.setHost(hostuser);
			message.setClient(clientUser);
			message.setUuid(uuid);
			
			messageRepo.save(message);
			
			int numOfMessages = faker.random().nextInt(5, 15);
			for (int j=0; j<numOfMessages; j++) {
				MessageLine messageLine = new MessageLine();
				messageLine.setContent(faker.address().firstName());
				messageLine.setCreatedDate(faker.date().past(3, TimeUnit.HOURS));
				if (j%2==0) {
					messageLine.setUser(clientUser);
				} else {
					messageLine.setUser(hostuser);
				}
				messageLine.setMessage(message);
				msgLineRepo.save(messageLine);
			}
			
		}
		
		
		return "redirect:/";
	}

}

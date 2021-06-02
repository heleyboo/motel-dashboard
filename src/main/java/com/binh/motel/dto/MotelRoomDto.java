package com.binh.motel.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.binh.motel.entity.RoomType;
import com.binh.motel.enums.RoomDirection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotelRoomDto {
	
//	@NotEmpty
//	private RoomType roomType;
	
	private int id;
	
 	@NotEmpty(message = "Tiêu đề bắt buộc nhập")
	private String title;
 	
	private String description;

	@NotNull(message = "Giá bắt buộc nhập")
	private double price;
	
	private double depositAmount;

	@NotNull(message = "Diện tích bắt buộc nhập")
	private float area;
	
	@NotNull(message = "Vui lòng nhập số phòng ngủ")
	private int numOfBedrooms;
	
	@NotNull(message = "Vui lòng nhập số toilets")
	private int numOfToilets;
	
	private RoomDirection doorDirection;
	
	private RoomDirection balconyDirection;

	@NotEmpty(message = "Địa chỉ bắt buộc nhập")
	private String address;

	private String latlng;
	
	private String googleMapUrl;

	 @NotEmpty(message = "Danh mục bắt buộc nhập")
	private String category;

	@NotEmpty(message = "Xã/phường bắt buộc nhập")
	private String ward;

	private String utilities;

	@NotEmpty(message = "SDT bắt buộc nhập")
	private String phoneNumber;

	private String slug;
	
//	private List<String> images;
	MultipartFile[] files;
	
	private int status;
}

package com.binh.motel.dto;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	
	private int id;
	
	
	private String userName;
	
	
	private String firstName;
	
	private String email;
	
	private Boolean active;
	
	private String phoneNumber;
	
	private String password;
	
	private String lastName;
	
	

}

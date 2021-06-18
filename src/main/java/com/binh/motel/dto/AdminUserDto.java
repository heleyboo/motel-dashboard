package com.binh.motel.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.binh.motel.validator.PasswordsEqualConstraint;
import com.binh.motel.validator.UniqueUserEmail;
import com.binh.motel.validator.UniqueUserName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PasswordsEqualConstraint(message = "Mật khẩu lặp lại không chính xác", passwordRepeatFieldName = "passwordRepeat")
public class AdminUserDto implements Serializable, UserPassword {

	private static final long serialVersionUID = 6922418863750892394L;
	
	@NotEmpty(message = "*Vui lòng nhập tên đăng nhập")
	@UniqueUserName
	private String userName;

	@NotEmpty(message = "*Mật khẩu bắt buộc nhập")
	private String password;

	@NotEmpty(message = "*Nhập lại mật khẩu")
	private String passwordRepeat;

	@NotEmpty(message = "*Nhập địa chỉ email")
	@Email(message = "Địa chỉ email không đúng định dạng")
	@UniqueUserEmail
	private String email;
	
	@NotEmpty(message = "*Mật khẩu bắt buộc nhập")
	private String firstName;

	@NotEmpty(message = "*Nhập lại mật khẩu")
	private String lastName;

}

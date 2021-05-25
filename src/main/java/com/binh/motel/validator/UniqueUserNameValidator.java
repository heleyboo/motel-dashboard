package com.binh.motel.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.binh.motel.repository.UserRepository;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

	@Autowired
	private UserRepository userRepo;
	
	@Override
    public void initialize(UniqueUserName uniqueUserName) {
    }
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !userRepo.existsByUserName(value);
	}

}

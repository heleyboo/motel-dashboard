package com.binh.motel.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.binh.motel.repository.UserRepository;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

	@Autowired
	private UserRepository userRepo;
	
	@Override
    public void initialize(UniqueUserEmail email) {
    }
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !userRepo.existsByEmail(value);
	}

}

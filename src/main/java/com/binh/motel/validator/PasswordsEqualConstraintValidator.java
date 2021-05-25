package com.binh.motel.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.binh.motel.dto.UserPassword;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordsEqualConstraint, Object> {

	private String passwordRepeatFieldName;

	@Override
	public void initialize(final PasswordsEqualConstraint constraintAnnotation) {
		passwordRepeatFieldName = constraintAnnotation.passwordRepeatFieldName();
	}

	@Override
	public boolean isValid(Object candidate, ConstraintValidatorContext context) {
		UserPassword user = (UserPassword) candidate;
		
		if (!StringUtils.hasText(user.getPasswordRepeat())) {
			return true;
		}
		
		boolean isValid = user.getPassword().equals(user.getPasswordRepeat());
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
					.addPropertyNode(passwordRepeatFieldName).addConstraintViolation();
		}
		return isValid;
	}
}
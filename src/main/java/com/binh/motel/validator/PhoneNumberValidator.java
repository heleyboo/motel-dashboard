package com.binh.motel.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	@Override
    public void initialize(PhoneNumber phoneNumber) {
    }

    @Override
    public boolean isValid(String contactField,
      ConstraintValidatorContext cxt) {
    	if (!StringUtils.hasText(contactField)) {
    		return true;
    	}
        return StringUtils.hasText(contactField) && contactField.matches("[0-9]+")
          && (contactField.length() > 8) && (contactField.length() < 14);
    }

}

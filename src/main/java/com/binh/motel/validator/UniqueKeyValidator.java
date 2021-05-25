package com.binh.motel.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, Object> {
	
	@Autowired
    private ApplicationContext applicationContext;

    private FieldValueExists service;
    private String fieldName;
    private boolean isEdit;

    @Override
    public void initialize(UniqueKey unique) {
        Class<? extends FieldValueExists> clazz = unique.service();
        this.fieldName = unique.fieldName();
        this.isEdit = unique.isEdit();
        String serviceQualifier = unique.serviceQualifier();

        if (!serviceQualifier.equals("")) {
            this.service = this.applicationContext.getBean(serviceQualifier, clazz);
        } else {
            this.service = this.applicationContext.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = !this.service.fieldValueExists(o, this.fieldName, this.isEdit);
        if(!isValid){
        	constraintValidatorContext.disableDefaultConstraintViolation();
        	constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                   .addPropertyNode(this.fieldName).addConstraintViolation();
       }
        return isValid;
    }
}
package org.springframework.samples.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNegativeValidator implements ConstraintValidator<NotNegative, Integer> {


	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == null) 
			return false;
		
		if (value < 0) {
			return false;
        } else {
        	return true;
        }
	}

	@Override
	public void initialize(NotNegative constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}

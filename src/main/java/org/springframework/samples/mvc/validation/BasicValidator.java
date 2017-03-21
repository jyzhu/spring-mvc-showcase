package org.springframework.samples.mvc.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BasicValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return JavaBean.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
		
		JavaBean javabean = (JavaBean)arg0;
		if (javabean.getNumber() < 0) {
			errors.rejectValue("number", "negativevalue");
        } 
	}

}

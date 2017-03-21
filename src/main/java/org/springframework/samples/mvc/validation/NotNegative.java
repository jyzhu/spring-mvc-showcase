package org.springframework.samples.mvc.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {NotNegativeValidator.class})
public @interface NotNegative {
	String message() default "Not negative allow";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default {};

}

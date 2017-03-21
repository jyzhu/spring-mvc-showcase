package org.springframework.samples.mvc.validation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ValidationController {

	@Autowired
	BasicValidator validator;
	// enforcement of constraints on the JavaBean arg require a JSR-303 provider on the classpath
	
	@RequestMapping("/validate")
	public @ResponseBody String validate(@Valid JavaBean bean, BindingResult result) {
		validator.validate(bean, result);
		
		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";
		}
	}

	@RequestMapping("/validateAnnotation")
	public @ResponseBody String validateAnnotation(@Valid JavaBean bean, BindingResult result) {
		
		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";
		}
	}

}

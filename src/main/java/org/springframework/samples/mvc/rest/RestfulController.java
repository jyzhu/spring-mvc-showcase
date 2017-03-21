package org.springframework.samples.mvc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class RestfulController {
	
    @RequestMapping(value="/user/nopassword", method=RequestMethod.GET)
    @JsonView(User.WithoutPasswordView.class)
    public User getUserNoPassword() {
        return new User("eric", "7!jd#h23");
    }
    
    @RequestMapping(value="/user/password", method=RequestMethod.GET)
    @JsonView(User.WithPasswordView.class)
    public User getUserPassword() {
        return new User("eric", "7!jd#h23");
    }
}

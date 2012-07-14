package org.srini.stayintouch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.srini.stayintouch.controllers.model.User;
import org.srini.stayintouch.services.LoginService;

@Controller
public class ReSTController {

	@Autowired
	LoginService loginService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/rest/testRest/{email}", method=RequestMethod.GET)
	public ModelAndView home(@PathVariable("email") String email) {
		
		User user = loginService.findUserbyEmail(email);
		ModelAndView mav = new ModelAndView("stayIntouchWS", BindingResult.MODEL_KEY_PREFIX + "user", user);
		return mav;
	}
}

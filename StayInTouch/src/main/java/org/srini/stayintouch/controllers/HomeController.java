package org.srini.stayintouch.controllers;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.srini.stayintouch.services.HomeService;
import org.srini.stayintouch.services.LoginService;
import org.srini.stayintouch.validators.UserDetailsValidator;
import org.srini.stayintouch.validators.UserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name = "homeService")
	private HomeService homeService;
	
	@Resource(name = "loginService")
	private LoginService loginService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/welcome", method=RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model, Principal principal) {
		
		if(request.isUserInRole("ROLE_USER")){
			logger.info("The user logged in is: "+principal.getName());
			return "main";
		}
		
		String userAgent = request.getHeader("user-agent");
		logger.info("The User Logged in using user agent "+userAgent);
		
		UserDetailsValidator userDetailValidator = new UserDetailsValidator();
		List<Integer> years = homeService.getYears();
		userDetailValidator.setYears(years);
		
		Map<Integer, String> months = homeService.getMonths();
		userDetailValidator.setMonths(months);
		
		model.addAttribute("userDetailsValidator", userDetailValidator);
		model.addAttribute("year", new Integer(0));
		model.addAttribute("month", new Integer(0));
		
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/ajax/getDays", method=RequestMethod.GET)
	public @ResponseBody String getDays(@RequestParam(value = "year", required = true) Integer year,
			@RequestParam(value = "month", required = true) String month, Model model) {
		logger.info("Welcome to Stay in touch!");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, (Integer.parseInt(month) - 1), 1);
		
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		String str = "<td id='dayTableCell'><select id='day' name='day'>";
		str += "<option>Day:</option>";
		
		for(int i = 1; i <= maxDays; i++){
			str += "<option value="+i+">"+i+"</option>";
		}
		
		str += "</select></td>";
		
		return str;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/ajax/checkForExistingId", method=RequestMethod.GET)
	public @ResponseBody String checkForExistingId(@RequestParam(value = "entryEmail", required = true) String entryEmail, Model model) {
		logger.info("Checking if the email "+entryEmail+" already exists");
		boolean exists = loginService.findUserbyName(entryEmail);
		
		
		if(exists){
			String str = "<td colspan='3' align='center' bgcolor='orange' id='errorRow' >";
			str += "Email: "+entryEmail+"  already exists.";
			str += "</td>";
			return str;
		}else{
			String str = "<td colspan='3' align='center' id='errorRow' >";
			str += "</td>";
			return str;
		}
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		String userAgent = request.getHeader("user-agent");
		logger.info("The User Logged in using user agent "+userAgent);
		
		model.addAttribute("userValidator", new UserValidator());
		
		UserDetailsValidator userDetailValidator = new UserDetailsValidator();
		List<Integer> years = homeService.getYears();
		userDetailValidator.setYears(years);
		
		Map<Integer, String> months = homeService.getMonths();
		userDetailValidator.setMonths(months);
		
		model.addAttribute("userDetailsValidator", userDetailValidator);
		model.addAttribute("year", new Integer(0));
		model.addAttribute("month", new Integer(0));
		
		return "home";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/main", method=RequestMethod.GET)
	public String gotoMain(Principal principal, UserValidator userValidator, HttpServletRequest request) {
		logger.info("The user logged in is: "+principal.getName());
		return "main";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/loginFailed", method=RequestMethod.GET)
	public String loginFailed(ModelMap model) {
		logger.info("Login Failed");
		UserValidator userValidator = new UserValidator();
		model.addAttribute("userValidator", userValidator);
		userValidator.setAuthSuccess("failed");
		return "user/login";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(@ModelAttribute("userValidator") @Valid UserValidator userValidator, BindingResult result, UserDetailsValidator userDetailsValidator, BindingResult result1) {
		if(result.hasErrors()){
			return "user/login";
		}else{
			boolean success = loginService.findUserbyNameandPassword(userValidator.getUserName(), userValidator.getPassword());
			if(success == false){
				result.rejectValue("loginError", "org.srini.stayintouch.validators.UserValidator.userName", new Object[]{}, "The system does not know this combination.");
				userValidator.setAuthSuccess("failed");
				return "user/login";
			}
			else{
				return "main";
			}
		}
	}*/
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/app/spring/signup", method=RequestMethod.POST)
	public ModelAndView signupAction(@ModelAttribute("userDetailsValidator") @Valid UserDetailsValidator userDetailsValidator, BindingResult result, UserValidator userValidator, BindingResult result1, Model model)  throws Exception {
		
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("userDetailsValidator", userDetailsValidator);
		modelView.addObject("userValidator", userValidator);
		
		if(result.hasErrors()){
			logger.info(""+result.getAllErrors());
			List<ObjectError> allErrors = result.getAllErrors();
			if(!allErrors.isEmpty()){
				ObjectError firstError = allErrors.get(0);
				logger.info("First error code "+firstError.getCode());
				result.rejectValue("signUpError", firstError.getCode(), firstError.getDefaultMessage());
			}
			
			setDateValues(userDetailsValidator);
			model.addAttribute("userDetailsValidator", userDetailsValidator);
			modelView.setViewName("home");
		}else{
			
			boolean userExists = loginService.findUserbyName(userDetailsValidator.getEntryEmail());
			logger.info("Does the user "+userDetailsValidator.getEntryEmail()+" Exists "+userExists);
			
			if(!userDetailsValidator.getReenterPassword().equals(userDetailsValidator.getEnterPassword())){
				result.rejectValue("signUpError", "org.srini.stayintouch.validators.UserValidator.password", "Password did not match");
				logger.info("Password did not match");
				userValidator.setAuthSuccess("failed");
				setDateValues(userDetailsValidator);
				model.addAttribute("userDetailsValidator", userDetailsValidator);
				modelView.setViewName("home");
			}else if(userExists){
				result.rejectValue("signUpError", "org.srini.stayintouch.validators.UserValidator.userName", "Email: "+userDetailsValidator.getEntryEmail()+"  already exists.");
				logger.info("User Name aleady exists");
				userValidator.setAuthSuccess("failed");
				setDateValues(userDetailsValidator);
				model.addAttribute("userDetailsValidator", userDetailsValidator);
				modelView.setViewName("home");
			}else{
				logger.info("Everything alright \r\n"+userDetailsValidator);
				
				boolean saved = loginService.save(userDetailsValidator);
				
				logger.info("User saved "+saved);
				modelView.setViewName("user/login");
				userValidator.setAuthSuccess("successful");
				return modelView;
			}
		}

		return modelView;
	}
	
	private void setDateValues(UserDetailsValidator userDetailsValidator){
		List<Integer> years = homeService.getYears();
		userDetailsValidator.setYears(years);
		Map<Integer, String> months = homeService.getMonths();
		userDetailsValidator.setMonths(months);
		userDetailsValidator.setDays(homeService.getDays(userDetailsValidator.getYear(), userDetailsValidator.getMonth()));
	}
	
	@ModelAttribute("userValidator")
	 public UserValidator getGreetingObject() {
	  return new UserValidator();
	 }
}


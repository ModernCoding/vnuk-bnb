package vn.edu.vnuk.bnb.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vnuk.bnb.dao.CountryDao;
import vn.edu.vnuk.bnb.dao.IdentificationTypeDao;
import vn.edu.vnuk.bnb.dao.UserDao;
import vn.edu.vnuk.bnb.dao.UserTypeDao;
import vn.edu.vnuk.bnb.model.User;


public class UsersController {
	
	private UserDao userDao;
	private UserTypeDao userTypeDao;
	private IdentificationTypeDao identificationTypeDao;
	private CountryDao countryDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setUserTypesDao(UserTypeDao userTypeDao) {
		this.userTypeDao = userTypeDao;
	}
	
	@Autowired
	public void setIdentificationTypesDao(IdentificationTypeDao identificationTypeDao) {
		this.identificationTypeDao = identificationTypeDao;
	}
	
	@Autowired
	public void setCountryId(CountryDao countryDao) {
		this.countryDao = countryDao;
	}
	

	@RequestMapping("/users")
    public String index(
		
		@RequestParam(value="userTypeId", required = false) String userTypeId,
		@RequestParam(value="identificationTypeId", required = false) String identificationTypeId,
		@RequestParam(value="countryId", required = false) String countryId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("users", userDao.read(userTypeId, identificationTypeId, countryId));
		
		if (userTypeId != null && identificationTypeId!= null && countryId !=null ) {
			model.addAttribute("userType", userTypeDao.read(Long.parseLong(userTypeId)));
			model.addAttribute("identificationType", identificationTypeDao.read(Long.parseLong(identificationTypeId)));
			model.addAttribute("country", countryDao.read(Long.parseLong(countryId)));
		}
		
        model.addAttribute("template", "users/index");
        return "_layout";
   
	}
	
	 @RequestMapping("/users/{id}")
	    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
	        model.addAttribute("user", userDao.read(id));
	        model.addAttribute("template", "users/show");
	        return "_layout";
	    }
	 
	 @RequestMapping("/users/new")
	    public String add(
	    		
			User user,
			Model model,
			@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
		
		) throws SQLException{
	    	
	    	for(FieldError fieldError : fieldErrors) {
	    		model.addAttribute(
	    				String.format("%sFieldError", fieldError.getField()),
	    				fieldError.getDefaultMessage()
	    			);
	    	}
	    	
	    	model.addAttribute("template", "users/new");
	    	model.addAttribute("userType", userTypeDao.read());
	    	model.addAttribute("identificationType", identificationTypeDao.read());
	        model.addAttribute("country", countryDao.read());
	        return "_layout";
	    }
	
	 @RequestMapping("/users/{id}/edit")
	    public String edit(
	    		
			@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
			@PathVariable("id") Long id,
			User user,
			Model model,
			ServletRequest request,
			@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
			
		) throws SQLException{
	    	
	    	
	    	for(FieldError fieldError : fieldErrors) {
	    		model.addAttribute(
	    				String.format("%sFieldError", fieldError.getField()),
	    				fieldError.getDefaultMessage()
	    			);
	    	}
	    	
	    	
	    	model.addAttribute("backToShow", backToShow);
	    	model.addAttribute("urlCompletion", backToShow ? String.format("/%s", id) : "");
	    	model.addAttribute("user", userDao.read(id));
	    	model.addAttribute("userType", userTypeDao.read());
	    	model.addAttribute("identificationType", identificationTypeDao.read());
	    	model.addAttribute("country", countryDao.read());
	        model.addAttribute("template", "user/edit");
	        return "_layout";
	    
	    }
	 
	 @RequestMapping(value="/users", method=RequestMethod.POST)
	    public String create(
			
	    	@Valid User user,
	    	BindingResult bindingResult,
	    	ServletRequest request,
	    	RedirectAttributes redirectAttributes
	    
	    ) throws SQLException{
	    	
	        if (bindingResult.hasErrors()) {
	        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
	            return "redirect:/users/new";
	        }
	        
	        
	        userDao.create(user);
	        return "redirect:/users";
	        
	    }
	 
	 
	 @RequestMapping(value="/users/{id}", method=RequestMethod.PATCH)
	    public String update(
	    		
	    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
	    		@PathVariable("id") Long id,
	    		@Valid User user,
	    		BindingResult bindingResult,
	    		ServletRequest request,
	    		RedirectAttributes redirectAttributes
	    		
	    	) throws SQLException{
	    	
	        
	    	if (bindingResult.hasErrors()) {
	        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
	            return String.format("redirect:/users/%s/edit", id);
	        }
	        
	        userDao.update(user);
	        return backToShow ? String.format("redirect:/users/%s", id) : "redirect:/users";
	        
	        
	    }
	    
	    
	    //  delete with ajax
	    @RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
	    	userDao.delete(id);
	        response.setStatus(200);
	    }
}
	
	
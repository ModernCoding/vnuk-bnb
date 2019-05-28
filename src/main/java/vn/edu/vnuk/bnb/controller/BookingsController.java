package vn.edu.vnuk.bnb.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vnuk.bnb.dao.BookingDao;
import vn.edu.vnuk.bnb.dao.RoomDao;
import vn.edu.vnuk.bnb.dao.UserDao;
import vn.edu.vnuk.bnb.model.Booking;


/**
 *
 * @author michel
 */
@Controller
public class BookingsController {
	
	private BookingDao bookingDao;
	private UserDao userDao;
	private RoomDao roomDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setRoomsDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	@RequestMapping("/bookings")
    public String index(
		
		@RequestParam(value="userId", required = false) String userId,
		@RequestParam(value="roomId", required = false) String roomId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("bookings", bookingDao.read(userId,roomId));
		
		if (userId != null && roomId != null) {
			model.addAttribute("user", userDao.read(Long.parseLong(userId)));
			model.addAttribute("room", roomDao.read(Long.parseLong(roomId)));
		}
		
        model.addAttribute("template", "bookings/index");
        return "_layout";
   
	}
	
	@RequestMapping("/bookings/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("booking", bookingDao.read(id));
        model.addAttribute("template", "bookings/show");
        return "_layout";
    }
	
	 @RequestMapping("/bookings/new")
	    public String add(
	    		
	    		
	    	Booking booking,
	    	@RequestParam(value="identificationTypesId", required = false) String identificationTypesId,
			@RequestParam(value="countryId", required = false) String countryId,
			@RequestParam(value="userTypesId", required = false) String userTypesId,
			@RequestParam(value="roomTypesId", required = false) String roomTypesId,
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
	    	
	    	model.addAttribute("template", "bookings/new");
	    	model.addAttribute("user", userDao.read(userTypesId, identificationTypesId, countryId));
	    	model.addAttribute("room", roomDao.read(roomTypesId));
	        return "_layout";
	    }
	 
	 @RequestMapping("/bookings/{id}/edit")
	    public String edit(
	    		
			@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
			@PathVariable("id") Long id,
			Booking booking,
			Model model,
			@RequestParam(value="identificationTypesId", required = false) String identificationTypesId,
			@RequestParam(value="countryId", required = false) String countryId,
			@RequestParam(value="userTypesId", required = false) String userTypesId,
			@RequestParam(value="roomTypesId", required = false) String roomTypesId,
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
	    	model.addAttribute("booking", bookingDao.read(id));
	    	model.addAttribute("user", userDao.read(userTypesId, identificationTypesId, countryId));
	    	model.addAttribute("room", roomDao.read(roomTypesId));
	        model.addAttribute("template", "bookings/edit");
	        return "_layout";
	    
	    }
	 
	 @RequestMapping(value="/bookings", method=RequestMethod.POST)
	    public String create(
			
	    	@Valid Booking booking,
	    	BindingResult bindingResult,
	    	ServletRequest request,
	    	RedirectAttributes redirectAttributes
	    
	    ) throws SQLException{
	    	
	        if (bindingResult.hasErrors()) {
	        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
	            return "redirect:/bookings/new";
	        }
	        
	        
	        bookingDao.create(booking);
	        return "redirect:/bookings";
	        
	    }
	 
	 @RequestMapping(value="/bookings/{id}", method=RequestMethod.PATCH)
	    public String update(
	    		
	    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
	    		@PathVariable("id") Long id,
	    		@Valid Booking booking,
	    		BindingResult bindingResult,
	    		ServletRequest request,
	    		RedirectAttributes redirectAttributes
	    		
	    	) throws SQLException{
	    	
	        
	    	if (bindingResult.hasErrors()) {
	        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
	            return String.format("redirect:/bookings/%s/edit", id);
	        }
	        
	        bookingDao.update(booking);
	        return backToShow ? String.format("redirect:/bookings/%s", id) : "redirect:/bookings";
	        
	        
	    }
	 
	 //  delete with ajax
	    @RequestMapping(value="/bookings/{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
	    	bookingDao.delete(id);
	        response.setStatus(200);
	    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
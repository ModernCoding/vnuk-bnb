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

import vn.edu.vnuk.bnb.dao.BillDao;
import vn.edu.vnuk.bnb.dao.BookingDao;
import vn.edu.vnuk.bnb.dao.UserDao;
import vn.edu.vnuk.bnb.model.Bill;



public class BillsController {
	
	private BillDao billDao;
	private BookingDao bookingDao;
	private UserDao userDao;
	
	@Autowired
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping("/bills")
    public String index(
		
		@RequestParam(value="bookingId", required = false) String bookingId,
		@RequestParam(value="userId", required = false) String userId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("bills", billDao.read(bookingId, userId));
		
		if (bookingId != null && userId!= null) {
			model.addAttribute("booking", bookingDao.read(Long.parseLong(bookingId)));
			model.addAttribute("user", userDao.read(Long.parseLong(userId)));
		}
		
        model.addAttribute("template", "bills/index");
        return "_layout";
   
	}
	
	@RequestMapping("/bills/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("bill", bookingDao.read(id));
        model.addAttribute("bill", userDao.read(id));
        return "_layout";
    }
 
 @RequestMapping("/bills/new")
    public String add(
    		
		Bill bill,
		@RequestParam(value="roomId", required = false) String roomId,
		@RequestParam(value="userId", required = false) String userId,
		@RequestParam(value="userTypesId", required = false) String userTypesId,
		@RequestParam(value="identificationTypesId", required = false) String identificationTypesId,
		@RequestParam(value="countryId", required = false) String countryId,
		ServletRequest request,
		
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "bills/new");
    	model.addAttribute("booking", bookingDao.read(roomId, userId));
    	model.addAttribute("user", userDao.read(userTypesId, identificationTypesId, countryId));
        return "_layout";
    }
 
 @RequestMapping("/bills/{id}/edit")
 public String edit(
 		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Bill bill,
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
 	model.addAttribute("bill", billDao.read(id));
 	model.addAttribute("booking", bookingDao.read(id));
 	model.addAttribute("user", userDao.read(id));
     model.addAttribute("template", "bills/edit");
     return "_layout";
 
 }
 
 @RequestMapping(value="/bills", method=RequestMethod.POST)
 public String create(
		
 	@Valid Bill bill,
 	BindingResult bindingResult,
 	ServletRequest request,
 	RedirectAttributes redirectAttributes
 
 ) throws SQLException{
 	
     if (bindingResult.hasErrors()) {
     	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
         return "redirect:/bills/new";
     }
     
     
     billDao.create(bill);
     return "redirect:/bills";
     
 }


@RequestMapping(value="/bills/{id}", method=RequestMethod.PATCH)
 public String update(
 		
 		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
 		@PathVariable("id") Long id,
 		@Valid Bill bill,
 		BindingResult bindingResult,
 		ServletRequest request,
 		RedirectAttributes redirectAttributes
 		
 	) throws SQLException{
 	
     
 	if (bindingResult.hasErrors()) {
     	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
         return String.format("redirect:/bills/%s/edit", id);
     }
     
     billDao.update(bill);
     return backToShow ? String.format("redirect:/bills/%s", id) : "redirect:/bills";
     
     
 }
 
 
 //  delete with ajax
 @RequestMapping(value="/bills/{id}", method = RequestMethod.DELETE)
 public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
 	billDao.delete(id);
     response.setStatus(200);
 }
	
}
	
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import vn.edu.vnuk.bnb.dao.ServiceDao;
import vn.edu.vnuk.bnb.dao.BookingServiceDao;
import vn.edu.vnuk.bnb.model.BookingService;

/**
 *
 * @author michel
 */
@Controller
public class BookingServicesController {
	
	private BookingServiceDao bookingServiceDao;
	private BookingDao bookingDao;
	private ServiceDao serviceDao;

	@Autowired
	public void setBookingServiceDao(BookingServiceDao bookingServiceDao) {
		this.bookingServiceDao = bookingServiceDao;
	}

	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	
	@RequestMapping("/booking-services")
    public String index(
		
		@RequestParam(value="bookingId", required = false) String bookingId,
		@RequestParam(value="serviceId", required = false) String serviceId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("bookingServices", bookingServiceDao.read(bookingId,serviceId));
		
		if (bookingId != null && serviceId != null) {
			model.addAttribute("booking", bookingDao.read(Long.parseLong(bookingId)));
			model.addAttribute("service", serviceDao.read(Long.parseLong(serviceId)));
		}
		
        model.addAttribute("template", "bookingServices/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/booking-services/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("bookingService", bookingDao.read(id));
        model.addAttribute("bookingService", serviceDao.read(id));
        model.addAttribute("template", "rooms/show");
        return "_layout";
    }
    
    
    @RequestMapping("/booking-services/new")
    public String add(
    		
		BookingService bookingService,
		@RequestParam(value="roomId", required = false) String roomId,
		@RequestParam(value="userId", required = false) String userId,
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
    	
    	model.addAttribute("template", "bookingServices/new");
    	model.addAttribute("booking", bookingDao.read(roomId, userId));
    	model.addAttribute("service", serviceDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/booking-services/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		BookingService bookingService,
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
    	model.addAttribute("bookingService", bookingServiceDao.read(id));
    	model.addAttribute("booking", bookingDao.read(id));
    	model.addAttribute("service", serviceDao.read());
        model.addAttribute("template", "bookingServices/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/booking-services", method=RequestMethod.POST)
    public String create(
		
    	@Valid BookingService bookingService,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/booking-services/new";
        }
        
        
        bookingServiceDao.create(bookingService);
        return "redirect:/booking-services";
        
    }
    
    
    @RequestMapping(value="/booking-services/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid BookingService bookingService,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/booking-services/%s/edit", id);
        }
        
    	bookingServiceDao.update(bookingService);
        return backToShow ? String.format("redirect:/booking-services/%s", id) : "redirect:/booking-services";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/booking-services/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	bookingServiceDao.delete(id);
        response.setStatus(200);
    }
    
}
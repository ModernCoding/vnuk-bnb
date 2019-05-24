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

import vn.edu.vnuk.bnb.dao.RoomTypeDao;
import vn.edu.vnuk.bnb.dao.RoomsDao;
import vn.edu.vnuk.bnb.model.Rooms;

/**
 *
 * @author michel
 */
@Controller
public class RoomsController {
	
	private RoomsDao roomDao;
	private RoomTypeDao roomTypeDao;

	@Autowired
	public void setRoomsDao(RoomsDao roomDao) {
		this.roomDao = roomDao;
	}

	@Autowired
	public void setRoomTypesDao(RoomTypeDao roomTypeDao) {
		this.roomTypeDao = roomTypeDao;
	}
	

	@RequestMapping("/rooms")
    public String index(
		
		@RequestParam(value="roomTypesId", required = false) String roomTypesId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("rooms", roomDao.read(roomTypesId));
		
		if (roomTypesId != null) {
			model.addAttribute("roomtype", roomTypeDao.read(Long.parseLong(roomTypesId)));
		}
		
        model.addAttribute("template", "room/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/rooms/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("room", roomTypeDao.read(id));
        model.addAttribute("template", "room/show");
        return "_layout";
    }
    
    
    @RequestMapping("/rooms/new")
    public String add(
    		
		Rooms room,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "room/new");
    	model.addAttribute("roomtype", roomTypeDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/rooms/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Rooms room,
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
    	model.addAttribute("room", roomDao.read(id));
    	model.addAttribute("roomtype", roomTypeDao.read());
        model.addAttribute("template", "room/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/rooms", method=RequestMethod.POST)
    public String create(
		
    	@Valid Rooms room,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/rooms/new";
        }
        
        
        roomDao.create(room);
        return "redirect:/rooms";
        
    }
    
    
    @RequestMapping(value="/rooms/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Rooms room,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/rooms/%s/edit", id);
        }
        
        roomDao.update(room);
        return backToShow ? String.format("redirect:/rooms/%s", id) : "redirect:/rooms";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/rooms/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	roomDao.delete(id);
        response.setStatus(200);
    }
    
}
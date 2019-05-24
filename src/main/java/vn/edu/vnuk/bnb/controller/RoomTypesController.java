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
import vn.edu.vnuk.bnb.model.RoomType;

/**
 *
 * @author michel
 */
@Controller
public class RoomTypesController {
	
	private RoomTypeDao dao;
	
	@Autowired
	public void setRoomTypesDao(RoomTypeDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/room-types")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("roomTypes", dao.read());
        model.addAttribute("template", "roomTypes/index");
        return "_layout";
    }
    
    
    @RequestMapping("/room-types/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("roomType", dao.read(id));
        model.addAttribute("template", "roomTypes/show");
        return "_layout";
    }
    
    
    @RequestMapping("/room-types/new")
    public String add(RoomType roomType, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "roomTypes/new");
        return "_layout";
    }
    
    
    @RequestMapping("/room-types/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		RoomType roomType,
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
    	model.addAttribute("roomtype", dao.read(id));
        model.addAttribute("template", "roomTypes/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/room-types", method=RequestMethod.POST)
    public String create(
		
    	@Valid RoomType roomType,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/roomstype/new";
        }
        
        dao.create(roomType);
        return "redirect:/roomstype";
        
        
    }
    
    
    @RequestMapping(value="/room-types/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid RoomType roomType,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/roomstype/%s/edit", id);
        }
        
        dao.update(roomType);
        return backToShow ? String.format("redirect:/roomstype/%s", id) : "redirect:/roomstype";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/room-types/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
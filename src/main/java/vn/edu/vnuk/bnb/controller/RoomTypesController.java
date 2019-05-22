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

import vn.edu.vnuk.bnb.dao.RoomTypesDao;
import vn.edu.vnuk.bnb.model.RoomTypes;

/**
 *
 * @author michel
 */
@Controller
public class RoomTypesController {
	
	private RoomTypesDao dao;
	
	@Autowired
	public void setRoomTypesDao(RoomTypesDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/roomstype")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("roomstype", dao.read());
        model.addAttribute("template", "roomtypes/index");
        return "_layout";
    }
    
    
    @RequestMapping("/roomstype/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("roomtype", dao.read(id));
        model.addAttribute("template", "roomtypes/show");
        return "_layout";
    }
    
    
    @RequestMapping("/roomstype/new")
    public String add(RoomTypes roomTypes, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "roomtypes/new");
        return "_layout";
    }
    
    
    @RequestMapping("/roomstype/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		RoomTypes roomTypes,
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
        model.addAttribute("template", "roomtypes/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/roomstype", method=RequestMethod.POST)
    public String create(
		
    	@Valid RoomTypes roomTypes,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/roomstype/new";
        }
        
        dao.create(roomTypes);
        return "redirect:/roomstype";
        
        
    }
    
    
    @RequestMapping(value="/roomstype/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid RoomTypes roomTypes,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/roomstype/%s/edit", id);
        }
        
        dao.update(roomTypes);
        return backToShow ? String.format("redirect:/roomstype/%s", id) : "redirect:/roomstype";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/roomstype/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
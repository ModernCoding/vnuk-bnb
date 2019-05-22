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

import vn.edu.vnuk.bnb.dao.UserTypesDao;
import vn.edu.vnuk.bnb.model.UserTypes;

/**
 *
 * @author michel
 */
@Controller
public class UserTypesController {
	
	private UserTypesDao dao;
	
	@Autowired
	public void setUserTypesDao(UserTypesDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/usertypes")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("usertypes", dao.read());
        model.addAttribute("template", "usertype/index");
        return "_layout";
    }
    
    
    @RequestMapping("/usertypes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("usertype", dao.read(id));
        model.addAttribute("template", "usertype/show");
        return "_layout";
    }
    
    
    @RequestMapping("/usertypes/new")
    public String add(UserTypes usertype, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "usertype/new");
        return "_layout";
    }
    
    
    @RequestMapping("/usertypes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		UserTypes usertype,
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
    	model.addAttribute("usertype", dao.read(id));
        model.addAttribute("template", "usertype/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/usertype", method=RequestMethod.POST)
    public String create(
		
    	@Valid UserTypes usertype,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/usertype/new";
        }
        
        dao.create(usertype);
        return "redirect:/usertype";
        
        
    }
    
    
    @RequestMapping(value="/usertypes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid UserTypes usertype,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/usertypes/%s/edit", id);
        }
        
        dao.update(usertype);
        return backToShow ? String.format("redirect:/usertypes/%s", id) : "redirect:/usertypes";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/usertypes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
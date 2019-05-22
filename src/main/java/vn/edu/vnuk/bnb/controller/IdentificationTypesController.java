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

import vn.edu.vnuk.bnb.dao.IdentificationTypesDao;
import vn.edu.vnuk.bnb.model.IdentificationTypes;

/**
 *
 * @author michel
 */
@Controller
public class IdentificationTypesController {
	
	private IdentificationTypesDao dao;
	
	@Autowired
	public void setIdentificationTypesDao(IdentificationTypesDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/identificationtypes")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("identificationtypes", dao.read());
        model.addAttribute("template", "identificationtypes/index");
        return "_layout";
    }
    
    
    @RequestMapping("/identificationtypes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("identificationtype", dao.read(id));
        model.addAttribute("template", "identificationtypes/show");
        return "_layout";
    }
    
    
    @RequestMapping("/identificationtypes/new")
    public String add(IdentificationTypes identificationtype, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "identificationtypes/new");
        return "_layout";
    }
    
    
    @RequestMapping("/identificationtypes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		IdentificationTypes identificationtype,
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
    	model.addAttribute("identificationtype", dao.read(id));
        model.addAttribute("template", "identificationtypes/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/identificationtypes", method=RequestMethod.POST)
    public String create(
		
    	@Valid IdentificationTypes identificationtype,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/identificationtypes/new";
        }
        
        dao.create(identificationtype);
        return "redirect:/identificationtypes";
        
        
    }
    
    
    @RequestMapping(value="/identificationtypes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid IdentificationTypes identificationtype,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/identificationtypes/%s/edit", id);
        }
        
        dao.update(identificationtype);
        return backToShow ? String.format("redirect:/identificationtypes/%s", id) : "redirect:/identificationtypes";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/identificationtypes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
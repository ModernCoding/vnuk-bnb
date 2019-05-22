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

import vn.edu.vnuk.bnb.dao.DishTypesDao;
import vn.edu.vnuk.bnb.model.DishTypes;

/**
 *
 * @author michel
 */
@Controller
public class DishTypesController {
	
	private DishTypesDao dao;
	
	@Autowired
	public void setDishTypesDao(DishTypesDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/dishestypes")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("dishestypes", dao.read());
        model.addAttribute("template", "dishtype/index");
        return "_layout";
    }
    
    
    @RequestMapping("/dishestypes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("dishtypes", dao.read(id));
        model.addAttribute("template", "dishtype/show");
        return "_layout";
    }
    
    
    @RequestMapping("/dishestypes/new")
    public String add(DishTypes dishtypes, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "dishestypes/new");
        return "_layout";
    }
    
    
    @RequestMapping("/dishestypes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		DishTypes dishtypes,
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
    	model.addAttribute("dishtypes", dao.read(id));
        model.addAttribute("template", "dishestypes/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/dishestypes", method=RequestMethod.POST)
    public String create(
		
    	@Valid DishTypes dishtypes,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/dishestypes/new";
        }
        
        dao.create(dishtypes);
        return "redirect:/dishestypes";
        
        
    }
    
    
    @RequestMapping(value="/dishestypes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid DishTypes dishtypes,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/dishestypes/%s/edit", id);
        }
        
        dao.update(dishtypes);
        return backToShow ? String.format("redirect:/dishestypes/%s", id) : "redirect:/dishestypes";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/dishestypes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
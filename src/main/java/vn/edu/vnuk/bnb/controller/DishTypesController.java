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

import vn.edu.vnuk.bnb.dao.DishTypeDao;
import vn.edu.vnuk.bnb.model.DishType;

/**
 *
 * @author michel
 */
@Controller
public class DishTypesController {
	
	private DishTypeDao dao;
	
	@Autowired
	public void setDishTypesDao(DishTypeDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/dish-types")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("dishTypes", dao.read());
        model.addAttribute("template", "dishtTypes/index");
        return "_layout";
    }
    
    
    @RequestMapping("/dish-types/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("dishType", dao.read(id));
        model.addAttribute("template", "dishtTypes/show");
        return "_layout";
    }
    
    
    @RequestMapping("/dish-types/new")
    public String add(DishType dishType, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "dishtTypes/new");
        return "_layout";
    }
    
    
    @RequestMapping("/dish-types/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		DishType dishType,
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
    	model.addAttribute("dishType", dao.read(id));
        model.addAttribute("template", "dishTypes/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/dish-types", method=RequestMethod.POST)
    public String create(
		
    	@Valid DishType dishType,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/dish-types/new";
        }
        
        dao.create(dishType);
        return "redirect:/dish-types";
        
        
    }
    
    
    @RequestMapping(value="/dish-types/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid DishType dishType,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/dish-types/%s/edit", id);
        }
        
        dao.update(dishType);
        return backToShow ? String.format("redirect:/dish-types/%s", id) : "redirect:/dish-types";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/dish-types/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
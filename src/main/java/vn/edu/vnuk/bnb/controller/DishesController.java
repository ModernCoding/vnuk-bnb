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

import vn.edu.vnuk.bnb.dao.DishDao;
import vn.edu.vnuk.bnb.dao.DishTypeDao;
import vn.edu.vnuk.bnb.model.Dish;

/**
 *
 * @author michel
 */
@Controller
public class DishesController {
	
	private DishDao dishDao;
	private DishTypeDao dishTypeDao;

	@Autowired
	public void setdishesDao(DishDao dishDao) {
		this.dishDao = dishDao;
	}

	@Autowired
	public void setDishTypesDao(DishTypeDao dishTypeDao) {
		this.dishTypeDao = dishTypeDao;
	}
	

	@RequestMapping("/dishes")
    public String index(
		
		@RequestParam(value="dishTypeId", required = false) String dishTypeId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("dishes", dishDao.read(dishTypeId));
		
		if (dishTypeId != null) {
			model.addAttribute("dishType", dishTypeDao.read(Long.parseLong(dishTypeId)));
		}
		
        model.addAttribute("template", "dishes/index");
        return "_layout";
   
	}
    
    
    @RequestMapping("/dishes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("dish", dishDao.read(id));
        model.addAttribute("template", "dishes/show");
        return "_layout";
    }
    
    
    @RequestMapping("/dishes/new")
    public String add(
    		
		Dish dish,
		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors
	
	) throws SQLException{
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
    	model.addAttribute("template", "dishes/new");
    	model.addAttribute("dishType", dishTypeDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/dishes/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Dish dish,
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
    	model.addAttribute("dish", dishDao.read(id));
    	model.addAttribute("dishType", dishTypeDao.read());
        model.addAttribute("template", "dishes/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/dishes", method=RequestMethod.POST)
    public String create(
		
    	@Valid Dish dish,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/dishes/new";
        }
        
        
        dishDao.create(dish);
        return "redirect:/dishes";
        
    }
    
    
    @RequestMapping(value="/dishes/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Dish dish,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/dishes/%s/edit", id);
        }
        
        dishDao.update(dish);
        return backToShow ? String.format("redirect:/dishes/%s", id) : "redirect:/dishes";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/dishes/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dishDao.delete(id);
        response.setStatus(200);
    }
    
}
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

import vn.edu.vnuk.bnb.dao.CountryDao;
import vn.edu.vnuk.bnb.model.Country;

/**
 *
 * @author michel
 */
@Controller
public class CountriesController {
	
	private CountryDao dao;
	
	@Autowired
	public void setCountriesDao(CountryDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/countries")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("countries", dao.read());
        model.addAttribute("template", "country/index");
        return "_layout";
    }
    
    
    @RequestMapping("/countries/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("country", dao.read(id));
        model.addAttribute("template", "country/show");
        return "_layout";
    }
    
    
    @RequestMapping("/countries/new")
    public String add(Country country, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "country/new");
        return "_layout";
    }
    
    
    @RequestMapping("/countries/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Country country,
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
    	model.addAttribute("country", dao.read(id));
        model.addAttribute("template", "country/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/countries", method=RequestMethod.POST)
    public String create(
		
    	@Valid Country country,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/countries/new";
        }
        
        dao.create(country);
        return "redirect:/countries";
        
        
    }
    
    
    @RequestMapping(value="/countries/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Country country,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/countries/%s/edit", id);
        }
        
        dao.update(country);
        return backToShow ? String.format("redirect:/countries/%s", id) : "redirect:/countries";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/countries/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
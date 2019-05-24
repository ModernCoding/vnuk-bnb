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

import vn.edu.vnuk.bnb.dao.EquipmentDao;
import vn.edu.vnuk.bnb.model.Equipment;

/**
 *
 * @author michel
 */
@Controller
public class EquipmentController {
	
	private EquipmentDao dao;
	
	@Autowired
	public void setEquipmentDao(EquipmentDao dao) {
		this.dao = dao;
	}
	

	@RequestMapping("/equipments")
    public String index(Model model, ServletRequest request) throws SQLException{
        model.addAttribute("equipments", dao.read());
        model.addAttribute("template", "equipments/index");
        return "_layout";
    }
    
    
    @RequestMapping("/equipments/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("equipment", dao.read(id));
        model.addAttribute("template", "equipments/show");
        return "_layout";
    }
    
    
    @RequestMapping("/equipments/new")
    public String add(Equipment equipment, Model model, @ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors){
    	
    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}
    	
        model.addAttribute("template", "equipments/new");
        return "_layout";
    }
    
    
    @RequestMapping("/equipments/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Equipment equipment,
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
    	model.addAttribute("equipment", dao.read(id));
        model.addAttribute("template", "equipments/edit");

        return "_layout";
    
        
    }
    
    
    @RequestMapping(value="/equipments", method=RequestMethod.POST)
    public String create(
		
    	@Valid Equipment equipment,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
        
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/equipments/new";
        }
        
        dao.create(equipment);
        return "redirect:/equipments";
        
        
    }
    
    
    @RequestMapping(value="/equipments/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid Equipment equipment,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/equipments/%s/edit", id);
        }
        
        dao.update(equipment);
        return backToShow ? String.format("redirect:/equipments/%s", id) : "redirect:/equipments";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/equipments/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	dao.delete(id);
        response.setStatus(200);
    }
    
}
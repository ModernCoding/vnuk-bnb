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

import vn.edu.vnuk.bnb.dao.UserTypeDao;
import vn.edu.vnuk.bnb.model.UserType;

/**
 *
 * @author michel
 */
@Controller
public class UserTypesController {

	private UserTypeDao dao;

	@Autowired
	public void setUserTypesDao(UserTypeDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/user-types")
	public String index(Model model, ServletRequest request) throws SQLException {
		model.addAttribute("userTypes", dao.read());
		model.addAttribute("template", "userTypes/index");
		return "_layout";
	}

	@RequestMapping("/user-types/{id}")
	public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException {
		model.addAttribute("userType", dao.read(id));
		model.addAttribute("template", "userTypes/show");
		return "_layout";
	}

	@RequestMapping("/user-types/new")
	public String add(UserType userType, Model model,
			@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors) {

		for (FieldError fieldError : fieldErrors) {
			model.addAttribute(String.format("%sFieldError", fieldError.getField()), fieldError.getDefaultMessage());
		}

		model.addAttribute("template", "userTypes/new");
		return "_layout";
	}

	@RequestMapping("/user-types/{id}/edit")
	public String edit(

			@RequestParam(value = "backToShow", defaultValue = "false") Boolean backToShow, @PathVariable("id") Long id,
			UserType userType, Model model, ServletRequest request,
			@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors

	) throws SQLException {

		for (FieldError fieldError : fieldErrors) {
			model.addAttribute(String.format("%sFieldError", fieldError.getField()), fieldError.getDefaultMessage());
		}

		model.addAttribute("backToShow", backToShow);
		model.addAttribute("urlCompletion", backToShow ? String.format("/%s", id) : "");
		model.addAttribute("userType", dao.read(id));
		model.addAttribute("template", "userTypes/edit");

		return "_layout";

	}

	@RequestMapping(value = "/user-types", method = RequestMethod.POST)
	public String create(

			@Valid UserType userType, BindingResult bindingResult, ServletRequest request,
			RedirectAttributes redirectAttributes

	) throws SQLException {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
			return "redirect:/user-types/new";
		}

		dao.create(userType);
		return "redirect:/user-types";

	}

	@RequestMapping(value = "/user-types/{id}", method = RequestMethod.PATCH)
	public String update(

			@RequestParam(value = "backToShow", defaultValue = "false") Boolean backToShow, @PathVariable("id") Long id,
			@Valid UserType userType, BindingResult bindingResult, ServletRequest request,
			RedirectAttributes redirectAttributes

	) throws SQLException {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
			return String.format("redirect:/user-types/%s/edit", id);
		}

		dao.update(userType);
		return backToShow ? String.format("redirect:/user-types/%s", id) : "redirect:/user-types";

	}

	// delete with ajax
	@RequestMapping(value = "/user-types/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response)
			throws SQLException {
		dao.delete(id);
		response.setStatus(200);
	}

}
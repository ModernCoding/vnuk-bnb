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

import vn.edu.vnuk.bnb.dao.IdentificationTypeDao;
import vn.edu.vnuk.bnb.model.IdentificationType;

/**
 *
 * @author michel
 */
@Controller
public class IdentificationTypesController {

	private IdentificationTypeDao dao;

	@Autowired
	public void setIdentificationTypesDao(IdentificationTypeDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/identification-types")
	public String index(Model model, ServletRequest request) throws SQLException {
		model.addAttribute("identificationTypes", dao.read());
		model.addAttribute("template", "identificationTypes/index");
		return "_layout";
	}

	@RequestMapping("/identification-types/{id}")
	public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException {
		model.addAttribute("identificationType", dao.read(id));
		model.addAttribute("template", "identificationTypes/show");
		return "_layout";
	}

	@RequestMapping("/identification-types/new")
	public String add(IdentificationType identificationType, Model model,
			@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors) {

		for (FieldError fieldError : fieldErrors) {
			model.addAttribute(String.format("%sFieldError", fieldError.getField()), fieldError.getDefaultMessage());
		}

		model.addAttribute("template", "identificationTypes/new");
		return "_layout";
	}

	@RequestMapping("/identification-types/{id}/edit")
	public String edit(

			@RequestParam(value = "backToShow", defaultValue = "false") Boolean backToShow, @PathVariable("id") Long id,
			IdentificationType identificationType, Model model, ServletRequest request,
			@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors

	) throws SQLException {

		for (FieldError fieldError : fieldErrors) {
			model.addAttribute(String.format("%sFieldError", fieldError.getField()), fieldError.getDefaultMessage());
		}

		model.addAttribute("backToShow", backToShow);
		model.addAttribute("urlCompletion", backToShow ? String.format("/%s", id) : "");
		model.addAttribute("identificationType", dao.read(id));
		model.addAttribute("template", "identificationTypes/edit");

		return "_layout";

	}

	@RequestMapping(value = "/identification-types", method = RequestMethod.POST)
	public String create(

			@Valid IdentificationType identificationType, BindingResult bindingResult, ServletRequest request,
			RedirectAttributes redirectAttributes

	) throws SQLException {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
			return "redirect:/identification-types/new";
		}

		dao.create(identificationType);
		return "redirect:/identification-types";

	}

	@RequestMapping(value = "/identification-types/{id}", method = RequestMethod.PATCH)
	public String update(

			@RequestParam(value = "backToShow", defaultValue = "false") Boolean backToShow, @PathVariable("id") Long id,
			@Valid IdentificationType identificationType, BindingResult bindingResult, ServletRequest request,
			RedirectAttributes redirectAttributes

	) throws SQLException {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
			return String.format("redirect:/identification-types/%s/edit", id);
		}

		dao.update(identificationType);
		return backToShow ? String.format("redirect:/identification-types/%s", id) : "redirect:/identification-types";

	}

	// delete with ajax
	@RequestMapping(value = "/identification-types/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response)
			throws SQLException {
		dao.delete(id);
		response.setStatus(200);
	}

}
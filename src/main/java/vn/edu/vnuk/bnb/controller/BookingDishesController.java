package vn.edu.vnuk.bnb.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vnuk.bnb.dao.BookingDishDao;
import vn.edu.vnuk.bnb.dao.BookingDao;
import vn.edu.vnuk.bnb.dao.DishDao;
import vn.edu.vnuk.bnb.model.BookingDish;



public class BookingDishesController {

	private BookingDishDao bookingDishDao;
	private BookingDao bookingDao;
	private DishDao dishDao;

	@Autowired
	public void setBookingDishDao(BookingDishDao bookingDishDao) {
		this.bookingDishDao = bookingDishDao;
	}

	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}


	@Autowired
	public void setDishDao(DishDao dishDao) {
		this.dishDao = dishDao;
	}

	@RequestMapping("/booking-Dishes")
    public String index(

		@RequestParam(value="bookingId", required = false) String bookingId,
		@RequestParam(value="dishId", required = false) String dishId,
		Model model,
		ServletRequest request

	) throws SQLException{

		model.addAttribute("bookingDishes", bookingDishDao.read(bookingId, dishId));

		if (bookingId != null && dishId!= null) {
			model.addAttribute("booking", bookingDao.read(Long.parseLong(bookingId)));
			model.addAttribute("dish", dishDao.read(Long.parseLong(dishId)));
		}

        model.addAttribute("template", "bookingDishes/index");
        return "_layout";

	}

	@RequestMapping("/booking-Dishes/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("bookingDish", bookingDao.read(id));
        model.addAttribute("bookingDish", dishDao.read(id));
        return "_layout";
    }

 @RequestMapping("/booking-Dishes/new")
    public String add(

		BookingDish bookingDish,
		@RequestParam(value="roomId", required = false) String roomId,
		@RequestParam(value="userId", required = false) String userId,
		@RequestParam(value="dishTypesId", required = false) String dishTypesId,
		ServletRequest request,

		Model model,
		@ModelAttribute("fieldErrors") ArrayList<FieldError> fieldErrors

	) throws SQLException{

    	for(FieldError fieldError : fieldErrors) {
    		model.addAttribute(
    				String.format("%sFieldError", fieldError.getField()),
    				fieldError.getDefaultMessage()
    			);
    	}

    	model.addAttribute("template", "bills/new");
    	model.addAttribute("booking", bookingDao.read(roomId, userId));
    	model.addAttribute("dish", dishDao.read(dishTypesId));
        return "_layout";
    }

 @RequestMapping("/booking-Dishes/{id}/edit")
 public String edit(

		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		BookingDish bookingDish,
		@RequestParam(value="roomId", required = false) String roomId,
		@RequestParam(value="userId", required = false) String userId,
		@RequestParam(value="dishTypesId", required = false) String dishTypesId,
		ServletRequest request,
		Model model,
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
 	model.addAttribute("bookingDish", bookingDishDao.read(id));
 	model.addAttribute("booking", bookingDao.read(roomId, userId));
 	model.addAttribute("dish", dishDao.read(dishTypesId));
     model.addAttribute("template", "bookingDishes/edit");
     return "_layout";

 }

 @RequestMapping(value="/booking-Dishes", method=RequestMethod.POST)
 public String create(

 	@Valid BookingDish bookingDish,
 	BindingResult bindingResult,
 	ServletRequest request,
 	RedirectAttributes redirectAttributes

 ) throws SQLException{

     if (bindingResult.hasErrors()) {
     	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
         return "redirect:/booking-Dishes/new";
     }


     bookingDishDao.create(bookingDish);
     return "redirect:/booking-Dishes";

 }


@RequestMapping(value="/booking-Dishes/{id}", method=RequestMethod.PATCH)
 public String update(

 		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
 		@PathVariable("id") Long id,
 		@Valid BookingDish bookingDish,
 		BindingResult bindingResult,
 		ServletRequest request,
 		RedirectAttributes redirectAttributes

 	) throws SQLException{


 	if (bindingResult.hasErrors()) {
     	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
         return String.format("redirect:/booking-Dishes/%s/edit", id);
     }

     bookingDishDao.update(bookingDish);
     return backToShow ? String.format("redirect:/booking-Dishes/%s", id) : "redirect:/booking-Dishes";


 }


 //  delete with ajax
 @RequestMapping(value="/booking-Dishes/{id}", method = RequestMethod.DELETE)
 public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
 	bookingDishDao.delete(id);
     response.setStatus(200);
 }

}

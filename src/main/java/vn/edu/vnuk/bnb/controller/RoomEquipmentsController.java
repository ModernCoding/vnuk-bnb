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

import vn.edu.vnuk.bnb.dao.EquipmentDao;
import vn.edu.vnuk.bnb.dao.RoomDao;
import vn.edu.vnuk.bnb.dao.RoomEquipmentDao;
import vn.edu.vnuk.bnb.model.Room;
import vn.edu.vnuk.bnb.model.RoomEquipment;

public class RoomEquipmentsController {
	
	private RoomEquipmentDao roomEquipmentDao;
	private RoomDao roomDao;
	private EquipmentDao equipmentDao;
	
	
	@Autowired
	public void setRoomEquipmentDao(RoomEquipmentDao roomEquipmentDao) {
		this.roomEquipmentDao = roomEquipmentDao;
	}

	@Autowired
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	@Autowired
	public void setEquipmentDao(EquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}
	
	@RequestMapping("/room-Equipments")
    public String index(
		
		@RequestParam(value="roomId", required = false) String roomId,
		@RequestParam(value="equipmentId", required = false) String equipmentId,
		Model model,
		ServletRequest request

	) throws SQLException{
        
		model.addAttribute("roomEquipments", roomEquipmentDao.read(roomId,equipmentId));
		
		if (roomId != null) {
			model.addAttribute("room", roomDao.read(Long.parseLong(roomId)));
			model.addAttribute("equipemnt", equipmentDao.read(Long.parseLong(equipmentId)));
		}
		
        model.addAttribute("template", "roomEquipments/index");
        return "_layout";
   
	}
	
	@RequestMapping("/room-Equipments/{id}")
    public String show(@PathVariable("id") Long id, Model model, ServletRequest request) throws SQLException{
        model.addAttribute("roomEquipment", roomEquipmentDao.read(id));
        model.addAttribute("template", "roomEquipments/show");
        return "_layout";
        
    }
    
    @RequestMapping("/room-Equipments/new")
    public String add(
    		
		RoomEquipment roomEquipment,
		@RequestParam(value="roomTypesId", required = false) String roomTypesId,
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
    	
    	model.addAttribute("template", "roomEquipments/new");
    	model.addAttribute("room", roomDao.read(roomTypesId));
    	model.addAttribute("equiment", equipmentDao.read());
        return "_layout";
    }
    
    
    @RequestMapping("/room-Equipments/{id}/edit")
    public String edit(
    		
		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
		@PathVariable("id") Long id,
		Room room,
		Model model,
		@RequestParam(value="roomTypesId", required = false) String roomTypesId,
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
    	model.addAttribute("rommEquipment", roomEquipmentDao.read(id));
    	model.addAttribute("room", roomDao.read(roomTypesId));
    	model.addAttribute("equipment", equipmentDao.read());
        model.addAttribute("template", "roomEquipments/edit");

        return "_layout";
    
    }
    
    
    @RequestMapping(value="/room-Equipments", method=RequestMethod.POST)
    public String create(
		
    	@Valid RoomEquipment roomEquipment,
    	BindingResult bindingResult,
    	ServletRequest request,
    	RedirectAttributes redirectAttributes
    
    ) throws SQLException{
    	
        if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return "redirect:/room-Equipments/new";
        }
        
        
        roomEquipmentDao.create(roomEquipment);
        return "redirect:/room-Equipments";
        
    }
    
    
    @RequestMapping(value="/room-Equipments/{id}", method=RequestMethod.PATCH)
    public String update(
    		
    		@RequestParam(value="backToShow", defaultValue="false") Boolean backToShow,
    		@PathVariable("id") Long id,
    		@Valid RoomEquipment roomEquipment,
    		BindingResult bindingResult,
    		ServletRequest request,
    		RedirectAttributes redirectAttributes
    		
    	) throws SQLException{
    	
        
    	if (bindingResult.hasErrors()) {
        	redirectAttributes.addFlashAttribute("fieldErrors", bindingResult.getAllErrors());
            return String.format("redirect:/room-Equipments/%s/edit", id);
        }
        
    	roomEquipmentDao.update(roomEquipment);
        return backToShow ? String.format("redirect:/room-Equipments/%s", id) : "redirect:/roomEquipments";
        
        
    }
    
    
    //  delete with ajax
    @RequestMapping(value="/room-Equipments/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id, ServletRequest request, HttpServletResponse response) throws SQLException {
    	roomEquipmentDao.delete(id);
        response.setStatus(200);
    }
    
}

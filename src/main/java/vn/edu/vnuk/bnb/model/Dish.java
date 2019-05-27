package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Dish {
	private int id;
	
	@NotNull
	@Size(min = 1, message="Label is mandatory")
	private String label;
	@NotNull
	private double price;
	@NotNull
	@Size(min = 1, message="Description is mandatory")
	private String description;
	@NotNull
	private int dishTypeId;
	
	
	private DishType dishType;
	

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DishType getDishType() {
		return dishType;
	}
	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDishTypeId() {
		return dishTypeId;
	}
	public void setDishTypeId(int dishTypeId) {
		this.dishTypeId = dishTypeId;
	}

	
}

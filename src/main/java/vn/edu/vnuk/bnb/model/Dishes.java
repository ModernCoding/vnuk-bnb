package vn.edu.vnuk.bnb.model;

public class Dishes {
	private Long id;
	private String label;
	private double price;
	private String description;
	private DishTypes dishType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public DishTypes getDishType() {
		return dishType;
	}
	public void setDishType(DishTypes dishType) {
		this.dishType = dishType;
	}
	
}

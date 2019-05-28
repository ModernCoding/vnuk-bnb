package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Equipment {
	private int id;
	
	@NotNull
    @Size(min = 1, message="Label is mandatory")
	private String label;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}

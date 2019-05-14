package vn.edu.vnuk.bnb.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserTypes {
	private Long id;
	
	@NotNull
    @Size(min = 1, message="Label is mandatory")
	private String label;
	
	
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
}

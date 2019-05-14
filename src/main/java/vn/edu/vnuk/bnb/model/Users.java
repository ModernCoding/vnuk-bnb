package vn.edu.vnuk.bnb.model;

import java.util.Calendar;


import org.springframework.format.annotation.DateTimeFormat;

public class Users {
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private String email;
	private String phone;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar createAt;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar updateAt;
	private int identificationNumber;
	private UserTypes userTypes;
	private IdentificationTypes identificationTypes;
	private Countries country;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Calendar getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Calendar createAt) {
		this.createAt = createAt;
	}
	public Calendar getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Calendar updateAt) {
		this.updateAt = updateAt;
	}
	public int getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(int identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public UserTypes getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(UserTypes userTypes) {
		this.userTypes = userTypes;
	}
	public IdentificationTypes getIdentificationTypes() {
		return identificationTypes;
	}
	public void setIdentificationTypes(IdentificationTypes identificationTypes) {
		this.identificationTypes = identificationTypes;
	}
	public Countries getCountry() {
		return country;
	}
	public void setCountry(Countries country) {
		this.country = country;
	}
}

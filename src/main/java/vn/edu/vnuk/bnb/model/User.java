package vn.edu.vnuk.bnb.model;

import java.sql.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	private int id;
	@NotNull
	@Size(min = 1, message="FirstName is mandatory")
	private String firstName;
	@NotNull
	@Size(min = 1, message="MiddleName is mandatory")
	private String middleName;
	@NotNull
	@Size(min = 1, message="LastName is mandatory")
	private String lastName;
	@NotNull
	@Size(min = 1, message="Address is mandatory")
	private String address;
	@NotNull
	@Size(min = 1, message="Email is mandatory")
	private String email;
	@NotNull
	@Size(min = 1, message="Phone is mandatory")
	private String phone;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date createAt;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date updateAt;
	
	@NotNull
	private int userTypeId;
	@NotNull
	private int countryId;
	@NotNull
	private int identificationTypeId;
	@NotNull
	private int identificationNumber;
	
	
	private UserType userType;
	private IdentificationType identificationType;
	private Country country;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public int getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(int identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public UserType getUserTypes() {
		return userType;
	}
	public void setUserTypes(UserType userType) {
		this.userType = userType;
	}
	public IdentificationType getIdentificationTypes() {
		return identificationType;
	}
	public void setIdentificationTypes(IdentificationType identificationType) {
		this.identificationType = identificationType;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public int getUserTypesId() {
		return userTypeId;
	}
	public void setUserTypesId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getIdentificationTypesId() {
		return identificationTypeId;
	}
	public void setIdentificationTypesId(int identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}
	
}

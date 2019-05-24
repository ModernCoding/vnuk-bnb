package vn.edu.vnuk.bnb.model;

import java.sql.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	private Long id;
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
	private Long userTypesId;
	@NotNull
	private Long countryId;
	@NotNull
	private Long identificationTypesId;
	@NotNull
	private int identificationNumber;
	
	
	private UserType userTypes;
	private IdentificationType identificationTypes;
	private Country country;
	
	
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
		return userTypes;
	}
	public void setUserTypes(UserType userTypes) {
		this.userTypes = userTypes;
	}
	public IdentificationType getIdentificationTypes() {
		return identificationTypes;
	}
	public void setIdentificationTypes(IdentificationType identificationTypes) {
		this.identificationTypes = identificationTypes;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Long getUserTypesId() {
		return userTypesId;
	}
	public void setUserTypesId(Long userTypesId) {
		this.userTypesId = userTypesId;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public Long getIdentificationTypesId() {
		return identificationTypesId;
	}
	public void setIdentificationTypesId(Long identificationTypesId) {
		this.identificationTypesId = identificationTypesId;
	}
	
}

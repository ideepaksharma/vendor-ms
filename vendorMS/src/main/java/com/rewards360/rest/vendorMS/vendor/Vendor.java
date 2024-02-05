package com.rewards360.rest.vendorMS.vendor;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rewards360.rest.vendorMS.post.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

//@JsonIgnoreProperties({"phone", "status"}) //static filtering at class level
//@JsonFilter("QRFilter")
@Entity(name="vendor")
public class Vendor {
	@Id
	@GeneratedValue
	private long id;
	@Size(min=4, max=250, message = "Name should have atleast 4 characters")
	private String name;
	private String phone;	
	private String mobile;
	@Email(message = "Please provide valid Email address")
	private String email;
	private String status;
	private String businessType; //FnC, beverages, sports
	private String locationType; //foodtruck, restaurant, store, sports
	private String businessAddress;	
	private String city;
	private String state;
	private String country;
	private String zipcode;
	@PastOrPresent
	private LocalDate registrationDate;
	private String qrCodeText;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vendor")
	private List<Post> posts;
	
	public Vendor() {
		
	}
	
	public Vendor(long id, String name, String phone, String mobile, String email, String status, String businessType,
			String locationType, String businessAddress, String city, String state, String country, String zipcode,
			LocalDate registrationDate, String qrCodeText) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.status = status;
		this.businessType = businessType;
		this.locationType = locationType;
		this.businessAddress = businessAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.registrationDate = registrationDate;
		this.qrCodeText = qrCodeText;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}	
	public String getQrCodeText() {
		return qrCodeText;
	}
	public void setQrCodeText(String qrCodeText) {
		this.qrCodeText = qrCodeText;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", phone=" + phone + ", mobile=" + mobile + ", email=" + email
				+ ", status=" + status + ", businessType=" + businessType + ", locationType=" + locationType
				+ ", businessAddress=" + businessAddress + ", city=" + city + ", state=" + state + ", country="
				+ country + ", zipcode=" + zipcode + ", registrationDate=" + registrationDate + ", qrCodeText="
				+ qrCodeText + "]";
	}
	
}

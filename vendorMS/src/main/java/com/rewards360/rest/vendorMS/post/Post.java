package com.rewards360.rest.vendorMS.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rewards360.rest.vendorMS.vendor.Vendor;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private long id;
	
	private String description;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Vendor vendor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Size(max = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
}

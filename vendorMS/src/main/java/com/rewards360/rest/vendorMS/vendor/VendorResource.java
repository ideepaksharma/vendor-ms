package com.rewards360.rest.vendorMS.vendor;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rewards360.rest.vendorMS.vendor.dao.VendorDaoService;
import com.rewards360.rest.vendorMS.vendor.exception.VendorNotFoundException;

import jakarta.validation.Valid;

@RestController
public class VendorResource {

	private VendorDaoService service;
	
	@Autowired
	private MessageSource msgSrc;
	
	public VendorResource(VendorDaoService service) {
		this.service = service;
	}
	
	//Get all vendors
	@GetMapping("/vendors")
	public List<Vendor> retrieveAllVendors(){
		return service.findAll();
	}

	//Get a vendor
	@GetMapping("/vendors/{id}")
	public Vendor retrieveVendorbyID(@PathVariable long id){
		Vendor vendorbyID = service.findById(id);
		
		if(vendorbyID == null) {
			Locale locale = LocaleContextHolder.getLocale();
			String vendorNotFound = msgSrc.getMessage("vendor.not.found", null, null, locale);
			throw new VendorNotFoundException(vendorNotFound + " id: " + id);
		}
		
		return vendorbyID;	
	}
	
	//Get a vendor QR code
	@GetMapping("/vendors/{id}/card")
	public MappingJacksonValue retrieveVendorQRCard(@PathVariable long id){
		Vendor vendorbyID = service.findById(id);
		
		if(vendorbyID == null) {
			Locale locale = LocaleContextHolder.getLocale();
			String vendorNotFound = msgSrc.getMessage("vendor.not.found", null, null, locale);
			throw new VendorNotFoundException(vendorNotFound + " id: " + id);			
		}
			
		
		  MappingJacksonValue filteredVendors = new MappingJacksonValue(vendorbyID);
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "email", "qrCodeText");
		  FilterProvider filters = new SimpleFilterProvider().addFilter("QRFilter", filter); 
		  filteredVendors.setFilters(filters); 
		  
		  return filteredVendors;
	}
	
	
	//Delete a vendor
	@DeleteMapping("/vendors/{id}")
	public void deleteVendorbyID(@PathVariable long id){
		service.deleteById(id);
	}
	
	//Create a vendor
	@PostMapping("/vendors")
	public ResponseEntity<Vendor> createVendor(@Valid @RequestBody Vendor vendor) {		
		Vendor savedVendor = service.save(vendor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedVendor.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}

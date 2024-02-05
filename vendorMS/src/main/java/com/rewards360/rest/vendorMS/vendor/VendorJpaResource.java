package com.rewards360.rest.vendorMS.vendor;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
import com.rewards360.rest.vendorMS.post.Post;
import com.rewards360.rest.vendorMS.vendor.exception.VendorNotFoundException;
import com.rewards360.rest.vendorMS.vendor.jpa.PostRepository;
import com.rewards360.rest.vendorMS.vendor.jpa.VendorRepository;

import jakarta.validation.Valid;

@RestController
public class VendorJpaResource {

	
	private VendorRepository vendorRepository;
	private PostRepository postRepository;

	@Autowired
	private MessageSource msgSrc;

	public VendorJpaResource(VendorRepository vendorRepository, PostRepository postRepository) {		
		this.vendorRepository = vendorRepository;
		this.postRepository = postRepository;
	}

	// Get all vendors
	@GetMapping("/jpa/vendors")
	public List<Vendor> retrieveAllVendors() {
		return vendorRepository.findAll();
	}

	// Get a vendor
	@GetMapping("/jpa/vendors/{id}")
	public Optional<Vendor> retrieveVendorbyID(@PathVariable long id) {
		Optional<Vendor> vendorbyID = vendorRepository.findById(id);

		if (vendorbyID.isEmpty()) {
			Locale locale = LocaleContextHolder.getLocale();
			String vendorNotFound = msgSrc.getMessage("vendor.not.found", null, null, locale);
			throw new VendorNotFoundException(vendorNotFound + " id: " + id);
		}

		return vendorbyID;
	}

	// Get a vendor QR code
	@GetMapping("/jpa/vendors/{id}/card")
	public MappingJacksonValue retrieveVendorQRCard(@PathVariable long id) {
		Optional<Vendor> vendorbyID = vendorRepository.findById(id);

		if (vendorbyID.isEmpty()) {
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

	// Delete a vendor
	@DeleteMapping("/jpa/vendors/{id}")
	public void deleteVendorbyID(@PathVariable long id) {
		vendorRepository.deleteById(id);
	}

	// Create a vendor
	@PostMapping("/jpa/vendors")
	public ResponseEntity<Vendor> createVendor(@Valid @RequestBody Vendor vendor) {
		Vendor savedVendor = vendorRepository.save(vendor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedVendor.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	// Get posts for vendor
	@GetMapping("/jpa/vendors/{id}/posts")
	public List<Post> retrievePostsForVendor(@PathVariable long id) {
		Optional<Vendor> vendorbyID = vendorRepository.findById(id);

		if (vendorbyID.isEmpty()) {
			Locale locale = LocaleContextHolder.getLocale();
			String vendorNotFound = msgSrc.getMessage("vendor.not.found", null, null, locale);
			throw new VendorNotFoundException(vendorNotFound + " id: " + id);
		}

		return vendorbyID.get().getPosts();
	}

	// Create post for vendor
	@PostMapping("/jpa/vendors/{id}/posts")
	public ResponseEntity<Object> CreatePostForVendor(@PathVariable long id, @Valid @RequestBody Post post) {
		Optional<Vendor> vendorbyID = vendorRepository.findById(id);

		if (vendorbyID.isEmpty()) {
			Locale locale = LocaleContextHolder.getLocale();
			String vendorNotFound = msgSrc.getMessage("vendor.not.found", null, null, locale);
			throw new VendorNotFoundException(vendorNotFound + " id: " + id);
		}

		post.setVendor(vendorbyID.get());
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}

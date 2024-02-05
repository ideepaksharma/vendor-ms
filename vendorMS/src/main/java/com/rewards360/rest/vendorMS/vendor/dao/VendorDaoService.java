package com.rewards360.rest.vendorMS.vendor.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.rewards360.rest.vendorMS.vendor.Vendor;

@Component
public class VendorDaoService {

	private static List<Vendor> vendors = new ArrayList<>();

	private static long vendorCount = 0;
	static {
		vendors.add(new Vendor(++vendorCount, "ComeBuy", null, "012542512345", "info@combuy.de", "Active", "FnC",
				"Store", "Bahnhofstr. 10", "Nuremberg", "Bavaria", "Germany", "90402", LocalDate.now(), "123456789|WPSLite|H34"));
		vendors.add(new Vendor(++vendorCount, "NTC", null, "012542523456", "info@ntc.de", "Active", "Sports", "Sports",
				null, "Nuremberg", "Bavaria", "Germany", "90402", LocalDate.now().minusDays(15), "123456789|WPSLite|H12"));
		vendors.add(new Vendor(++vendorCount, "Swagman", null, "012542534567", "info@swagman.de", "Active", "FnC",
				"FoodTruck", "Marienplatz. 42", "Nuremberg", "Bavaria", "Germany", "90407",
				LocalDate.now().minusDays(5), "123456780|WPSLite|H55"));
	}

	public List<Vendor> findAll() {
		return vendors;
	}

	public Vendor findById(long id) {
		Predicate<? super Vendor> predicate = vendor -> vendor.getId() == id;
		return vendors.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(long id) {
		Predicate<? super Vendor> predicate = vendor -> vendor.getId() == id;
		vendors.removeIf(predicate);
	}

	public Vendor save(Vendor vendor) {
		vendor.setId(++vendorCount);
		vendors.add(vendor);

		return vendor;
	}
}

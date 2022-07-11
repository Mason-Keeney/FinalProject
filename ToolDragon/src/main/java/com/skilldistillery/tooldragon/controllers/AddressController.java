package com.skilldistillery.tooldragon.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tooldragon.entities.Address;
import com.skilldistillery.tooldragon.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class AddressController {

	@Autowired
	private AddressService addressService;
//	private String city;

	@GetMapping("addresses")
	public List<Address> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<Address> address = addressService.index(principal.getName());
		if (address == null) {
			res.setStatus(404);
		}
		return address;
	}

	@GetMapping("addresses/{tid}")
	public Address show(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, Principal principal) {
		Address address = addressService.show(principal.getName(), tid);
		if (address == null) {
			res.setStatus(404);
		}
//	Thread Safety Failure Example (setting and calling a class variable, if multiple threads are passing they can change the class variable before it is called in the getter)	
//		setCity(address.getCity());
//		if("Fresno".equals(getCity())) {
//			System.out.println("Fresno rules");
//		}
		return address;
	}

	@PostMapping("addresses")
	public Address create(HttpServletRequest req, HttpServletResponse res, @RequestBody Address address,
			Principal principal) {
		address = addressService.create(principal.getName(), address);
		try {
			if (address == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(address.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			address = null;

		}
		return address;
	}

	@PutMapping("addresses/{tid}")
	public Address update(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid,
			@RequestBody Address address, Principal principal) {
		address = addressService.update(principal.getName(), address, tid);
		if (address == null) {
			res.setStatus(404);
		}
		return address;
	}

	@DeleteMapping("addresses/{tid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, Principal principal) {
		try {
			if (addressService.destroy(principal.getName(), tid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}

}

package com.management.gym.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.gym.dto.Actor;
import com.management.gym.dto.Actress;
import com.management.gym.entity.Customer;
import com.management.gym.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/saveCustomers")
	public ResponseEntity<?> saveCustomers() {

		List<Actor> actorList = null;
		List<Actress> actressList = null;
		List<Customer> customers = new ArrayList<Customer>(50);
		List<Customer> addedCustomers = null;

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://freetestapi.com/api/v1/actors?limit=25"))
					.GET()
					.build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ObjectMapper mapper = new ObjectMapper();
				actorList = mapper.readValue(response.body(), new TypeReference<List<Actor>>() {});		
			}

			HttpRequest request1 = HttpRequest.newBuilder()
					.uri(new URI("https://freetestapi.com/api/v1/actress?limit=25"))
					.GET()
					.build();

			HttpClient client1 = HttpClient.newHttpClient();
			HttpResponse<String> response1 = client1.send(request1, BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ObjectMapper mapper1 = new ObjectMapper();
				actressList = mapper1.readValue(response.body(), new TypeReference<List<Actress>>() {});
			} else {
				System.out.println("Failed to fetch customers: HTTP " + response1.statusCode());
			}	

			for(Actor actor:actorList) {
				Customer customer = new Customer();
				customer.setBio(actor.getBiography());			
				customer.setName(actor.getName());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
				LocalDate date = LocalDate.parse("01-Jan-"+actor.getBirth_year(), formatter);
				customer.setDob(date);
				//customer.setAvailability(true);

				customers.add(customer);
			}

			for(Actress actress:actressList) {
				Customer customer = new Customer();
				customer.setBio(actress.getBiography());			
				customer.setName(actress.getName());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
				LocalDate date = LocalDate.parse("01-Jan-"+actress.getBirth_year(), formatter);
				customer.setDob(date);
				
				customers.add(customer);
			}
			addedCustomers = customerService.saveCustomers(customers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Customer>>(addedCustomers,HttpStatus.OK);
	}
}
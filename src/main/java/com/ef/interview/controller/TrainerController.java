package com.ef.interview.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ef.interview.dto.User;
import com.ef.interview.entity.Trainer;
import com.ef.interview.service.TrainerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/trainer")
@Log4j2
public class TrainerController {

	@Autowired
	TrainerService trainerService;

	@PostMapping("/saveTrainers")
	public ResponseEntity<?> saveTrainers() {

		List<User> userList = null;
		List<Trainer> trainers = new ArrayList<Trainer>(30);
		List<Trainer> addedTrainers = null;

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://freetestapi.com/api/v1/users?limit=30"))
					.GET()
					.build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ObjectMapper mapper = new ObjectMapper();
				userList = mapper.readValue(response.body(), new TypeReference<List<User>>() {});	

			}
			for(User user:userList) {

				Trainer trainer = new Trainer();
				trainer.setName(user.getName());
				trainer.setEmail(user.getEmail());
				trainer.setPhone(user.getPhone());
				trainer.setCity(user.getAddress().getCity());
				trainer.setStreet(user.getAddress().getStreet());
				trainer.setZip(user.getAddress().getZip());
				trainer.setAvailability(true);

				trainers.add(trainer);
			}
			addedTrainers = trainerService.saveTrainers(trainers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Trainer>>(addedTrainers,HttpStatus.OK);
	}
}

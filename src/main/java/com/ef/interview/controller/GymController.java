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
import com.ef.interview.dto.USState;
import com.ef.interview.entity.Gym;
import com.ef.interview.service.GymService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/gym")
@Log4j2
public class GymController {

	@Autowired
	GymService gymService;

	@PostMapping("/createGymForEachState")
	public ResponseEntity<?> createGymForEachState() {

		List<USState> usStateList = null;
		List<Gym> gyms = new ArrayList<Gym>(50);
		List<Gym> createdGyms = null;

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://freetestapi.com/api/v1/us-states?limit=50"))
					.GET()
					.build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ObjectMapper mapper = new ObjectMapper();
				usStateList = mapper.readValue(response.body(), new TypeReference<List<USState>>() {});	

			}
			for(USState state:usStateList) {

				Gym gym = new Gym();
				gym.setUsState(state.getName());
				gym.setName(state.getName()+" State Gym");

				gyms.add(gym);
			}
			createdGyms = gymService.createGyms(gyms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Gym>>(createdGyms,HttpStatus.OK);
	}
}

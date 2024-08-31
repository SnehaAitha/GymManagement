package com.ef.interview.service;

import java.util.List;
import java.util.Optional;
import com.ef.interview.entity.Gym;

public interface GymService {

	List<Gym> createGyms(List<Gym> gyms);
	Optional<Gym> fetchGym(Integer id);

}

package com.management.gym.service;

import java.util.List;
import java.util.Optional;

import com.management.gym.entity.Gym;

public interface GymService {

	List<Gym> createGyms(List<Gym> gyms);
	Optional<Gym> fetchGym(Integer id);

}

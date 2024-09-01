package com.management.gym.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.gym.entity.Gym;
import com.management.gym.repository.GymRepository;
import com.management.gym.service.GymService;

@Service
public class GymServiceImpl implements GymService {
	
	@Autowired
	GymRepository gymRepo;

	@Override
	public List<Gym> createGyms(List<Gym> gyms) {
		return gymRepo.saveAll(gyms);
	}

	@Override
	public Optional<Gym> fetchGym(Integer id) {
		return gymRepo.findById(id);
	}

}


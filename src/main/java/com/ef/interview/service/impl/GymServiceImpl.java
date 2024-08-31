package com.ef.interview.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ef.interview.entity.Gym;
import com.ef.interview.repository.GymRepository;
import com.ef.interview.service.GymService;

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


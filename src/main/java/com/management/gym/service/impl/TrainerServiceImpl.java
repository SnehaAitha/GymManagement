package com.management.gym.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.gym.entity.Trainer;
import com.management.gym.repository.TrainerRepository;
import com.management.gym.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {
	
	@Autowired
	TrainerRepository trainerRepo;

	@Override
	public List<Trainer> saveTrainers(List<Trainer> trainers) {
		return trainerRepo.saveAll(trainers);
	}

	@Override
	public Optional<Trainer> fetchTrainer(Integer id) {
		return trainerRepo.findById(id);
	}
	
	@Override
	public void saveTrainer(Trainer trainer) {
		trainerRepo.save(trainer);	
	}

}

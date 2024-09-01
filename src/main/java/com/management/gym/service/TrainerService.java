package com.management.gym.service;

import java.util.List;
import java.util.Optional;

import com.management.gym.entity.Trainer;

public interface TrainerService {

	List<Trainer> saveTrainers(List<Trainer> trainers);
	Optional<Trainer> fetchTrainer(Integer id);
	void saveTrainer(Trainer trainer);

}

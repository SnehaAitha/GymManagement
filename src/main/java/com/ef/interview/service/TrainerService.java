package com.ef.interview.service;

import java.util.List;
import java.util.Optional;
import com.ef.interview.entity.Trainer;

public interface TrainerService {

	List<Trainer> saveTrainers(List<Trainer> trainers);
	Optional<Trainer> fetchTrainer(Integer id);
	void saveTrainer(Trainer trainer);

}

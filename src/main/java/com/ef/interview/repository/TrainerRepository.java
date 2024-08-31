package com.ef.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ef.interview.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Integer>{

}

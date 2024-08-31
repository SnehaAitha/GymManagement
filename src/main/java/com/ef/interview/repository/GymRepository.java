package com.ef.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ef.interview.entity.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym,Integer>{

}

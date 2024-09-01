package com.management.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.gym.entity.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym,Integer>{

}

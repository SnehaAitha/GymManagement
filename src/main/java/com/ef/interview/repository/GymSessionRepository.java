package com.ef.interview.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ef.interview.entity.GymSession;

@Repository
public interface GymSessionRepository extends JpaRepository<GymSession,Integer>{
	
	@Query("SELECT g FROM GymSession g WHERE (:trainerId =0 or g.trainer.id = :trainerId)" +
	         " and (:customerId =0 or g.customer.id = :customerId)"+
	         " and (:startTime is NULL or DATE(g.startTime) >= DATE(:startTime))"+
	         " and (:endTime is NULL or DATE(g.endTime) <= DATE(:endTime))")
	  List<GymSession> findGymSessionByAllFields(@Param("trainerId") Integer trainerId,
	                                       @Param("customerId") Integer customerId,
	                                       @Param("startTime") LocalDateTime startTime,
	                                       @Param("endTime") LocalDateTime endTime);
}


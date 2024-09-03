package com.management.gym.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.management.gym.entity.GymSession;

public interface GymSessionService {

	GymSession bookSession(GymSession session) throws ParseException;
	List<GymSession> searchSession(GymSession session);
	Optional<GymSession> fetchGymSession(Integer id);
	boolean rescheduleSession(Integer id, String startTimeString, String endTimeString) throws ParseException;
	boolean cancelSession(Integer id, LocalDateTime startTime) throws ParseException;
	List<GymSession> fetchAllSessions();
}

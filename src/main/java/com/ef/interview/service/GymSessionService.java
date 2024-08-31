package com.ef.interview.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.ef.interview.entity.GymSession;

public interface GymSessionService {

	GymSession bookSession(GymSession session) throws ParseException;
	List<GymSession> searchSession(GymSession session);
	Optional<GymSession> fetchGymSession(Integer id);
	boolean rescheduleSession(Integer id, String startTimeString, String endTimeString) throws ParseException;
	boolean cancelSession(Integer id, LocalDateTime startTime) throws ParseException;
}

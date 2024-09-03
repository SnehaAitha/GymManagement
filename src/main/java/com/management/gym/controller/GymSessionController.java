package com.management.gym.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.gym.entity.GymSession;
import com.management.gym.service.CustomerService;
import com.management.gym.service.GymSessionService;
import com.management.gym.service.TrainerService;
import com.management.gym.util.DateUtil;

@RestController
@RequestMapping("/gymsession")
public class GymSessionController {

	@Autowired
	GymSessionService gymSessionService;

	@Autowired
	TrainerService trainerService;

	@Autowired
	CustomerService customerService;

	@PostMapping("/bookSession")
	public ResponseEntity<?> bookSession(@RequestBody GymSession session) {
		GymSession gymSession = null;
		try {
			List<GymSession> sessions = gymSessionService.fetchAllSessions();
			if(!sessions.isEmpty() && sessions.size()>0)
			{
				for(GymSession sess:sessions)
				{
					LocalDateTime sessionStartTime = DateUtil.convertDateStringToUTCFormat(session.getStartTimeString());
					LocalDateTime sessionEndTime = DateUtil.convertDateStringToUTCFormat(session.getEndTimeString());

					if((sess.getStartTime().isEqual(sessionStartTime)) && (sess.getEndTime().isEqual(sessionEndTime))) {
						if(session.getTrainer().getId() == sess.getTrainer().getId()) {
							return new ResponseEntity<String>("Trainer is not available for this session.",HttpStatus.NOT_FOUND);
						}
						if (session.getCustomer().getId() == sess.getCustomer().getId()){
							return new ResponseEntity<String>("Customer is not available for this session.",HttpStatus.NOT_FOUND);
						}
					}
				}
			}
			gymSession = gymSessionService.bookSession(session);
			if(gymSession != null && gymSession.getId() != 0)
				return new ResponseEntity<GymSession>(gymSession,HttpStatus.OK);
			return new ResponseEntity<String>("GymSession booking failed.",HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("GymSession booking failed with exception.",HttpStatus.BAD_REQUEST);
		} 
	}

	@PostMapping("/searchSession")
	public ResponseEntity<?> searchSession(@RequestBody GymSession session) {
		List<GymSession> gymSessions = null;
		try {
			gymSessions = gymSessionService.searchSession(session);
			if(gymSessions != null && gymSessions.size()> 0)
				return new ResponseEntity<List<GymSession>>(gymSessions,HttpStatus.OK);
			return new ResponseEntity<String>("No gym sessions available.",HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("GymSession fetching failed with exception.",HttpStatus.BAD_REQUEST);
		} 
	}

	@DeleteMapping("/cancelSession/{id}")
	public ResponseEntity<?> cancelSession(@PathVariable("id") Integer id){
		Optional<GymSession> gymSession = null;
		boolean timeExceeded=false;
		try
		{
			gymSession = gymSessionService.fetchGymSession(id);
			if(gymSession.isEmpty() || !gymSession.isPresent()) {
				return new ResponseEntity<String>("Gym Session " + id+ " not found.",HttpStatus.NOT_FOUND);
			}
			timeExceeded=gymSessionService.cancelSession(id,gymSession.get().getStartTime());
			gymSession = gymSessionService.fetchGymSession(id);
			if(timeExceeded != true) {
				if(gymSession.isEmpty() || !gymSession.isPresent()) {		
					return new ResponseEntity<String>("Gym Session "
							+id+" cancelled successfully for free of charge.",HttpStatus.OK);
				}
			}
			else {
				if((gymSession.isEmpty() || !gymSession.isPresent())) {		
					return new ResponseEntity<String>("Gym Session "
							+id+" cancelled successfully but you will charged for the same.",HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("Gym Session cancellation " + id+ " failed.",HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Gym Session cancellation " + id+ " failed with exception.",HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/rescheduleSession/{id}")
	public ResponseEntity<?> rescheduleSession(@PathVariable("id") Integer id,@RequestBody GymSession session){
		Optional<GymSession> gymSession = null;
		boolean timeExceeded=false;
		try
		{
			gymSession = gymSessionService.fetchGymSession(id);
			if(gymSession.isEmpty() || !gymSession.isPresent()) {
				return new ResponseEntity<String>("Gym Session " + id+ " not found.",HttpStatus.NOT_FOUND);
			}
			timeExceeded=gymSessionService.rescheduleSession(id,session.getStartTimeString(),session.getEndTimeString());
			if(timeExceeded != true) {
				if(session.getStartTimeString() != null && session.getEndTimeString() != null) {		
					return new ResponseEntity<String>("Gym Session "
							+id+" rescheduled successfully without any charge.",HttpStatus.OK);
				}
			}
			else {
				if(session.getStartTimeString() != null && session.getEndTimeString() != null) {		
					return new ResponseEntity<String>("Gym Session "
							+id+" rescheduled successfully but you will charged for the same.",HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("Gym Session rescheduling for " + id+ " failed as the rescheduling time is not selected.",HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Gym Session rescheduling " + id+ " failed with exception.",HttpStatus.BAD_REQUEST);
		}
	}

}

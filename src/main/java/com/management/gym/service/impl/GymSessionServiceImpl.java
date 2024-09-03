package com.management.gym.service.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.management.gym.entity.Customer;
import com.management.gym.entity.Gym;
import com.management.gym.entity.GymSession;
import com.management.gym.entity.Trainer;
import com.management.gym.repository.GymSessionRepository;
import com.management.gym.service.CustomerService;
import com.management.gym.service.GymService;
import com.management.gym.service.GymSessionService;
import com.management.gym.service.TrainerService;
import com.management.gym.util.DateUtil;

@Service
public class GymSessionServiceImpl implements GymSessionService {

	@Autowired
	GymSessionRepository gymSessionRepo;

	@Autowired
	TrainerService trainerService;

	@Autowired
	CustomerService customerService;

	@Autowired
	GymService gymService;

	@Override
	public GymSession bookSession(GymSession session) throws ParseException {
		GymSession gymSession = new GymSession();
		
		LocalDateTime startTime = DateUtil.convertDateStringToUTCFormat(session.getStartTimeString());
		LocalDateTime endTime = DateUtil.convertDateStringToUTCFormat(session.getEndTimeString());

		gymSession.setStartTime(startTime);
		gymSession.setEndTime(endTime);

		Optional<Gym> gym = gymService.fetchGym(session.getGym().getId());
		Optional<Trainer> trainer = trainerService.fetchTrainer(session.getTrainer().getId());
		Optional<Customer> customer = customerService.fetchCustomer(session.getCustomer().getId());

		if(!gym.isEmpty() || gym.isPresent())
			gymSession.setGym(gym.get());
		if(!trainer.isEmpty() || trainer.isPresent()) {
			gymSession.setTrainer(trainer.get());
			trainerService.saveTrainer(trainer.get());
		}
		if(!customer.isEmpty() || customer.isPresent()) {
			gymSession.setCustomer(customer.get());
			customerService.saveCustomer(customer.get());
		}
		return gymSessionRepo.save(gymSession);
	}

	@Override
	public List<GymSession> searchSession(GymSession session) {
		List<GymSession> gymSessions = gymSessionRepo.findGymSessionByAllFields(session.getTrainer().getId(),
				session.getCustomer().getId(),session.getStartTime(),session.getEndTime()); 
		return gymSessions;
	}

	@Override
	public boolean cancelSession(Integer id,LocalDateTime startTime) throws ParseException {
		gymSessionRepo.deleteById(id);
		Date today = new Date(); 
		LocalDateTime currentDate = LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault());
		long differenceInHours = findDifferenceBetweenDates(currentDate,startTime);
		if(differenceInHours<24) {
			return true;
		}
		return false;
	}

	@Override
	public Optional<GymSession> fetchGymSession(Integer id) {
		return gymSessionRepo.findById(id);
	}

	@Override
	public boolean rescheduleSession(Integer id,String startTimeString, String endTimeString) throws ParseException {
		GymSession gymSession=fetchGymSession(id).get()	;
		
		LocalDateTime startTime = DateUtil.convertDateStringToUTCFormat(startTimeString);	
		LocalDateTime endTime = DateUtil.convertDateStringToUTCFormat(endTimeString);

		gymSession.setStartTime(startTime);
		gymSession.setEndTime(endTime);

		gymSessionRepo.save(gymSession);
		Date today = new Date(); 
		LocalDateTime currentDate = LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault());
		long differenceInHours = findDifferenceBetweenDates(currentDate,startTime);
		if(differenceInHours<24) {
			return true;
		}
		return false;

	}
	
	public long findDifferenceBetweenDates(LocalDateTime date1, LocalDateTime date2)
	{
		long hours = date1.until(date2, ChronoUnit.HOURS);
		return hours;
	}

	@Override
	public List<GymSession> fetchAllSessions() {
		return gymSessionRepo.findAll();
	}

}

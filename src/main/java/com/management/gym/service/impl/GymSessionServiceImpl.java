package com.management.gym.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
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

		final String DATE_FORMAT = "MM-dd-yyyy HH:mm:ss";

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
		formatter.setTimeZone(utcTimeZone);

		Date startDateInAmerica = new Date();

		startDateInAmerica = formatter.parse(session.getStartTimeString()); 
		LocalDateTime startTime = startDateInAmerica.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		gymSession.setStartTime(startTime);
		Date endDateInAmerica = new Date();

		endDateInAmerica = formatter.parse(session.getEndTimeString()); 
		LocalDateTime endTime = endDateInAmerica.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		gymSession.setEndTime(endTime);

		Optional<Gym> gym = gymService.fetchGym(session.getGym().getId());
		Optional<Trainer> trainer = trainerService.fetchTrainer(session.getTrainer().getId());
		Optional<Customer> customer = customerService.fetchCustomer(session.getCustomer().getId());

		if(!gym.isEmpty() || gym.isPresent())
			gymSession.setGym(gym.get());
		if(!trainer.isEmpty() || trainer.isPresent()) {
			gymSession.setTrainer(trainer.get());
			trainer.get().setAvailability(false);
			trainerService.saveTrainer(trainer.get());
		}
		if(!customer.isEmpty() || customer.isPresent()) {
			gymSession.setCustomer(customer.get());
			customerService.saveCustomer(customer.get());
			customer.get().setAvailability(false);
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
		final String DATE_FORMAT = "MM-dd-yyyy HH:mm:ss";

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
		formatter.setTimeZone(utcTimeZone);

		Date startDateInAmerica = new Date();

		startDateInAmerica = formatter.parse(startTimeString); 
		LocalDateTime startTime = startDateInAmerica.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		gymSession.setStartTime(startTime);

		Date endDateInAmerica = new Date();

		endDateInAmerica = formatter.parse(endTimeString); 
		LocalDateTime endTime = endDateInAmerica.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

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

}

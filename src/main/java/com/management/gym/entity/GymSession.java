package com.management.gym.entity;

import java.time.LocalDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="gymsession")
public class GymSession {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;

	@Column(name="start_time")
	private LocalDateTime startTime;

	@Column(name="end_time")
	private LocalDateTime endTime;

	@Transient
	private String startTimeString;

	@Transient
	private String endTimeString;

	@ManyToOne
	@JoinColumn(name = "gym_id")
	private Gym gym;

	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private Trainer trainer;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStartTimeString() {
		return startTimeString;
	}

	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}

	public String getEndTimeString() {
		return endTimeString;
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

	@Override
	public String toString() {
		return "GymSession [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", gym=" + gym
				+ ", trainer=" + trainer + ", customer=" + customer + "]";
	}

}

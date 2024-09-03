package com.management.gym.entity;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="bio")
	private String bio;

	@Column(name="dob")
	private LocalDate dob;
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<GymSession> gymSessions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public List<GymSession> getGymSessions() {
		return gymSessions;
	}

	public void setGymSessions(List<GymSession> gymSessions) {
		this.gymSessions = gymSessions;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", bio=" + bio + ", dob=" + dob + "]";
	}

}

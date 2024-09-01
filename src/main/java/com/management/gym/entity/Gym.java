package com.management.gym.entity;

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
@Table(name="gym")
public class Gym {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;

	@Column(name="us-state")
	private String usState;

	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "gym")
	@JsonIgnore
	private List<GymSession> gymSessions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsState() {
		return usState;
	}

	public void setUsState(String usState) {
		this.usState = usState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GymSession> getGymSessions() {
		return gymSessions;
	}

	public void setGymSession(List<GymSession> gymSessions) {
		this.gymSessions = gymSessions;
	}

	@Override
	public String toString() {
		return "Gym [id=" + id + ", usState=" + usState + ", name=" + name + "]";
	}

}
